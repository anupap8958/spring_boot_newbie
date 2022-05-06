package com.prior.newbie.repository;

import com.prior.newbie.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<People, String> {
//    Optional<People> selectID(String id);
}
