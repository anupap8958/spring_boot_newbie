package com.prior.newbie.controller;

import com.prior.newbie.entities.People;
import com.prior.newbie.repository.NamedParameterCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/namedParame")
public class NamedParameterJbcTemplateController {

    @Autowired
    private NamedParameterCustomRepository namedParameterCustomRepository;

    @GetMapping("/findAll")
    public List<People> findAll() {
        return namedParameterCustomRepository.findAll();
    }

    @GetMapping("/findById/{cid}")
    public People findById(@PathVariable String cid) {
        return namedParameterCustomRepository.findById(cid);
    }

    @PostMapping("/create")
    public String create(@RequestBody People inputs) {
        return namedParameterCustomRepository.create(inputs) == 1 ? "Create Successfully" : "Failed, Please try again";
    }

    @PatchMapping("/update/{cid}")
    public String update(@PathVariable String cid, @RequestBody Map<String, String> inputs) {
        return namedParameterCustomRepository.update(cid, inputs) == 1 ? "Update Successfully" : "Failed, Please try again";
    }

    @PatchMapping("/delete/{cid}")
    public String delete(@PathVariable String cid) {
        return namedParameterCustomRepository.delete(cid) == 1 ? "Delete cid = " + cid + " Successfully" : "Failed, Please try again";
    }
}
