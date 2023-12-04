package com.vibrations.vibrationsapi.service.impl;

import com.vibrations.vibrationsapi.dto.SendMessageResponseDto;
import com.vibrations.vibrationsapi.model.Message;
import com.vibrations.vibrationsapi.repository.MessageRepository;
import com.vibrations.vibrationsapi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public SendMessageResponseDto sendMessage(String senderEmail, String receiverEmail, String content) {
        Message message = new Message();
        message.setSenderEmail(senderEmail);
        message.setReceiverEmail(receiverEmail);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
        SendMessageResponseDto response = new SendMessageResponseDto();
        response.setStatusCode(200);
        response.setStatusMessage("Message sent successfully");
        return response;
    }

    @Override
    public List<Message> getMessages(String userEmail){
        return messageRepository.findBySenderEmailOrReceiverEmail(userEmail, userEmail);
    }
}
