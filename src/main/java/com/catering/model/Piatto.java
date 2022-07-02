package com.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
public class Piatto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String description;


    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "piatto_id")
    @OrderBy("name desc")
    private List<Ingrediente> ingredienti;


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


    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piatto)) return false;
        Piatto piatto = (Piatto) o;
        return getId() == piatto.getId() && getName().equals(piatto.getName()) && Objects.equals(getDescription(), piatto.getDescription()) && getIngredienti().equals(piatto.getIngredienti());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getIngredienti());
    }

    @Override
    public String toString() {
        return "Piatto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredienti=" + ingredienti +
                '}';
    }
}
