package ru.starshinDev.translatorapi.dto.user;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRequest {
    @NotBlank
    @Size(min = 2, max = 2, message = "Неверный код языка")
    private String sourceLanguage;

    @NotBlank
    @Size(min = 2, max = 2, message = "Неверный код языка")
    private String targetLanguage;

    @NotBlank(message = "Текст не должен быть пустым")
    private String sourceText;
}
