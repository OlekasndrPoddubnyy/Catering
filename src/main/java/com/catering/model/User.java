package com.catering.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users") // user in postgres è una parola riservata
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    //per gli utenti do la possibilità di salvare
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @OrderBy("name desc")
    private List<Buffet> bookmarks;

    public List<Buffet> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Buffet> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getNome(), user.getNome()) && Objects.equals(getBookmarks(), user.getBookmarks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getBookmarks());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", bookmark=" + bookmarks +
                '}';
    }
}

