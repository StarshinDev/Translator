package ru.vertuoscode.translatorapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "source_text")
    private String sourceText;
    @Column(name = "source_language")
    private String sourceLanguage;
    @Column(name = "translated_text")
    private String translatedText;
    @Column(name = "target_language")
    private String targetLanguage;
    @Column(name = "source_ip")
    private String sourceIp;
    @Column(name = "request_time")
    private Timestamp requestTime;
}
