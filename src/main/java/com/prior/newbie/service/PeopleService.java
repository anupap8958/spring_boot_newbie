package com.prior.newbie.service;

import com.prior.newbie.entities.People;
import com.prior.newbie.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;
    public People showWithPath(String cid) {
        Optional<People> user = peopleRepository.findById(cid);
        System.out.println("############### Find User By ID (In Console)###############");
        System.out.println(user.get());
        return user.get();
    }
}
