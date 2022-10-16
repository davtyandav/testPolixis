package com.davtyandav.testPolixis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("notes")
@Getter
@Setter
@NoArgsConstructor
public class Note {

    @Id
    private String id;
    private String title;
    private String note;
    private Date create;
    private Date updateTime;
    private String userId;
}
