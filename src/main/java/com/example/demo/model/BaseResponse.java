package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by sungjae.hong on 2017. 8. 23..
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BaseResponse<T> extends ResourceSupport {
    T content;
    String status = "SUCCESS";
    String message;
    ErrorDto errors;

    public BaseResponse(T type, String status, String message) {
        this.content = type;
        this.status = status;
        this.message = message;
    }

    public BaseResponse(ErrorDto errors) {
        this.errors = errors;
    }
}
