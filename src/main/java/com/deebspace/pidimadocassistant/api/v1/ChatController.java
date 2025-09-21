package com.deebspace.pidimadocassistant.api.v1;

import com.deebspace.pidimadocassistant.models.Message;
import com.deebspace.pidimadocassistant.models.Session;
import com.deebspace.pidimadocassistant.services.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatservice;

    @PostMapping("/session")
    public ResponseEntity<Session> createSession(@RequestBody(required = false) Session dto) {
        Session session = chatservice.createSession(dto != null ? dto.getMetaData() : null);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }

    @PostMapping("/message")
    public ResponseEntity<Message> postMessage( @Valid @RequestBody Message message){
        Message responseMessage = chatservice.handleMessage(message);
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/history/{sessionId}")
    public ResponseEntity<List<Message>> history(@PathVariable String sessionId){
        List<Message> history = chatservice.getHistory(sessionId);
        if(history == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(history);
    }
}
