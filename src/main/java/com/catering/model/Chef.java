package com.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    private String nationality;

    /* La strategia di cascade è all infatti buffet dipendono interamente dallo chef, questo implica che
    * non possiamo cambiare lo scef ad un dato buffet, inoltre fetch è EAGER perché ci interessa  sempre
    * avere diponibili i buffet di un certo chef*/
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @OrderBy("name desc")
    private List<Buffet> buffets;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Buffet> getBuffets() {
        return buffets;
    }

    public void setBuffets(List<Buffet> buffets) {
        this.buffets = buffets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chef)) return false;
        Chef chef = (Chef) o;
        return getId() == chef.getId() && getName().equals(chef.getName()) && getLastName().equals(chef.getLastName()) && getNationality().equals(chef.getNationality()) && Objects.equals(getBuffets(), chef.getBuffets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastName(), getNationality(), getBuffets());
    }

    @Override
    public String toString() {
        return "Chef{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
