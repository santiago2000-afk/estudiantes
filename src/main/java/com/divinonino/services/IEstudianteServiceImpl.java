package com.divinonino.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divinonino.models.dao.IEstudianteDao;
import com.divinonino.models.entities.Estudiante;

@Service
public class IEstudianteServiceImpl implements IEstudianteService{
	
	@Autowired
	private IEstudianteDao estudianteDao;
	
	@Override
	public Estudiante save(Estudiante estudiante) {
		return estudianteDao.save(estudiante);
	}


	@Override
	public Estudiante findByCarnet(String estudianteId) {
	    return estudianteDao.findByCarnet(estudianteId);
	}

	    @Override
	    public List<Estudiante> findAll() {
	        return (List<Estudiante>) estudianteDao.findAll();
	    }
	    
	    @Override
	    public Estudiante obtenerUltimoEstudiante() {
	        Optional<Estudiante> optionalEstudiante = estudianteDao.findFirstByOrderByCarnetDesc();
	        if (optionalEstudiante.isPresent()) {
	            return optionalEstudiante.get();
	        } else {
	            // Si no hay estudiantes, devuelve un nuevo estudiante con ID predeterminado "0001"
	            Estudiante estudiantePredeterminado = new Estudiante();
	            estudiantePredeterminado.setCarnet("0001");
	            return estudiantePredeterminado;
	        }
	    }

	    @Override
	    public List<Estudiante> findByApellidos(String apellidos) {
	        return estudianteDao.findByApellidos(apellidos);
	    }


	    @Override
	    public List<Estudiante> findEstudiantesByGrado(Integer idGrado) {
	        return estudianteDao.findByidGrado(idGrado);
	    }

}
