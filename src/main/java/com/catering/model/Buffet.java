package com.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "chef_id")
    private Chef chef;

    /* Uso la strategia di cascade ALL perché tutti i piatti dipendono dal buffet,
    * mentre la strategia di fetch è EAGER, perché ci interessa sempre sapere i piatti
    * che compongono un buffet */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
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

    public boolean addPiatto(Piatto piatto){
        if(this.piatti.isEmpty()){
            this.piatti = new ArrayList<>();
            this.piatti.add(piatto);
            return true;
        }
        for (Piatto piatto1  : this.piatti) {
            if(piatto1.equals(piatto))
                return false;
        }
        this.piatti.add(piatto);
        return false;
    }

    public void deletePiatto(Piatto piatto){
        if(this.piatti.isEmpty()){
            return;
        }
        if(this.existPiatto(piatto))
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
