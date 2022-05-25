package com.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Piatto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "buffet_id")
    private Buffet buffet;
}
