package ru.starshinDev.translatorapi.dto.yandex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class YandexResponse {
    private List<YandexPartResponse> translations;

    public List<YandexPartResponse> getTranslations() {
        return translations;
    }

    @NoArgsConstructor
    @Data
    public static class YandexPartResponse {
        private String text;
        private String detectedLanguageCode;
    }
}
