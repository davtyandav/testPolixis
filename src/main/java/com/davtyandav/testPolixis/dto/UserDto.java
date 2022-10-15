package com.davtyandav.testPolixis.dto;

import com.davtyandav.testPolixis.model.Note;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String id;
    private String email;
    private String password;
    private Date create;

}
