package com.catering.repository;

import com.catering.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    @Modifying
    @Query(value = "insert into users_bookmarks (user_id, bookmarks_id) Values (:idU, :idB)", nativeQuery = true)
    void aggiungiBuffetUser(@Param("idU") Long idU, @Param("idB") Long id);

    @Modifying
    @Query(value = "delete from users_bookmarks p where p.bookmarks_id=:idB", nativeQuery = true)
    void cancellaBuffetUser(@Param("idB") Long id);
}