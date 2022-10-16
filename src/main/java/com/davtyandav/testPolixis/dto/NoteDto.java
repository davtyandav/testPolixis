package com.davtyandav.testPolixis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NoteDto {

    private String id;

    @NotBlank(message = "Title may not be blank")
    @Size(max = 20)
    private String title;

    @Size(max = 512)
    private String note;
    private Date create;
    private Date updateTime;
    private String userId;
}
