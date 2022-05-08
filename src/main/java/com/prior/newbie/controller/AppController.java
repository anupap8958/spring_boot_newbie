package com.prior.newbie.controller;

import com.prior.newbie.entities.People;
import com.prior.newbie.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/demo")
public class AppController {
    @Autowired
    private PeopleService peopleService;
    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping(value = "/{cid}")
    public People findById(@PathVariable String cid) {
        return peopleService.findById(cid).get();
    }
}
