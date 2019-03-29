package com.github.uuidcode.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
@Entity
public class Academy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long academyId;
    private String name;
    private String address;
}