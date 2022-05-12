package com.prior.newbie.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "color")
public class Color {
    @Id
    @Column(name = "color_code")
    private String color_code;

    @Column(name = "color_name")
    private String color_name;

    @Column(name = "decimal_code")
    private String decimal_code;
}
