package com.prior.newbie.controller;

import com.prior.newbie.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
public class AppController {
    @Autowired
    private PeopleRepository peopleRepository;

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello";
    }

//    @GetMapping(path="/all")
//    @ResponseBody
//    public Collection<People> findAll() {
//        System.out.println("cc");
//        List<People> users = (List<People>) peopleRepository.findAll();
//        System.out.println(users);
//        return users;
//    }
}
