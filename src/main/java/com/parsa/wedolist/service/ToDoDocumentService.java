package com.parsa.wedolist.service;

import com.parsa.wedolist.model.ToDoDocument;
import org.springframework.stereotype.Service;

@Service
public interface ToDoDocumentService {
    ToDoDocument saveOrUpdate(ToDoDocument toDoDocument);
    ToDoDocument getById(String documentId);
    void deleteById(String documentId);
}
