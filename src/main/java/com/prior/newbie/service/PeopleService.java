package com.prior.newbie.service;

import com.prior.newbie.entities.People;
import com.prior.newbie.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class PeopleService implements PeopleRepository {
    @Autowired
    private PeopleRepository peopleRepository;


    @Override
    public List<People> findAll() {
        return null;
    }

    @Override
    public List<People> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<People> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<People> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(People entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends People> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends People> S save(S entity) {
        return null;
    }

    @Override
    public <S extends People> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<People> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends People> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends People> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<People> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public People getOne(Integer integer) {
        return null;
    }

    @Override
    public People getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends People> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends People> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends People> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends People> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends People> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends People> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends People, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
