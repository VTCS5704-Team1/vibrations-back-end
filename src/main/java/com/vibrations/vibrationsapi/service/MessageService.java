package com.vibrations.vibrationsapi.service;

import com.vibrations.vibrationsapi.dto.SendMessageResponseDto;
import com.vibrations.vibrationsapi.model.Message;

import java.util.List;

/**
 * Services for messages
 */
public interface MessageService {

    /**
     * Uploads message to the database
     * @param senderEmail -- Sender id
     * @param receiverEmail -- Receiver id
     * @param content -- Message content
     * @return -- Confirmation JSON
     */
    SendMessageResponseDto sendMessage(String senderEmail, String receiverEmail, String content);
    List<Message> getMessages(String userEmail);
}
