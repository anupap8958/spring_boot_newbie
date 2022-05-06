package com.prior.newbie.repository;

import com.prior.newbie.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, String> {

}
