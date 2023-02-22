package ru.starshinDev.translatorapi.services;

import org.springframework.stereotype.Service;
import ru.starshinDev.translatorapi.models.Request;
import ru.starshinDev.translatorapi.repo.RequestsRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class RequestService {
    private final HttpServletRequest servletRequest;
    private final RequestsRepository repository;

    public RequestService(HttpServletRequest servletRequest, RequestsRepository repository) {
        this.servletRequest = servletRequest;
        this.repository = repository;
    }

    public void save(Request request) {
        enrichRequest(request);
        repository.save(request);
    }

    private void enrichRequest(Request request) {
        request.setSourceIp(servletRequest.getRemoteAddr());
        request.setRequestTime(Timestamp.valueOf(LocalDateTime.now()));
    }
}
