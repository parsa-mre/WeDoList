package com.parsa.wedolist.service;

import com.parsa.wedolist.model.ToDoDocument;
import com.parsa.wedolist.repository.ToDoDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ToDoDocumentCRDTServiceImpl implements ToDoDocumentService {

    private final ToDoDocumentRepository toDoDocumentRepository;

    @Autowired
    public ToDoDocumentCRDTServiceImpl(ToDoDocumentRepository toDoDocumentRepository) {
        this.toDoDocumentRepository = toDoDocumentRepository;
    }

    @Override
    public ToDoDocument saveOrUpdate(ToDoDocument toDoDocument) {
        ToDoDocument existingToDoDocument = toDoDocumentRepository.findById(toDoDocument.getId()).orElse(null);
        if (existingToDoDocument == null) {
            return toDoDocumentRepository.save(toDoDocument);
        }

        existingToDoDocument.merge(toDoDocument);
        return toDoDocumentRepository.save(existingToDoDocument);
    }

    @Override
    public void saveAsync(ToDoDocument toDoDocument) {
        ToDoDocument existingToDoDocument = toDoDocumentRepository.findById(toDoDocument.getId()).orElse(null);
        if (existingToDoDocument == null) {
            return;
        }
        existingToDoDocument.merge(toDoDocument);
        toDoDocumentRepository.save(existingToDoDocument);
    }

    @Override
    public ToDoDocument getById(String documentId) {
        return toDoDocumentRepository.findById(documentId).orElse(null);
    }

    @Override
    public void deleteById(String documentId) {
        toDoDocumentRepository.deleteById(documentId);
    }

    @Override
    public ToDoDocument createAToDoList() {
        String id = UUID.randomUUID().toString().substring(0, 5);
        ToDoDocument toDoDocument = new ToDoDocument(
            id,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );
        return toDoDocumentRepository.save(toDoDocument);
    }
}
