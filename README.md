# Pidima Chat Assistant


Minimal microservice demonstrating the required endpoints:
- POST /chat/session
- POST /chat/message
- GET /chat/history/{session_id}
- GET /health


Includes basic validation, error handling, logging, configuration, unit tests, and Dockerfile.


## Run locally
1. Build: `mvn clean package`
2. Run: `java -jar target/pidima-doc-assistant-0.0.1-SNAPSHOT.jar`


## Docker
Build: `docker build -t pidima-doc-assistant:local .`
Run: `docker run -p 8080:8080 pidima-doc-assistant:local`


## Tests
`mvn test`


## API design notes (small, clear rules)

- `POST /chat/session`

  - Request body: optional metadata (e.g., user-id, doc-id)

  - Response: `{ sessionId, createdAt, metadata }`


- `POST /chat/message`

    - Request body: `{ sessionId, role: 'user', text }`

    - Response: assistant message object `{ id, sessionId, role:'assistant', text, timestamp }`

    - Behavior: validate session; append user message; enqueue for LLM (or call LLM synchronously for prototype); append assistant response

- `GET /chat/history/{sessionId}`

    - Response: list of messages in chronological order

- `GET /health`

    - Simple `200 OK` and quick checks for DB/queue readiness (in prod)

Validation: use DTO constraints, return `400` for bad input, `404` for missing session, `500` for unexpected errors.

Idempotency: For `POST /chat/message`, accept an optional `clientMessageId` to avoid duplicates.

check postman collection [here](https://cynerv.postman.co/workspace/My-Workspace~e018740d-6d6c-4a8d-8199-c81c4624959b/collection/32022656-ce844de0-ca25-4656-9902-d23b6d45809d?action=share&creator=32022656)
