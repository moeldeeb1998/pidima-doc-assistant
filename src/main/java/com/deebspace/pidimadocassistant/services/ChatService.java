package com.deebspace.pidimadocassistant.services;

import com.deebspace.pidimadocassistant.models.Message;
import com.deebspace.pidimadocassistant.models.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ChatService {

    // In-memory stores for minimal template. Replace with DB in production.
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();
    private final Map<String, List<Message>> messages = new ConcurrentHashMap<>();

    public Session createSession(String metadata) {
        Session session = new Session(metadata);
        sessions.put(session.getId(), session);
        messages.put(session.getId(), Collections.synchronizedList(new ArrayList<>()));
        log.info("Created session {}", session.getId());
        return session;
    }

    public Message handleMessage(Message message) {
        // Validation: session exists
        if(!sessions.containsKey(message.getSessionId())) {
            throw new NoSuchElementException("Session not found");
        }

        Message userMsg = new Message(message.getSessionId(), "user", message.getText());

        // Minimal "LLM integration" placeholder: echo with prefix. Replace with actual async LLM call.
        Message assistantMsg = new Message(message.getSessionId(), "assistant", "(simulated Echo: " + message.getText());

        messages.get(message.getSessionId()).add(userMsg);
        messages.get(message.getSessionId()).add(assistantMsg);

        log.info("Handled message for session {}", message.getSessionId());

        return assistantMsg;
    }

    public List<Message> getHistory(String sessionId) {
        return messages.get(sessionId);
    }
}
