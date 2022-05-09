package com.prior.newbie.service;

import com.prior.newbie.entities.People;
import com.prior.newbie.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;

    public Optional<People> findById(String cid) {
        return peopleRepository.findById(cid);
    }

    public People create(People people) {
        try {
            People user = people;
            user.setCreated_date(new Date().toString());
            user.setUpdate_date(new Date().toString());

            peopleRepository.save(user);
            return user;
        } catch (Exception ex) {
            System.err.println("Failed, Please try again");
            throw ex;
        }
    }


    public String deleteUser(String cid) { // N -> Y
        People user = peopleRepository.getById(cid);
        user.setIs_deleted("Y");

        peopleRepository.save(user);
        return "Delete Successfully";
    }

    public String update(String cid, Map<String, String> inputs) {
        People user = peopleRepository.getById(cid);
        user.setTitle(inputs.get("title"));
        user.setFirstname(inputs.get("firstname"));
        user.setLastname(inputs.get("lastname"));
        user.setMiddle_name(inputs.get("middle_name"));
        user.setMobile(inputs.get("mobile"));
        user.setGender(inputs.get("gender"));
        user.setBirth_date(inputs.get("birth_date"));
        user.setUpdated_date(new Date().toString());
        user.setUpdated_by(inputs.get("updated_by"));
        user.setUpdate_date(new Date().toString());
        user.setUpdate_by(inputs.get("update_by"));

        System.out.println(user);

        peopleRepository.save(user);
        return "Update Successfully";
    }

    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    public String delete(String cid) {
        peopleRepository.deleteById(cid);
        return "deleted successfully";
    }
}
