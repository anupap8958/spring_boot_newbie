package com.prior.newbie.controller;

import com.prior.newbie.entities.People;
import com.prior.newbie.repository.EntityManagerCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/entity")
public class EntityController {
    @Autowired
    private EntityManagerCustomRepository entityManagerCustomRepository;

    @GetMapping("/findAll")
    public List<People> findAll() {
        return entityManagerCustomRepository.findAll();
    }

    @GetMapping("/findById/{cid}")
    public People findById(@PathVariable String cid) {
        return entityManagerCustomRepository.findById(cid);
    }

    @PostMapping("/create")
    public String create(@RequestBody People people) {
        return entityManagerCustomRepository.create(people);
    }

    @PatchMapping("/update/{cid}")
    public People update(@PathVariable String cid, @RequestBody Map<String, String> inputs){
        return entityManagerCustomRepository.update(cid, inputs);
    }

    @PatchMapping("delete/{cid}")
    public String delete(@PathVariable String cid) {
        return entityManagerCustomRepository.delete(cid);
    }
}
