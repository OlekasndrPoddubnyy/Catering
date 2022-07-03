package com.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

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
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER )
    private Chef chef;

    /* Uso la strategia di cascade ALL perché tutti i piatti dipendono dal buffet,
    * mentre la strategia di fetch è EAGER, perché ci interessa sempre sapere i piatti
    * che compongono un buffet */
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER )
    @OrderBy("name desc")
    private Set<Piatto> piatti;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("nome desc")
    private Set<User> users;


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

    public Set<Piatto> getPiatti() {
        return piatti;
    }

    public void setPiatti(Set<Piatto> piatti) {
        this.piatti = piatti;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addPiatto(Piatto piatto){
        if(this.piatti.isEmpty()){
            this.piatti = new HashSet<>();
            this.piatti.add(piatto);
            return ;
        }
        for (Piatto piatto1  : this.piatti) {
            if(piatto1.equals(piatto))
                return ;
        }
        this.piatti.add(piatto);
        return;
    }

    public void deletePiatto(Piatto piatto){
        if(this.piatti.isEmpty()){
            return;
        }
        while(this.existPiatto(piatto))
            this.piatti.remove(piatto);
    }

    public boolean existPiatto(Piatto piatto){
        for (Piatto piatto1  : this.piatti) {
            if(piatto1.equals(piatto))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Buffet)) return false;
        Buffet buffet = (Buffet) o;
        return getId() == buffet.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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
