package com.divinonino.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.divinonino.models.entities.Notas;

public interface INotasDao extends CrudRepository<Notas, Integer> {

    List<Notas> findByEstudianteCarnetAndPeriodoIdPeriodo(String carnetEstudiante, int idPeriodo);

}
