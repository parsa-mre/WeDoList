package com.parsa.wedolist.controller;


import com.parsa.wedolist.model.ToDoDocument;
import com.parsa.wedolist.service.ToDoDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
public class ToDoEditController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ToDoDocumentService toDoDocumentService;

    @MessageMapping("/todo/{id}/push")
    public void pushToDoList(@Payload ToDoDocument toDoDocument, @DestinationVariable String id) {
        toDoDocumentService.saveAsync(toDoDocument);
        messagingTemplate.convertAndSend("/topic/todo/" + id, toDoDocument);
    }
}