package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.VirtualAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by sungjae.hong on 2017. 8. 24..
 */
@RestController
public class VirtualAccountController {

    @PostMapping(value = "/openAccount")
    public BaseResponse openAccount(int applicantId, String applicantType) {
        Account account = new Account();
        account.setAccountNo(1);
        account.setAccountType("PRIME_STORE");
        account.setCreatedAt(new Date());
        account.setCreator("sungjae.hong");

        BaseResponse resourceResponse = new BaseResponse();
        resourceResponse.setContent(account);
        resourceResponse.setStatus("SUCCESS");
        resourceResponse.setMessage("");
        resourceResponse.add(linkTo(methodOn(VirtualAccountController.class).openVirtualAccount(applicantId, applicantType)).withRel("openVirtualBankAccount"));

        return resourceResponse;
    }

    @PostMapping(value = "/openVirtualBankAccount")
    public BaseResponse openVirtualAccount(int accountNo, String accountOwner) {
        VirtualAccount virtualAccount = new VirtualAccount();
        virtualAccount.setAccountNo(accountNo);
        virtualAccount.setBankCode("");

        BaseResponse resourceResponse = new BaseResponse();
        resourceResponse.setContent(virtualAccount);
        resourceResponse.setStatus("SUCCESS");
        resourceResponse.setMessage("");
        resourceResponse.add(linkTo(methodOn(VirtualAccountController.class).openAccount(virtualAccount.getAccountNo(), accountOwner)).withRel("openAccount"));

        return resourceResponse;
    }

    @GetMapping(value = "/getVirtualBankAccount")
    public BaseResponse getVirtualBankAccount(@RequestParam Long accountNumber) {
        return new BaseResponse();
    }

}
