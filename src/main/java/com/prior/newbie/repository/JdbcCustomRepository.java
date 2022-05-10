package com.prior.newbie.repository;

import com.prior.newbie.entities.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class JdbcCustomRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<People> findAll() {
        String sql = "SELECT * FROM PEOPLE";
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<People>(People.class));
    }

    public People getPeopleById(String cid) {
        String sql = "SELECT * FROM PEOPLE WHERE cid = ?";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("cid", cid);
        log.info(sql);
        return (People) jdbcTemplate.queryForObject(sql, new Object[]{cid}, new BeanPropertyRowMapper(People.class));
    }

    public List<People> getPeopleIs_deleated() {
        String sql = "SELECT * FROM PEOPLE WHERE is_deleted = 'Y'";
        log.info(sql);
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<People>(People.class));
    }

    public int create(People user) {
        StringBuilder sql;
        int result = 0;
        try {
            sql = new StringBuilder();
            sql.append(" INSERT INTO PEOPLE (CID, TITLE, FIRSTNAME, LASTNAME, MIDDLE_NAME, MOBILE, GENDER, BIRTH_DATE, IS_DELETED, CREATED_BY, CREATED_DATE, UPDATED_DATE, UPDATED_BY, UPDATE_DATE, UPDATE_BY)");
            sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)   \r\n");

            if (log.isInfoEnabled()) {
                log.info("function : insertSlotAppointment");
                log.info("sql: {}", sql);
            }

            // Solution 1
            result = jdbcTemplate.update(sql.toString(), new Object[]{
                    user.getCid(), user.getTitle(), user.getFirstname(), user.getLastname(), user.getMiddle_name(), user.getMobile(),
                    user.getGender(), user.getBirth_date(), user.getIs_deleted(), user.getCreated_by(), new Date().toString(),
                    user.getUpdated_date(), user.getUpdated_by(), new Date().toString(), user.getUpdate_by()
            });

            // Solution 2
            /*
            result = jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    int i = 1;
                    ps.setString(i++, user.getCid());
                    ps.setString(i++, user.getTitle());
                    ps.setString(i++, user.getFirstname());
                    ps.setString(i++, user.getLastname());
                    ps.setString(i++, user.getMiddle_name());
                    ps.setString(i++, user.getMobile());
                    ps.setString(i++, user.getGender());
                    ps.setString(i++, user.getBirth_date());
                    ps.setString(i++, user.getIs_deleted());
                    ps.setString(i++, user.getCreated_by());
                    ps.setString(i++, new Date().toString());
                    ps.setString(i++, user.getUpdated_date());
                    ps.setString(i++, user.getUpdated_by());
                    ps.setString(i++, new Date().toString());
                    ps.setString(i++, user.getUpdate_by());
                }
            });
             */

        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    public int update(String cid, Map<String, String> inputs) {
        StringBuilder sql = new StringBuilder();
        int result = 0;

        sql.append(" UPDATE PEOPLE SET TITLE = ?, FIRSTNAME = ?, LASTNAME = ?, MIDDLE_NAME = ?, MOBILE = ?, GENDER = ?, BIRTH_DATE = ?, UPDATED_DATE = ?, UPDATED_BY = ?, UPDATE_DATE = ?, UPDATE_BY = ?     \r\n");
        sql.append(" WHERE CID = ?   \r\n");

        if (log.isInfoEnabled()) {
            log.info("function : Update Successfully");
            log.info("sql: {}", sql.toString());
        }

        // update value
        People user = (People) jdbcTemplate.queryForObject("SELECT * FROM PEOPLE WHERE cid = ?", new Object[]{cid}, new BeanPropertyRowMapper(People.class));
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

        // update to database
        result = jdbcTemplate.update(sql.toString(), new Object[]{
                user.getTitle(), user.getFirstname(), user.getLastname(), user.getMiddle_name(), user.getMobile(),
                user.getGender(), user.getBirth_date(), new Date().toString(), user.getUpdated_by(), new Date().toString(), user.getUpdate_by(), cid
        });

        return result;
    }

    public int delete(String cid) {
        String sql = "UPDATE PEOPLE SET IS_DELETED = ? WHERE cid = ?";
        return jdbcTemplate.update(sql, "Y", cid);
    }
}