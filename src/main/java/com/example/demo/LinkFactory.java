package com.example.demo;

import com.example.demo.model.PageableResponse;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by sungjae.hong on 2017. 8. 23..
 */
@Service
public class LinkFactory<T> {

    public PageableResponse addLink(PageableResponse pageResource, Page<T> page) {
        if (page.hasPrevious()) {
            String path = createBuilder()
                    .queryParam("size", page.getSize())
                    .queryParam("page", page.getNumber() - 1)
                    .build()
                    .toUriString();
            Link link = new Link(path, Link.REL_PREVIOUS);
            pageResource.add(link);
        }
        if (page.hasNext()) {
            String path = createBuilder()
                    .queryParam("size", page.getSize())
                    .queryParam("page", page.getNumber() + 1)
                    .build()
                    .toUriString();
            Link link = new Link(path, Link.REL_NEXT);
            pageResource.add(link);
        }
        Link link = buildPageLink("page", 0, "size", page.getSize(), Link.REL_FIRST);
        pageResource.add(link);

        int indexOfLastPage = page.getTotalPages() - 1;
        link = buildPageLink("page", indexOfLastPage, "size", page.getSize(), Link.REL_LAST);
        pageResource.add(link);
        return pageResource;
    }

    private ServletUriComponentsBuilder createBuilder() {
        return ServletUriComponentsBuilder.fromCurrentRequestUri();
    }

    private Link buildPageLink(String pageParam, int page, String sizeParam, int size, String rel) {
        String path = createBuilder()
                .queryParam(sizeParam, size)
                .queryParam(pageParam, page)
                .build()
                .toUriString();
        Link link = new Link(path, rel);
        return link;
    }
}
