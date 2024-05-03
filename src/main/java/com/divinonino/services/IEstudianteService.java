package com.divinonino.services;

import java.util.List;

import com.divinonino.models.entities.Estudiante;

public interface IEstudianteService {

	 Estudiante save(Estudiante estudiante);
		
	 List<Estudiante> findAll();
	
	 Estudiante obtenerUltimoEstudiante();

	List<Estudiante> findByApellidos(String apellidos);

	Estudiante findByCarnet(String estudianteId);

	List<Estudiante> findEstudiantesByGrado(Integer idGrado);}
