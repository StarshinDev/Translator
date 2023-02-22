package ru.starshinDev.translatorapi.services.yandex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.starshinDev.translatorapi.dto.yandex.YandexRequest;
import ru.starshinDev.translatorapi.dto.yandex.YandexResponse;
import ru.starshinDev.translatorapi.dto.user.UserRequest;

import java.util.Arrays;
import java.util.List;

@Service
public class YandexServiceImpl implements YandexService{
    private static final String SPLIT_REGEX = "[\\s,?!;.]+";
    private final String apiKey;
    private final String apiUrl;
    private final String apiFolderId;
    private final HttpHeaders headers;
    private final RestTemplate restTemplate;
    @Autowired
    public YandexServiceImpl(@Value("${translator.yandex.api-key}")String apiKey,
                             @Value("${translator.yandex.api-url}") String apiUrl,
                             @Value("${translator.yandex.api-folder-id}") String apiFolderId,
                             RestTemplate rest) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.apiFolderId = apiFolderId;
        this.restTemplate = rest;
        this.headers =  new HttpHeaders();
        initHeaders();
    }
    private void initHeaders() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Api-Key " + this.apiKey);
    }
    @Override
    public String translate(UserRequest request) {
        YandexResponse yandexResponse = sendRequest(request, Arrays.asList(request.getSourceText().split(SPLIT_REGEX)));
        return getTranslatedString(yandexResponse.getTranslations());
    }

    private String getTranslatedString(List<YandexResponse.YandexPartResponse> words) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i).getText());
            if (i < words.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public YandexResponse sendRequest(UserRequest userRequest, List<String> words) {
        YandexRequest requestBody = new YandexRequest(apiFolderId, userRequest.getSourceLanguage(),
                userRequest.getTargetLanguage(), words);
        HttpEntity<YandexRequest> entity = new HttpEntity<>(requestBody, headers);
        return restTemplate.postForObject(apiUrl, entity, YandexResponse.class);
    }
}
