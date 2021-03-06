package com.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

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
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @OrderBy("name desc")
    private Set<Buffet> buffets;


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

    public Set<Buffet> getBuffets() {
        return buffets;
    }

    public void setBuffets(Set<Buffet> buffets) {
        this.buffets = buffets;
    }

    public boolean addBuffet(Buffet buffet){
        if(this.buffets.isEmpty()){
            this.buffets = new HashSet<>();
            this.buffets.add(buffet);
            return true;
        }
        for (Buffet buffet1  : this.buffets) {
            if(buffet1.equals(buffet))
                return false;
        }
        this.buffets.add(buffet);
        return false;
    }

    public void deleteBuffet(Buffet buffet){
        if(this.buffets.isEmpty()){
            return;
        }
        if(this.existBuffet(buffet))
            this.buffets.remove(buffet);
    }

    public boolean existBuffet(Buffet buffet){
        for (Buffet buffet1  : this.buffets) {
            if(buffet1.equals(buffet))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chef)) return false;
        Chef chef = (Chef) o;
        return getId() == chef.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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
