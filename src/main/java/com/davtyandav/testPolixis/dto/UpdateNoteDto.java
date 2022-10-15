package com.davtyandav.testPolixis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdateNoteDto {

    @NotBlank(message = "Title may not be blank")
    private String title;

    @Max(value = 512)
    private String note;
    private Date create;
    private Date updateTime;
    private String userId;
}
