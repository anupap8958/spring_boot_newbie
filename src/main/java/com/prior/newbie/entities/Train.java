package com.prior.newbie.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class Train {
    private String createdAt;
    private String name;
    private String avatar;
    private int rank;
    private String colorCode;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
}
