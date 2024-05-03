package com.divinonino.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divinonino.models.dao.INotasDao;
import com.divinonino.models.dao.NotasPeriodoDTO;
import com.divinonino.models.entities.Notas;

@Service
public class NotasServiceImpl implements INotasService {

    @Autowired
    private INotasDao notasDao;

    
    @Override
    public List<Notas> findByEstudianteCarnetAndIdperiodo(String carnetEstudiante, int idPeriodo) {
        return notasDao.findByEstudianteCarnetAndPeriodoIdPeriodo(carnetEstudiante, idPeriodo);
    }
    
    public List<NotasPeriodoDTO> calcularNotasPeriodo(String carnetEstudiante, int idPeriodo) {
        List<Notas> notas = findByEstudianteCarnetAndIdperiodo(carnetEstudiante, idPeriodo);
        Map<Integer, NotasPeriodoDTO> mapNotasPeriodoDTO = new HashMap<>();

        for (Notas nota : notas) {
            int idMateria = nota.getMateria().getIdMaterias(); // Obtener el id de la materia
            if (!mapNotasPeriodoDTO.containsKey(idMateria)) {
                mapNotasPeriodoDTO.put(idMateria, new NotasPeriodoDTO());
            }
            NotasPeriodoDTO notasPeriodoDTO = mapNotasPeriodoDTO.get(idMateria);
            Double notaPonderada = nota.getNota(); // Obtener la nota ponderada directamente
            switch (nota.getTipoEvaluacion()) {
                case "Prom Guias":
                    notasPeriodoDTO.setNotaGuias(notaPonderada);
                    break;
                case "Laboratorio":
                    notasPeriodoDTO.setNotaLaboratorio(notaPonderada);
                    break;
                case "Evaluado":
                    notasPeriodoDTO.setNotaEvaluado(notaPonderada);
                    break;
                default:
                    break;
            }
            Double notaGuias = notasPeriodoDTO.getNotaGuias() != null ? notasPeriodoDTO.getNotaGuias() : 0.0;
            Double notaEvaluado = notasPeriodoDTO.getNotaEvaluado() != null ? notasPeriodoDTO.getNotaEvaluado() : 0.0;
            notasPeriodoDTO.setPromedioPeriodoMateria((notaGuias*0.6) + (notaEvaluado*0.4));
        }

        return new ArrayList<>(mapNotasPeriodoDTO.values());
    }
}