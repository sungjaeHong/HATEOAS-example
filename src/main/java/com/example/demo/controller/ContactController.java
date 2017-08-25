package com.example.demo.controller;

import com.example.demo.LinkFactory;
import com.example.demo.model.Address;
import com.example.demo.model.BasePageableResponse;
import com.example.demo.model.Contact;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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


    //used wrapper class
    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    @ResponseBody
    public BasePageableResponse contactsPages(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(
                page, size, new Sort("id")
        );
        Page<Contact> pageResult = contactRepository.findAll(pageable);

        BasePageableResponse contactPageResource = new BasePageableResponse(pageResult);
        contactPageResource = linkFactory.addLink(contactPageResource, pageResult);

        return contactPageResource;
    }

    //used wrapper class
    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public BasePageableResponse addressList(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(page, size, new Sort("id"));
        Page<Address> pageResult = addressRepository.findAll(pageable);
        System.out.println(pageResult.toString());
        BasePageableResponse addressPageResource = new BasePageableResponse(pageResult);
        addressPageResource = linkFactory.addLink(addressPageResource, pageResult);
        return addressPageResource;
    }

    @GetMapping(value = "/contact/{id}")
    public Resource<Contact> getContact(@PathVariable Long id) {
        return contactToResource(contactRepository.findOne(id));
    }

    @GetMapping(value = "/contact")
    public Resources<Resource<Contact>> getContacts() {
        return contactToResource(contactRepository.findAll());
    }

    private Resource<Contact> contactToResource(Contact contact) {
        Link selfLink = linkTo(methodOn(ContactController.class).getContact(contact.getId())).withSelfRel();
        return new Resource<>(contact, selfLink);
    }

    private Resources<Resource<Contact>> contactToResource(List<Contact> contacts) {
        Link selfLink = linkTo(methodOn(ContactController.class).getContacts()).withSelfRel();
        List<Resource<Contact>> contactResources = contacts.stream().map(contact -> contactToResource(contact)).collect(Collectors.toList());
        return new Resources<>(contactResources, selfLink);
    }

}
