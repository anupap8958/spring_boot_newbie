package com.prior.newbie.controller;

import com.prior.newbie.entities.People;
import com.prior.newbie.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private PeopleService peopleService;

    @PostMapping(value = "/create")
    public People create(@RequestBody People inputs) throws Exception {
        //Saving data
        return peopleService.create(inputs);
    }

    @PatchMapping(value = "/delete/{cid}")
    public String deleteUser(@PathVariable String cid) {
        Optional<People> user = peopleService.findById(cid);
        if (!user.isPresent()) {
            return "error," + "User ID : " + cid + " not found";
        } else {
            //Delete user by id (N -> Y)
            return peopleService.deleteUser(cid);
        }
    }

    @PatchMapping(value = "/update/{cid}")
    public String update(@PathVariable String cid, @RequestBody Map<String,String> inputs) {
        Optional<People> user = peopleService.findById(cid);
        if (!user.isPresent()) {
            return "error," + "User ID : " + cid + " not found";
        } else {
            //Update Data
            return peopleService.update(cid, inputs);
        }
    }

    @GetMapping(value = "/findAll")
    public List<People> findAll(){
        return peopleService.findAll();
    }

    /*
    @DeleteMapping(value = "/delete/{cid}")
    public String delete(@PathVariable String cid) {
        Optional<People> user = peopleService.findById(cid);
        if (!user.isPresent()) {
            return "error," + "User ID : " + cid + " not found";
        } else {
            //Delete user by id
            return peopleService.delete(cid);
        }
    }
    */
}
