package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sungjae.hong on 2017. 8. 24..
 */
@Getter
@Setter
public class ErrorDto {

    private Timestamp timestamp;
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ErrorDto(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public ErrorDto(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
