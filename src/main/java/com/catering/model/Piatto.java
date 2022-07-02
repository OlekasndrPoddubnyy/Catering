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


    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
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

    public boolean addIngrediente(Ingrediente ingrediente){
        if(this.ingredienti.isEmpty()){
            this.ingredienti = new ArrayList<>();
            this.ingredienti.add(ingrediente);
            return true;
        }
        for (Ingrediente ingrediente1  : this.ingredienti) {
            if(ingrediente1.equals(ingrediente))
               return false;
        }
        this.ingredienti.add(ingrediente);
        return false;
    }

    public void deleteIngrediente(Ingrediente ingrediente){
        if(this.ingredienti.isEmpty()){
            return;
        }
        if(this.existIngrediente(ingrediente))
            this.ingredienti.remove(ingrediente);
    }

    public boolean existIngrediente(Ingrediente ingrediente){
        for (Ingrediente ingrediente1  : this.ingredienti) {
            if(ingrediente1.equals(ingrediente))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piatto)) return false;
        Piatto piatto = (Piatto) o;
        return getId() == piatto.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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
