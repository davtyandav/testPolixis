package com.davtyandav.testPolixis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String id;
    private String email;
    private String password;
    private Date create;
}
