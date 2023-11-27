package com.vibrations.vibrationsapi.service;

import com.vibrations.vibrationsapi.dto.SendMessageResponseDto;
import com.vibrations.vibrationsapi.model.Message;
import com.vibrations.vibrationsapi.model.User;

import java.util.List;

public interface MessageService {
    SendMessageResponseDto sendMessage(String senderEmail, String receiverEmail, String content);
    List<Message> getMessages(String userEmail);
}
