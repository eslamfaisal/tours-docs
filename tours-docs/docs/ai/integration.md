# Chat Integration

## Widget Component

```jsx
// components/ChatWidget.jsx
'use client';
import { useState } from 'react';
import { ChatWindow } from './ChatWindow';

export function ChatWidget() {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <>
      <button 
        className="chat-fab"
        onClick={() => setIsOpen(true)}
        aria-label="Open chat"
      >
        💬
      </button>
      {isOpen && <ChatWindow onClose={() => setIsOpen(false)} />}
    </>
  );
}
```

## API Endpoint

```http
POST /api/v1/chat
Content-Type: application/json

{
  "message": "Looking for a 3-day Cairo tour",
  "conversationId": "conv_123",
  "locale": "en"
}
```

## Response Stream

Uses Server-Sent Events for streaming responses.
