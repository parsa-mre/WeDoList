package com.parsa.wedolist.service;

import com.parsa.wedolist.model.DocumentClientList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface DocumentClientListService {
    DocumentClientList saveOrUpdate(DocumentClientList documentClientList);
    DocumentClientList getById(String documentId);
    ArrayList<String> getRemoteAddresses(String documentId);
    void addRemoteAddress(String documentId, String remoteAddress);
    void removeRemoteAddress(String documentId, String remoteAddress);
    void removeAllRemoteAddresses(String documentId);
}
