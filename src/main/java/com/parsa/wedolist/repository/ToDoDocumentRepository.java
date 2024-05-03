package com.parsa.wedolist.repository;

import com.parsa.wedolist.model.ToDoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoDocumentRepository extends MongoRepository<ToDoDocument, String>{
}
