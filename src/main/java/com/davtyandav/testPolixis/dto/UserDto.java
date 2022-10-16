package com.davtyandav.testPolixis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String id;

    @Email
    private String email;

    @NotBlank(message = "Password may not be blank")
    @Size(min = 8)
    private String password;
    private Date create;

}
