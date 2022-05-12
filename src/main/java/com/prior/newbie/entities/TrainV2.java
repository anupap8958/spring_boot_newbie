package com.prior.newbie.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class TrainV2 {
    private String createdAt;
    private String name;
    private String avatar;
    private int rank;
    private String colorCode;
    private String colorName;
    private String colorDecimal;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

}
