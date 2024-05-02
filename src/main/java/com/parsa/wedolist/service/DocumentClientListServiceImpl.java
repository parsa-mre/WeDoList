package com.parsa.wedolist.service;

import com.parsa.wedolist.model.DocumentClientList;
import com.parsa.wedolist.repository.DocumentClientListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentClientListServiceImpl implements DocumentClientListService {

    private final DocumentClientListRepository documentClientListRepository;

    @Autowired
    public DocumentClientListServiceImpl(DocumentClientListRepository documentClientListRepository) {
        this.documentClientListRepository = documentClientListRepository;
    }

    @Override
    public DocumentClientList saveOrUpdate(DocumentClientList documentClientList) {
        return documentClientListRepository.save(documentClientList);
    }

    @Override
    public DocumentClientList getById(String documentId) {
        return null;
    }

    @Override
    public ArrayList<String> getRemoteAddresses(String documentId) {
        DocumentClientList documentClientList = documentClientListRepository.findById(documentId).orElse(null);
        if (documentClientList == null) {
            return new ArrayList<>();
        }
        return documentClientList.getRemoteAddresses();
    }

    @Override
    public void addRemoteAddress(String documentId, String remoteAddress) {
        DocumentClientList documentClientList = documentClientListRepository.findById(documentId).orElse(null);
        if (documentClientList == null) {
            documentClientList = new DocumentClientList();
            documentClientList.setDocumentId(documentId);
            documentClientList.setRemoteAddresses(new ArrayList<>(List.of(remoteAddress)));
        }
        else {
            documentClientList.getRemoteAddresses().add(remoteAddress);
        }
    }

    @Override
    public void removeRemoteAddress(String documentId, String remoteAddress) {
        DocumentClientList documentClientList = documentClientListRepository.findById(documentId).orElse(null);
        if (documentClientList == null) {
            return;
        }
        documentClientList.getRemoteAddresses().remove(remoteAddress);
    }

    @Override
    public void removeAllRemoteAddresses(String documentId) {
        DocumentClientList documentClientList = documentClientListRepository.findById(documentId).orElse(null);
        if (documentClientList == null) {
            return;
        }
        documentClientList.setRemoteAddresses(new ArrayList<>());
    }
}
