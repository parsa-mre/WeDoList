package com.parsa.wedolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;

@RedisHash("DocumentClientList")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentClientList implements Serializable {
    @Id
    private String documentId;
    private ArrayList<String> remoteAddresses;
}
