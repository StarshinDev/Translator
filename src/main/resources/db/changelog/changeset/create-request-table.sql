--liquibase formatted sql
--changeset Vitally:create-request
CREATE TABLE IF NOT EXISTS request
(
    request_ID      int generated by default as identity primary key,
    source_Text     VARCHAR     NOT NULL,
    source_Language VARCHAR(2)  NOT NULL,
    target_Language VARCHAR(2)  NOT NULL,
    translated_Text VARCHAR     NOT NULL,
    source_IP       VARCHAR(32),
    request_Time    TIMESTAMP   NOT NULL
);