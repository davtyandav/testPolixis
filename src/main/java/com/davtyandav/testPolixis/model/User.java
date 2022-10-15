package com.davtyandav.testPolixis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
public class User {

    private String id;
    private String email;
    private String password;
    private Date create;
}
