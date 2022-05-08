package com.prior.newbie.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Data
@Entity
@Table(name = "people")
public class People {
    @Id
    @GenericGenerator(name = "sequence_cid", strategy = "com.prior.newbie.model.ImeiIdGenerator") // generate UUID at com.prior.newbie.model.ImeiIdGenerator
    @GeneratedValue(generator = "sequence_cid", strategy = GenerationType.AUTO)
    @Column(name = "cid")
    private String cid;

    @Column(name = "title")
    private String title;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "middle_name")
    private String middle_name;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    private String birth_date;

    @Column(name = "is_deleted")
    private String is_deleted;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "created_date")
    private String created_date;

    @Column(name = "updated_date")
    private String updated_date;

    @Column(name = "updated_by")
    private String updated_by;

    @Column(name = "update_date")
    private String update_date;

    @Column(name = "update_by")
    private String update_by;
}

