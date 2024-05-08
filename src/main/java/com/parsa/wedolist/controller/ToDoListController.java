package com.parsa.wedolist.controller;

import com.parsa.wedolist.model.ToDoDocument;
import com.parsa.wedolist.service.DocumentClientListService;
import com.parsa.wedolist.service.ToDoDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${url_prefix}/todos")
public class ToDoListController {
    private final DocumentClientListService documentClientListService;
    private final ToDoDocumentService toDoDocumentService;

    @GetMapping("/{id}")
    public ToDoDocument getToDoList(@PathVariable("id") String documentId) {
        return toDoDocumentService.getById(documentId);
    }

    @PostMapping("/")
    public ToDoDocument createToDoList(@RequestBody ToDoDocument toDoDocument) {
        return toDoDocumentService.saveOrUpdate(toDoDocument);
    }


}
