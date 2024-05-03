package com.divinonino.services;

import java.util.List;

import com.divinonino.models.dao.NotasPeriodoDTO;
import com.divinonino.models.entities.Notas;

public interface INotasService {

    List<Notas> findByEstudianteCarnetAndIdperiodo(String carnetEstudiante, int idPeriodo);

    List<NotasPeriodoDTO> calcularNotasPeriodo(String carnetEstudiante, int idPeriodo);

}
