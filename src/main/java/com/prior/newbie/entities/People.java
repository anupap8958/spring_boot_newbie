package com.prior.newbie.entities;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cid;
    private String title;
    private String firstname;
    private String lastname;
    private String middle_name;
    private String mobile;
    private String gender;
    private String birth_date;
    private char is_deleted;
    private String created_by;
    private String created_date;
    private String update_date;
}

