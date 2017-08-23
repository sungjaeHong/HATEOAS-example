package com.example.demo.repository;

import com.example.demo.model.Address;
import com.example.demo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sungjae.hong on 2017. 8. 23..
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findOne(Long id);
}