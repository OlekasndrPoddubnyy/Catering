package com.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
public class Buffet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String description;

    /* Uso la strategia di cascade solo persist in quanto potrei crearli simltanemente,
    *  mentre la strategia di fetch è EAGER, perché dobbiamo sapere lo chef di ogni buffet */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "chef_id")
    private Chef chef;

    /* Uso la strategia di cascade ALL perché tutti i piatti dipendono dal buffet,
    * mentre la strategia di fetch è EAGER, perché ci interessa sempre sapere i piatti
    * che compongono un buffet */
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "piatto_id")
    @OrderBy("name desc")
    private List<Piatto> piatti;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("nome desc")
    private List<User> users;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public List<Piatto> getPiatti() {
        return piatti;
    }

    public void setPiatti(List<Piatto> piatti) {
        this.piatti = piatti;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Buffet)) return false;
        Buffet buffet = (Buffet) o;
        return getId() == buffet.getId() && getName().equals(buffet.getName()) && Objects.equals(getDescription(), buffet.getDescription()) && Objects.equals(getChef(), buffet.getChef()) && Objects.equals(getPiatti(), buffet.getPiatti()) && Objects.equals(getUsers(), buffet.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getChef(), getPiatti(), getUsers());
    }

    @Override
    public String toString() {
        return "Buffet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", chef=" + chef +
                ", piatti=" + piatti +
                ", users=" + users +
                '}';
    }
}
