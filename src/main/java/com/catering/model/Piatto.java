package com.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn(name = "buffet_id")
    private Buffet buffet;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @OrderBy("name desc")
    private List<Ingrediente> ingredienti;

    public Piatto(){}

    public Piatto(String name, String description, Buffet buffet, List<Ingrediente> ingredienti){
        this.name = name;
        this.description = description;
        this.buffet = buffet;
        this.ingredienti = new ArrayList<>(ingredienti);
    }

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

    public Buffet getBuffet() {
        return buffet;
    }

    public void setBuffet(Buffet buffet) {
        this.buffet = buffet;
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
        return getId() == piatto.getId() && getName().equals(piatto.getName()) && Objects.equals(getDescription(), piatto.getDescription()) && getBuffet().equals(piatto.getBuffet()) && getIngredienti().equals(piatto.getIngredienti());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getBuffet(), getIngredienti());
    }

    @Override
    public String toString() {
        return "Piatto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", buffet=" + buffet +
                ", ingredienti=" + ingredienti +
                '}';
    }
}
