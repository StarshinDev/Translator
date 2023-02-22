package ru.starshinDev.translatorapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.starshinDev.translatorapi.dto.user.UserRequest;
import ru.starshinDev.translatorapi.dto.user.UserResponse;
import ru.starshinDev.translatorapi.models.Request;
import ru.starshinDev.translatorapi.services.RequestService;
import ru.starshinDev.translatorapi.services.yandex.YandexServiceImpl;

import javax.validation.Valid;
@Controller
@RequestMapping("/translator")
public class TranslatorController {
    private final YandexServiceImpl yandexService;
    private final RequestService requestService;
    private final ModelMapper modelMapper;

    @Autowired
    public TranslatorController(YandexServiceImpl yandexService, RequestService requestService, ModelMapper modelMapper) {
        this.yandexService = yandexService;
        this.requestService = requestService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("newRequest", new UserRequest());
        model.addAttribute("response", new UserResponse());
        return "translate/index";
    }

    @PostMapping
    public String translate(@ModelAttribute("response") UserResponse userResponse,
                            @ModelAttribute("newRequest") @Valid UserRequest userRequest,
                            BindingResult result) {
        if (result.hasErrors())
            return "translate/index";
        Request request = convertToRequest(userRequest);
        request.setTranslatedText(yandexService.translate(userRequest));
        requestService.save(request);
        userResponse.setTranslatedText(request.getTranslatedText());
        return "translate/index";
    }

    private Request convertToRequest(UserRequest userRequest) {
        return modelMapper.map(userRequest, Request.class);
    }
}
