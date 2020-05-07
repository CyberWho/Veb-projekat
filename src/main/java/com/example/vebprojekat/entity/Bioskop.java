package com.example.vebprojekat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bioskop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
