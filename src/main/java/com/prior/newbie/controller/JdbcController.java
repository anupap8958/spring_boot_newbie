package com.prior.newbie.controller;

import com.prior.newbie.entities.People;
import com.prior.newbie.repository.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/jdbc")
public class JdbcController {
    @Autowired
    private JdbcRepository jdbcRepository;

    @GetMapping("/findAll")
    public List<People> findAll() {
        return jdbcRepository.findAll();
    }

    @GetMapping("/findById/{cid}")
    public List<People> findById(@PathVariable String cid) {
        return jdbcRepository.getPeopleById(cid);
    }

    @GetMapping("/findPeopleIsDeleted")
    public List<People> getPeopleIs_deleated() {
        return jdbcRepository.getPeopleIs_deleated();
    }

    @PostMapping("/create")
    public String create(@RequestBody People inputs) {
        return jdbcRepository.create(inputs) == 1 ? "Create Successfully" : "Failed, Please try again";
    }

    @PatchMapping("/update/{cid}")
    public String update(@PathVariable String cid, @RequestBody People inputs) {
        return jdbcRepository.update(cid, inputs) == 1 ? "Update Successfully" : "Failed, Please try again";
    }

    @PatchMapping("/delete/{cid}")
    public String delete(@PathVariable String cid) {
        return jdbcRepository.delete(cid) == 1 ? "Delete cid = " + cid + " Successfully" : "Failed, Please try again";
    }
}