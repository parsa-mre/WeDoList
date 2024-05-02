package com.parsa.wedolist.repository;

import com.parsa.wedolist.model.DocumentClientList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentClientListRepository extends CrudRepository<DocumentClientList, String> {}
