package com.vibrations.vibrationsapi.repository;

import com.vibrations.vibrationsapi.model.Message;
import com.vibrations.vibrationsapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findBySenderEmail(String senderEmail);
    public  List<Message> findByReceiverEmail(String receiverEmail);

    public List<Message> findBySenderEmailOrReceiverEmail(String senderEmail, String receiverEmail);
}
