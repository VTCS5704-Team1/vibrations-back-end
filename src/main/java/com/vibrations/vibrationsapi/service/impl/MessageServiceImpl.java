package com.vibrations.vibrationsapi.service.impl;

import com.vibrations.vibrationsapi.model.Message;
import com.vibrations.vibrationsapi.model.User;
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
    public Message sendMessage(User sender, User receiver, String content) {
        Message message = new Message();
        message.setSenderEmail(sender);
        message.setReceiverEmail(receiver);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessages(User user) {
        return messageRepository.findBySenderOrReceiver(user, user);
    }
}
