package com.catering.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users") // user in postgres è una parola riservata
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    //per gli utenti do la possibilità di salvare
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @OrderBy("name desc")
    private Set<Buffet> bookmarks;

    public Set<Buffet> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Set<Buffet> bookmarks) {
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

    public boolean isBookmarked(Buffet buffet){
        return this.bookmarks.contains(buffet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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

