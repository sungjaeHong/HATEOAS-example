package com.example.demo.controller;

import com.example.demo.LinkFactory;
import com.example.demo.model.Address;
import com.example.demo.model.Contact;
import com.example.demo.model.ErrorDto;
import com.example.demo.model.PageableResponse;
import com.example.demo.model.ResourceResponse;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by sungjae.hong on 2017. 8. 23..
 */
@RestController
@RequestMapping("/contacts")
public class ContactController {


    @Autowired
    ContactRepository contactRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    LinkFactory linkFactory;

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    @ResponseBody
    public PageableResponse contactsPages(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(
                page, size, new Sort("id")
        );
        Page<Contact> pageResult = contactRepository.findAll(pageable);
        PageableResponse contactPageResource = new PageableResponse(pageResult);
        contactPageResource = linkFactory.addLink(contactPageResource, pageResult);

        return contactPageResource;
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public ResourceResponse type(@RequestParam int page, @RequestParam int id) throws Exception {
        if (page > 5) {
            ResourceResponse<Contact> resourceResponse = new ResourceResponse();
            setResult(resourceResponse, contactRepository, new Long(id));
            System.out.println(resourceResponse.toString());
            resourceResponse.add(linkTo(methodOn(ContactController.class).addressList(6, 2)).withRel("address"));
            return resourceResponse;
        } else {
            ResourceResponse<Address> resourceResponse = new ResourceResponse();
            setResult(resourceResponse, addressRepository, new Long(id));
            System.out.println(resourceResponse.toString());
            return resourceResponse;
        }
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public PageableResponse addressList(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(page, size, new Sort("id"));
        Page<Address> pageResult = addressRepository.findAll(pageable);
        System.out.println(pageResult.toString());
        PageableResponse addressPageResource = new PageableResponse(pageResult);
        addressPageResource = linkFactory.addLink(addressPageResource, pageResult);
        return addressPageResource;
    }

    public ResourceResponse setResult(ResourceResponse resourceResponse, JpaRepository repository, Long id) throws Exception {
        Object data = repository.findOne(new Long(id));
        if (data != null) {
            resourceResponse.setStatus("SUCCESS");
            resourceResponse.setData(data);
        } else {
            resourceResponse.setStatus("ZERO_RESULTS");
            resourceResponse.setErrors(new ErrorDto(HttpStatus.BAD_REQUEST, "Bad Request", "검색 결과가 없습니다."));
        }
        return resourceResponse;

    }

}
