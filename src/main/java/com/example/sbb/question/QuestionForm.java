package com.example.sbb.question;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
    @Size(max=200)
    @NotEmpty(message="제목은 필수항목입니다.")
    private String title;

    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;
}
