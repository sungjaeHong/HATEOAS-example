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
    String status;
    String errorMessage;
}
