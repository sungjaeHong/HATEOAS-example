package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by sungjae.hong on 2017. 8. 23..
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResourceResponse<T> extends ResourceSupport {
    T data;
    String status = "SUCCESS";
    String message;
    ErrorDto errors;

    public ResourceResponse(T type, String status, String message) {
        this.data = type;
        this.status = status;
        this.message = message;
    }
    public ResourceResponse(ErrorDto errors) {
        this.errors = errors;
    }
}
