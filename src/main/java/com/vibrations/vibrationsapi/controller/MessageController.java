//package com.vibrations.vibrationsapi.controller;
//
//import com.vibrations.vibrationsapi.model.Message;
//import com.vibrations.vibrationsapi.model.User;
//import com.vibrations.vibrationsapi.service.MessageService;
//import com.vibrations.vibrationsapi.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/messages")
//public class MessageController {
//    @Autowired
//    private MessageService messageService;
//    @Autowired
//    private UserService userService;
//
//    @PostMapping
//    public ResponseEntity<Message> sendMessage(
//            @RequestParam String senderEmail,
//            @RequestParam String receiverEmail,
//            @RequestParam String content) {
//
//        Optional<User> sender = userService.findUserByEmail(senderEmail);
//        Optional<User> receiver = userService.findUserByEmail(receiverEmail);
//
//        if (sender.isPresent() && receiver.isPresent()) {
//            Message message = messageService.sendMessage(sender.get(), receiver.get(), content);
//            return new ResponseEntity<>(message, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/{email}")
//    public ResponseEntity<List<Message>> getMessages(@PathVariable String email) {
//        Optional<User> user = userService.findUserByEmail(email);
//
//        if (user.isPresent()) {
//            List<Message> messages = messageService.getMessages(user.get());
//            return new ResponseEntity<>(messages, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}
