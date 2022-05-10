package com.prior.newbie.controller;

import com.prior.newbie.entities.People;
import com.prior.newbie.repository.JdbcCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/jdbc")
public class JdbcController {
    @Autowired
    private JdbcCustomRepository jdbcCustomRepository;

    @GetMapping("/findAll")
    public List<People> findAll() {
        return jdbcCustomRepository.findAll();
    }

    @GetMapping("/findById/{cid}")
    public People findById(@PathVariable String cid) {
        return jdbcCustomRepository.getPeopleById(cid);
    }

    @GetMapping("/findPeopleIsDeleted")
    public List<People> getPeopleIs_deleated() {
        return jdbcCustomRepository.getPeopleIs_deleated();
    }

    @PostMapping("/create")
    public String create(@RequestBody People inputs) {
        return jdbcCustomRepository.create(inputs) == 1 ? "Create Successfully" : "Failed, Please try again";
    }

    @PatchMapping("/update/{cid}")
    public String update(@PathVariable String cid, @RequestBody Map<String, String> inputs) {
        return jdbcCustomRepository.update(cid, inputs) == 1 ? "Update Successfully" : "Failed, Please try again";
    }

    @PatchMapping("/delete/{cid}")
    public String delete(@PathVariable String cid) {
        return jdbcCustomRepository.delete(cid) == 1 ? "Delete cid = " + cid + " Successfully" : "Failed, Please try again";
    }
}