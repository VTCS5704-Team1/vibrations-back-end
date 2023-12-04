package com.vibrations.vibrationsapi.controller;

import com.vibrations.vibrationsapi.dto.SendMessageRequestDto;
import com.vibrations.vibrationsapi.model.Message;
import com.vibrations.vibrationsapi.model.User;
import com.vibrations.vibrationsapi.service.MessageService;
import com.vibrations.vibrationsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST API Controller for handling messages.
 * Messaging is not implemented in the final project
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody SendMessageRequestDto sendMessageRequestDto) {
        return  ResponseEntity.ok(messageService.sendMessage(
                sendMessageRequestDto.getSenderEmail(),
                sendMessageRequestDto.getReceiverEmail(),
                sendMessageRequestDto.getContent()));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String email) {
        return ResponseEntity.ok(messageService.getMessages(email));
    }
}
