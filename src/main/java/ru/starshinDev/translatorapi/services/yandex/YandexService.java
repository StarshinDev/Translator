package ru.starshinDev.translatorapi.services.yandex;

import ru.starshinDev.translatorapi.dto.user.UserRequest;

public interface YandexService {
    String translate(UserRequest request);
}
