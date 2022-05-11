package com.prior.newbie.controller;

import com.prior.newbie.entities.Train;
import com.prior.newbie.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/restTemplate")
public class RestTemplateController {

    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping("/findAll")
    public ResponseEntity<String> getAllUser() {
        return restTemplateService.getAllUser();
    }

    @GetMapping("/findById/{id}")
    public Object getUserById(@PathVariable String id) {
        try {
            return restTemplateService.getUserById(id);
        } catch (Exception ex) {
            return "Not Found Id = " + id + ", Please try again";
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody Train inputs) {
        return restTemplateService.createUser(inputs);
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable String id, @RequestBody Map<String, Object> inputs) {
        try {
            return restTemplateService.updateUser(inputs, id);
        } catch (Exception ex) {
            return "Not Found Id = " + id + ", Please try again";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        try {
            return restTemplateService.delete(id);
        } catch (Exception ex) {
            return "Not Found Id = " + id + ", Please try again";
        }
    }
}
