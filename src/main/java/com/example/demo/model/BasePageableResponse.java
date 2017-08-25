package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.ResourceSupport;

import java.util.Iterator;
import java.util.List;

/**
 * Created by sungjae.hong on 2017. 8. 23..
 */
@Getter
@Setter
@JsonIgnoreProperties({"data", "number", "first", "last", "sort"})
public class BasePageableResponse<T> extends ResourceSupport implements Page<T> {

    Page<T> data;

    int page;

    String message;

    String status = "SUCCESS";

    ErrorDto errors;

    public BasePageableResponse(Page<T> page) {
        data = page;
    }

    public int getPage() {
        return data.getNumber();
    }

    @Override
    public int getTotalPages() {
        return data.getTotalPages();
    }

    @Override
    public long getTotalElements() {
        return data.getTotalElements();
    }

    @Override
    public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
        return null;
    }

    @Override
    public int getNumber() {
        return data.getNumber();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public int getNumberOfElements() {
        return data.getNumberOfElements();
    }

    @Override
    public List<T> getContent() {
        return data.getContent();
    }

    @Override
    public boolean hasContent() {
        return data.hasContent();
    }

    @Override
    public Sort getSort() {
        return data.getSort();
    }

    @Override
    public boolean isFirst() {
        return data.isFirst();
    }

    @Override
    public boolean isLast() {
        return data.isLast();
    }

    @Override
    public boolean hasNext() {
        return data.hasNext();
    }

    @Override
    public boolean hasPrevious() {
        return data.hasPrevious();
    }

    @Override
    public Pageable nextPageable() {
        return data.nextPageable();
    }

    @Override
    public Pageable previousPageable() {
        return data.previousPageable();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

}

