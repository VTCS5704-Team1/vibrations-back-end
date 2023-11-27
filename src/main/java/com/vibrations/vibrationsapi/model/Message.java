package com.vibrations.vibrationsapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "messages", schema = "vibrations_backend")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @JoinColumn(name = "sender_email", nullable = false)
    private String senderEmail;

    @JoinColumn(name = "receiver_email", nullable = false)
    private String receiverEmail;

    private String content;
    private LocalDateTime timestamp;
}
