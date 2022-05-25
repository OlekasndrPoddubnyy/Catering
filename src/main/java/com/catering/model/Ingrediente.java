package com.catering.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @NotBlank
    @Column(nullable = false)
    private String origin;

    /* Le strategie di cascade e fetch sono adottate in baese al fatto che un ingrediente
    * è fa parte di un piatto perciò di sicuro dovrò saperne l'appartenenza*/
    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "piatto_id")
    private Piatto piatto;

    public Ingrediente(){}

    public Ingrediente(String name, String description, String origin, Piatto piatto){
        this.name =name;
        this.description = description;
        this.origin = origin;
        this.piatto = piatto;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Piatto getPiatto() {
        return piatto;
    }

    public void setPiatto(Piatto piatto) {
        this.piatto = piatto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingrediente)) return false;
        Ingrediente that = (Ingrediente) o;
        return getId() == that.getId() && getName().equals(that.getName()) && getDescription().equals(that.getDescription()) && getOrigin().equals(that.getOrigin()) && getPiatto().equals(that.getPiatto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getOrigin(), getPiatto());
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", origin='" + origin + '\'' +
                ", piatto=" + piatto +
                '}';
    }
}
