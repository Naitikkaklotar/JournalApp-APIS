package com.naitik.journalapp.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
@Getter
@Setter
public class RedisUser implements Serializable {
    private String name;
    private String userID;
    private String phone;
    private String email;

}
