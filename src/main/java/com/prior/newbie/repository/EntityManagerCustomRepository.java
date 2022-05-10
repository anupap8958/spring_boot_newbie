package com.prior.newbie.repository;

import com.prior.newbie.entities.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class EntityManagerCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<People> findAll() {
        // solution 1
        String sql = "SELECT p FROM People p";

        TypedQuery<People> query = entityManager.createQuery(sql, People.class);

        return query.getResultList();

        // solution 2
        /*entityManager.clear();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT cid, title, firstname, lastname, middle_name, mobile, gender, birth_date, is_deleted, created_by, created_date, updated_date, updated_by, update_date, update_by ");
        sql.append("FROM people");

        Session session = entityManager.unwrap(Session.class);
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());

        sqlQuery.addScalar("cid", StringType.INSTANCE);
        sqlQuery.addScalar("title", StringType.INSTANCE);
        sqlQuery.addScalar("firstname", StringType.INSTANCE);
        sqlQuery.addScalar("lastname", StringType.INSTANCE);
        sqlQuery.addScalar("middle_name", StringType.INSTANCE);
        sqlQuery.addScalar("mobile", StringType.INSTANCE);
        sqlQuery.addScalar("gender", StringType.INSTANCE);
        sqlQuery.addScalar("birth_date", StringType.INSTANCE);
        sqlQuery.addScalar("is_deleted", StringType.INSTANCE);
        sqlQuery.addScalar("created_by", StringType.INSTANCE);
        sqlQuery.addScalar("created_date", StringType.INSTANCE);
        sqlQuery.addScalar("updated_date", StringType.INSTANCE);
        sqlQuery.addScalar("updated_by", StringType.INSTANCE);
        sqlQuery.addScalar("update_date", StringType.INSTANCE);
        sqlQuery.addScalar("update_by", StringType.INSTANCE);

        sqlQuery.setResultTransformer(Transformers.aliasToBean(People.class));

        List<People> list = sqlQuery.list();

        entityManager.close();

        return list;*/
    }

    public People findById(String cid) {
        return entityManager.find(People.class, cid);
    }

    @Transactional
    public String create(People people) {
        People user = people;

        user.setCreated_date(new Date().toString());
        user.setUpdate_date(new Date().toString());

        entityManager.persist(user);
        return "Create Successfully";
    }

    @Transactional
    public People update(String cid, Map<String, String> inputs) {
        People user = entityManager.find(People.class, cid);
        user.setTitle(inputs.containsKey("title") ? inputs.get("title") : user.getTitle());
        user.setFirstname(inputs.containsKey("firstname") ? inputs.get("firstname") : user.getFirstname());
        user.setLastname(inputs.containsKey("lastname") ? inputs.get("lastname") : user.getLastname());
        user.setMiddle_name(inputs.containsKey("middle_name") ? inputs.get("middle_name") : user.getMiddle_name());
        user.setMobile(inputs.containsKey("mobile") ? inputs.get("mobile") : user.getMobile());
        user.setGender(inputs.containsKey("gender") ? inputs.get("gender") : user.getGender());
        user.setBirth_date(inputs.containsKey("birth_date") ? inputs.get("birth_date") : user.getBirth_date());
        user.setUpdated_date(new Date().toString());
        user.setUpdated_by(inputs.containsKey("updated_by") ? inputs.get("updated_by") : user.getUpdated_by());
        user.setUpdate_date(new Date().toString());
        user.setUpdate_by(inputs.containsKey("update_by") ? inputs.get("update_by") : user.getUpdate_by());

        return entityManager.merge(user);
    }

    @Transactional
    public String delete(String cid) {
        People user = entityManager.find(People.class, cid);
        if (user != null) {
            user.setIs_deleted("Y"); // N -> Y
            entityManager.merge(user);
            return "Delete cid = " + cid + " Successfully";
        } else {
            return "Failed, Please try again";
        }
    }
}

