package com.catering.repository;

import com.catering.model.Chef;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChefRepository extends CrudRepository<Chef, Long> {
    public List<Chef> findByName(String name);

    @Modifying
    @Query(value = "delete from chef_buffets p where p.buffets_id=:idB", nativeQuery = true)
    void deleteBuffetforAll(@Param("idB")Long idB);

    @Modifying
    @Query(value = "delete from chef_buffets  where chef_id=:idC", nativeQuery = true)
    void deleteBuffetforChef(@Param("idC")Long idC);
    @Modifying
    @Query(value = "delete from chef_buffets  where chef_id=:idC and buffets_id=:idB", nativeQuery = true)
    void deleteBuffetforChef2(@Param("idC")Long idC, @Param("idB")Long idB);

    @Modifying
    @Query(value = "insert into chef_buffets (chef_id, buffets_id) Values (:idC, :idB)", nativeQuery = true)
    void addBuffetforChef(@Param("idC")Long idC, @Param("idB")Long idB);

    @Modifying
    @Query("update Chef c set c.name=:nome, c.lastName=:cognome, c.nationality=:nazionalita where c.id=:id")
    public void update(@Param("id") Long id, @Param("nome") String nome, @Param("cognome") String cognome,
                       @Param("nazionalita") String nazionalita);
}
