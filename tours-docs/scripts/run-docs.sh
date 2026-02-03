#!/bin/bash
# Run MkDocs documentation locally

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(dirname "$SCRIPT_DIR")"

cd "$PROJECT_ROOT"

echo "📘 EvalExpert Documentation Server"
echo "=================================="

# Check if virtual environment exists
if [ -d ".venv" ]; then
    echo "✅ Using existing virtual environment"
    source .venv/bin/activate
else
    echo "📦 Creating virtual environment..."
    python3 -m venv .venv
    source .venv/bin/activate
    echo "📥 Installing dependencies..."
    pip install --quiet mkdocs mkdocs-material
fi

# Verify mkdocs is installed
if ! command -v mkdocs &> /dev/null; then
    echo "📥 Installing MkDocs..."
    pip install --quiet mkdocs mkdocs-material
fi

echo ""
echo "🚀 Starting documentation server..."
echo "   Open: http://127.0.0.1:8000"
echo "   Press Ctrl+C to stop"
echo ""

mkdocs serve
