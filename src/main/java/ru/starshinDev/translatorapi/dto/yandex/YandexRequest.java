package ru.starshinDev.translatorapi.dto.yandex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YandexRequest {
    private String folderId;
    private String sourceLanguageCode;
    private String targetLanguageCode;
    private List<String> texts;
}
