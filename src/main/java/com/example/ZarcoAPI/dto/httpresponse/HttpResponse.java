package com.example.ZarcoAPI.dto.httpresponse;

import com.example.ZarcoAPI.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpResponse<T> {
    private Status status;
    private T payload;
    private Object errors;
    private Object metadata;

    public static <T> HttpResponse<T> badRequest() {
        HttpResponse<T> response = new HttpResponse<>();
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }

    public static <T> HttpResponse<T> ok() {
        HttpResponse<T> response = new HttpResponse<>();
        response.setStatus(Status.OK);
        return response;
    }

    public static <T> HttpResponse<T> exception() {
        HttpResponse<T> response = new HttpResponse<>();
        response.setStatus(Status.EXCEPTION);
        return response;
    }

    public static <T> HttpResponse<T> notFound() {
        HttpResponse<T> response = new HttpResponse<>();
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

    public static <T> HttpResponse<T> duplicateEntity() {
        HttpResponse<T> response = new HttpResponse<>();
        response.setStatus(Status.DUPLICATE_ENTITY);
        return response;
    }

    public enum Status {
        OK,
        BAD_REQUEST,
        UNAUTHORIZED,
        VALIDATION_EXCEPTION,
        EXCEPTION,
        WRONG_CREDENTIALS,
        ACCESS_DENIED,
        NOT_FOUND,
        DUPLICATE_ENTITY
    }

}
