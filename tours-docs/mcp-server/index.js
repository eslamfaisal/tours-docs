#!/usr/bin/env node

/**
 * Egypt Tours Docs MCP Server
 * 
 * A simple MCP server that provides access to MkDocs documentation.
 * Tools: docs_search, docs_read, docs_list
 */

const { Server } = require('@modelcontextprotocol/sdk/server/index.js');
const { StdioServerTransport } = require('@modelcontextprotocol/sdk/server/stdio.js');
const {
     CallToolRequestSchema,
     ListToolsRequestSchema,
     ListResourcesRequestSchema,
     ReadResourceRequestSchema
} = require('@modelcontextprotocol/sdk/types.js');
const fs = require('fs');
const path = require('path');
const { glob } = require('glob');

const DOCS_ROOT = path.join(__dirname, '..', 'docs');

// Create MCP Server
const server = new Server(
     { name: 'egypt-tours-docs', version: '1.0.0' },
     { capabilities: { tools: {}, resources: {} } }
);

// List available tools
server.setRequestHandler(ListToolsRequestSchema, async () => ({
     tools: [
          {
               name: 'docs_search',
               description: 'Search documentation for relevant content. Returns matching sections with file paths.',
               inputSchema: {
                    type: 'object',
                    properties: {
                         query: { type: 'string', description: 'Search query (keywords)' },
                         section: { type: 'string', description: 'Optional: Limit to section (api, backend, frontend, etc.)' },
                         limit: { type: 'number', default: 10, description: 'Max results' }
                    },
                    required: ['query']
               }
          },
          {
               name: 'docs_read',
               description: 'Read the full content of a documentation file.',
               inputSchema: {
                    type: 'object',
                    properties: {
                         path: { type: 'string', description: 'Relative path to file (e.g., api/auth.md)' }
                    },
                    required: ['path']
               }
          },
          {
               name: 'docs_list',
               description: 'List documentation structure - sections and files.',
               inputSchema: {
                    type: 'object',
                    properties: {
                         section: { type: 'string', description: 'Optional: Specific section to list' }
                    }
               }
          },
          {
               name: 'requirements_extract',
               description: 'Extract requirements for a feature from documentation.',
               inputSchema: {
                    type: 'object',
                    properties: {
                         feature: { type: 'string', description: 'Feature name to extract requirements for' }
                    },
                    required: ['feature']
               }
          }
     ]
}));

// Handle tool calls
server.setRequestHandler(CallToolRequestSchema, async (request) => {
     const { name, arguments: args } = request.params;

     switch (name) {
          case 'docs_search':
               return await searchDocs(args.query, args.section, args.limit || 10);
          case 'docs_read':
               return await readDoc(args.path);
          case 'docs_list':
               return await listDocs(args.section);
          case 'requirements_extract':
               return await extractRequirements(args.feature);
          default:
               throw new Error(`Unknown tool: ${name}`);
     }
});

// List resources (documentation sections)
server.setRequestHandler(ListResourcesRequestSchema, async () => ({
     resources: [
          { uri: 'docs://product', name: 'Product', description: 'Vision, PRD, personas' },
          { uri: 'docs://requirements', name: 'Requirements', description: 'Functional & non-functional specs' },
          { uri: 'docs://api', name: 'API', description: 'RESTful endpoint specs' },
          { uri: 'docs://backend', name: 'Backend', description: 'Clean architecture, modules' },
          { uri: 'docs://frontend', name: 'Frontend', description: 'React, Zustand, i18n' },
          { uri: 'docs://ai', name: 'AI & Chat', description: 'MCP tools, RAG, guardrails' },
          { uri: 'docs://admin', name: 'Admin', description: 'Backoffice operations' },
          { uri: 'docs://devops', name: 'DevOps', description: 'CI/CD, hosting' }
     ]
}));

// Read resource
server.setRequestHandler(ReadResourceRequestSchema, async (request) => {
     const uri = request.params.uri;
     const section = uri.replace('docs://', '');
     const result = await listDocs(section);
     return { contents: [{ uri, mimeType: 'text/plain', text: JSON.stringify(result.content, null, 2) }] };
});

// Tool implementations
async function searchDocs(query, section, limit) {
     const pattern = section
          ? path.join(DOCS_ROOT, section, '**/*.md')
          : path.join(DOCS_ROOT, '**/*.md');

     const files = await glob(pattern);
     const results = [];
     const queryLower = query.toLowerCase();
     const queryTerms = queryLower.split(/\s+/);

     for (const file of files) {
          try {
               const content = fs.readFileSync(file, 'utf-8');
               const contentLower = content.toLowerCase();

               // Simple relevance scoring
               let score = 0;
               for (const term of queryTerms) {
                    const matches = (contentLower.match(new RegExp(term, 'g')) || []).length;
                    score += matches;
               }

               if (score > 0) {
                    const relativePath = path.relative(DOCS_ROOT, file);

                    // Extract matching context
                    const lines = content.split('\n');
                    const matchingLines = lines.filter(line =>
                         queryTerms.some(term => line.toLowerCase().includes(term))
                    ).slice(0, 3);

                    results.push({
                         file: relativePath,
                         score,
                         preview: matchingLines.join('\n').substring(0, 300)
                    });
               }
          } catch (err) {
               // Skip unreadable files
          }
     }

     results.sort((a, b) => b.score - a.score);

     return {
          content: [{
               type: 'text',
               text: JSON.stringify({
                    query,
                    results: results.slice(0, limit),
                    total: results.length
               }, null, 2)
          }]
     };
}

async function readDoc(docPath) {
     const fullPath = path.join(DOCS_ROOT, docPath);

     if (!fs.existsSync(fullPath)) {
          return {
               content: [{
                    type: 'text',
                    text: JSON.stringify({ error: 'File not found', path: docPath })
               }]
          };
     }

     const content = fs.readFileSync(fullPath, 'utf-8');

     return {
          content: [{
               type: 'text',
               text: content
          }]
     };
}

async function listDocs(section) {
     const searchPath = section
          ? path.join(DOCS_ROOT, section)
          : DOCS_ROOT;

     if (!fs.existsSync(searchPath)) {
          return {
               content: [{
                    type: 'text',
                    text: JSON.stringify({ error: 'Section not found', section })
               }]
          };
     }

     const result = {};

     if (section) {
          // List files in specific section
          const files = fs.readdirSync(searchPath)
               .filter(f => f.endsWith('.md'))
               .map(f => ({
                    file: f,
                    path: `${section}/${f}`
               }));
          result.section = section;
          result.files = files;
     } else {
          // List all sections
          const sections = fs.readdirSync(DOCS_ROOT)
               .filter(f => fs.statSync(path.join(DOCS_ROOT, f)).isDirectory())
               .map(dir => ({
                    name: dir,
                    files: fs.readdirSync(path.join(DOCS_ROOT, dir))
                         .filter(f => f.endsWith('.md')).length
               }));
          result.sections = sections;
     }

     return {
          content: [{
               type: 'text',
               text: JSON.stringify(result, null, 2)
          }]
     };
}

async function extractRequirements(feature) {
     const featureLower = feature.toLowerCase();
     const requirementsFiles = [
          'requirements/functional.md',
          'requirements/non-functional.md',
          'requirements/feature-checklist.md'
     ];

     const extracted = [];

     for (const reqFile of requirementsFiles) {
          const fullPath = path.join(DOCS_ROOT, reqFile);
          if (fs.existsSync(fullPath)) {
               const content = fs.readFileSync(fullPath, 'utf-8');
               const lines = content.split('\n');

               for (let i = 0; i < lines.length; i++) {
                    if (lines[i].toLowerCase().includes(featureLower)) {
                         // Get context (previous heading + matching line)
                         let heading = '';
                         for (let j = i; j >= 0; j--) {
                              if (lines[j].startsWith('#')) {
                                   heading = lines[j].replace(/^#+\s*/, '');
                                   break;
                              }
                         }
                         extracted.push({
                              source: reqFile,
                              heading,
                              content: lines[i].trim()
                         });
                    }
               }
          }
     }

     return {
          content: [{
               type: 'text',
               text: JSON.stringify({
                    feature,
                    requirements: extracted
               }, null, 2)
          }]
     };
}

// Start the server
async function main() {
     const transport = new StdioServerTransport();
     await server.connect(transport);
     console.error('Egypt Tours Docs MCP Server running on stdio');
}

main().catch(console.error);
