package com.prior.newbie.repository;

import com.prior.newbie.entities.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class NamedParameterCustomRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<People> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM people");

        // Solution 1
        return namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(People.class));

        // Solution 2
        /*MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        List<People> listPeople = this.namedParameterJdbcTemplate.query(sql.toString(), mapSqlParameterSource, new RowMapper<People>() {
            @Override
            public People mapRow(ResultSet rs, int rowNum) throws SQLException {
                People user = new People();

                int col = 1;
                user.setCid(rs.getString(col++));
                user.setTitle(rs.getString(col++));
                user.setFirstname(rs.getString(col++));
                user.setLastname(rs.getString(col++));
                user.setMiddle_name(rs.getString(col++));
                user.setMobile(rs.getString(col++));
                user.setGender(rs.getString(col++));
                user.setBirth_date(rs.getString(col++));
                user.setIs_deleted(rs.getString(col++));
                user.setCreated_by(rs.getString(col++));
                user.setCreated_date(rs.getString(col++));
                user.setUpdated_date(rs.getString(col++));
                user.setUpdated_by(rs.getString(col++));
                user.setUpdate_date(rs.getString(col++));
                user.setUpdate_by(rs.getString(col++));

                return user;
            }
        });

        return listPeople;*/
    }

    public People findById(String cid) {
        String sql = "SELECT * FROM people WHERE cid = :cid";
        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("cid", cid), new BeanPropertyRowMapper<>(People.class));
    }

    public int create(People user) {
        StringBuilder sql = new StringBuilder();

        sql.append(" INSERT INTO PEOPLE (CID, TITLE, FIRSTNAME, LASTNAME, MIDDLE_NAME, MOBILE, GENDER, BIRTH_DATE, IS_DELETED, CREATED_BY, CREATED_DATE, UPDATED_DATE, UPDATED_BY, UPDATE_DATE, UPDATE_BY) ");
        sql.append(" VALUES(:cid, :title, :firstname, :lastname, :middle_name, :mobile, :gender, :birth_date, :is_deleted, :created_by, :created_date, :updated_date, :updated_by, :update_date, :update_by)   \r\n");

        SqlParameterSource namedParameter = new MapSqlParameterSource("cid", user.getCid()).addValue("title", user.getTitle()).addValue("firstname", user.getFirstname())
                .addValue("lastname", user.getLastname()).addValue("middle_name", user.getMiddle_name()).addValue("mobile", user.getMobile()).addValue("gender", user.getGender())
                .addValue("birth_date", user.getBirth_date()).addValue("is_deleted", user.getIs_deleted()).addValue("created_by", user.getCreated_by()).addValue("created_date", new Date().toString())
                .addValue("updated_date", user.getUpdated_date()).addValue("updated_by", user.getUpdated_by()).addValue("update_date", new Date().toString()).addValue("update_by", user.getUpdate_by());

        return namedParameterJdbcTemplate.update(sql.toString(), namedParameter);
    }

    public int update(String cid, Map<String, String> inputs) {

        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE PEOPLE SET TITLE = :title, FIRSTNAME = :firstname, LASTNAME = :lastname, MIDDLE_NAME = :middle_name, MOBILE = :mobile, GENDER = :gender, BIRTH_DATE = :birth_date, UPDATED_DATE = :updated_date,UPDATED_BY = :updated_by, UPDATE_DATE = :update_date, UPDATE_BY = :update_by ");
        sql.append("WHERE cid = :cid");

        People user = namedParameterJdbcTemplate.queryForObject("SELECT * FROM people WHERE cid = :cid", new MapSqlParameterSource("cid", cid), new BeanPropertyRowMapper<>(People.class));
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

        SqlParameterSource namedParameter = new MapSqlParameterSource("cid", user.getCid()).addValue("title", user.getTitle()).addValue("firstname", user.getFirstname())
                .addValue("lastname", user.getLastname()).addValue("middle_name", user.getMiddle_name()).addValue("mobile", user.getMobile()).addValue("gender", user.getGender())
                .addValue("birth_date", user.getBirth_date()).addValue("updated_date", user.getUpdated_date()).addValue("updated_by", user.getUpdated_by())
                .addValue("update_date", new Date().toString()).addValue("update_by", user.getUpdate_by());

        return namedParameterJdbcTemplate.update(sql.toString(), namedParameter);
    }

    public int delete(String cid) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE PEOPLE SET IS_DELETED = :is_deleted WHERE cid = :cid");
        int result = 0;

        result = namedParameterJdbcTemplate.update(sql.toString(), new MapSqlParameterSource("is_deleted","Y").addValue("cid", cid));

        return result;
    }
}
