package com.deebspace.pidimadocassistant.api.v1;

import com.deebspace.pidimadocassistant.services.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChatController.class)
public class ChatControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ChatService chatService;

    @Test
    public void postMessageMissingSession() throws Exception {
        when(chatService.handleMessage(any()))
                .thenThrow(new NoSuchElementException("Session not found"));

        mockMvc.perform(post("/api/v1/chat/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sessionId\":\"no-session\",\"role\":\"user\",\"text\":\"hello\"}"))
                .andExpect(status().isNotFound());


    }
}
