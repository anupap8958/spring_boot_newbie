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

@Repository
@Slf4j
public class JdbcRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<People> findAll() {
        String sql = "SELECT * FROM PEOPLE";
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<People>(People.class));
    }

    public List<People> getPeopleById(String cid) {
        String sql = "SELECT * FROM PEOPLE WHERE cid = ?";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("cid", cid);
        log.info(sql);
        return jdbcTemplate.query(sql, new Object[]{cid}, new BeanPropertyRowMapper<People>(People.class));
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
            sql.append(" INSERT INTO PEOPLE (CID,TITLE, FIRSTNAME, LASTNAME, MIDDLE_NAME, MOBILE, GENDER, BIRTH_DATE, IS_DELETED, CREATED_BY, CREATED_DATE, UPDATED_DATE, UPDATED_BY, UPDATE_DATE, UPDATE_BY)");
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

    public int update(String cid, People user) {
        StringBuilder sql = new StringBuilder();
        int result = 0;

        sql.append(" UPDATE PEOPLE SET TITLE = ?, FIRSTNAME = ?, LASTNAME = ?, MIDDLE_NAME = ?, MOBILE = ?, GENDER = ?, BIRTH_DATE = ?, UPDATED_DATE = ?, UPDATED_BY = ?, UPDATE_DATE = ?, UPDATE_BY = ?     \r\n");
        sql.append(" WHERE CID = ?   \r\n");

        if (log.isInfoEnabled()) {
            log.info("function : Update Successfully");
            log.info("sql: {}", sql.toString());
        }

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
