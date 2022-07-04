package com.catering.repository;

import com.catering.model.Piatto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {
    public List<Piatto> findByName(String name);

    @Modifying
    @Query(value = "delete from piatto_ingredienti p where p.ingredienti_id=:idI", nativeQuery = true)
    void deleteIngredienteforAll(@Param("idI")Long idI);

    @Modifying
    @Query(value = "delete from piatto_ingredienti p where p.piatto_id=:idP and p.ingredienti_id=:idI", nativeQuery = true)
    void deleteIngredienteforPiatto(@Param("idP")Long idP, @Param("idI")Long idI);

    @Modifying
    @Query(value = "insert into piatto_ingredienti (piatto_id, ingredienti_id) Values (:idP, :idI)", nativeQuery = true)
    void addIngredienteforPiatto(@Param("idP")Long idP, @Param("idI")Long idI);
}
