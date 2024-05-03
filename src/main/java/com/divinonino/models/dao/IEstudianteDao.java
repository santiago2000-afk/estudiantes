package com.divinonino.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.divinonino.models.entities.Estudiante;

public interface IEstudianteDao extends CrudRepository<Estudiante, String>{

    Optional<Estudiante> findFirstByOrderByCarnetDesc();

	List<Estudiante> findByApellidos(String apellidos);

	Estudiante findByCarnet(String carnet);

	@Query("SELECT e FROM Estudiante e WHERE e.idGrado = :idGrado")
    List<Estudiante> findByidGrado(@Param("idGrado") Integer idGrado);

}
