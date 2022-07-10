package com.catering.repository;

import com.catering.model.Buffet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {
    public List<Buffet> findByName(String name);

    @Modifying
    @Query(value = "delete from buffet_piatti p where p.piatti_id=:idP", nativeQuery = true)
    void deletePiattoforAll(@Param("idP")Long idP);

    @Modifying
    @Query(value = "delete from buffet_piatti p where p.buffet_id=:idB and p.piatti_id=:idP", nativeQuery = true)
    void deletePiattoforBuffet(@Param("idB")Long idB, @Param("idP")Long idP);

    @Modifying
    @Query(value = "insert into buffet_piatti (buffet_id, piatti_id) Values (:idB, :idP)", nativeQuery = true)
    void addPiattoforBuffet(@Param("idB")Long idB, @Param("idP")Long idP);

    @Modifying
    @Query(value = "update buffet set chef_id=:idC where id=:idB",nativeQuery = true)
    void setChefBuffet(@Param("idC")Long idC, @Param("idB") Long idB);

    @Modifying
    @Query("update Buffet p set p.chef=:idC where p.chef=:idC2")
    void setAllChefBuffet(@Param("idC")Long idC, @Param("idC2") Long idC2);

    @Modifying
    @Query(value = "update buffet set chef_id=null where chef_id=:idC",nativeQuery = true)
    void delAllChefBuffet(@Param("idC")Long idC);

    @Modifying
    @Query(value = "update buffet set chef_id=null where id=:idB",nativeQuery = true)
    void delChefBuffet(@Param("idB")Long idB);

    @Modifying
    @Query("update Buffet b set b.name=:nome, b.description=:descrizione where b.id=:id")
    public void update(@Param("id") Long id, @Param("nome") String nome, @Param("descrizione") String descrizione);
}
