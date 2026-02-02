# Content Import

## Import Format

Content imported via JSON or CSV with the following schema:

```json
{
  "blogs": [
    {
      "title": "Post Title",
      "slug": "post-title",
      "content": "Markdown content...",
      "featuredImage": "path/to/image.jpg",
      "publishedAt": "2025-01-15",
      "tags": ["egypt", "travel"]
    }
  ]
}
```

## Import Process

1. Prepare import file
2. Validate format
3. Run import script
4. Review imported items
5. Fix any issues
6. Publish approved content
