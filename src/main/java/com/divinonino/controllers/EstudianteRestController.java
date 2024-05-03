package com.divinonino.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.divinonino.dto.EstudianteDTO;
import com.divinonino.models.entities.Estudiante;
import com.divinonino.services.IEstudianteService;


@RestController
@RequestMapping("/estudiantes")
@CrossOrigin(origins = "http://localhost:3000")
public class EstudianteRestController {
	
	@Autowired
	private IEstudianteService estudianteService;
	
	
	@GetMapping("/grado/{idGrado}")
	public List<EstudianteDTO> getEstudiantesByGrado(@PathVariable("idGrado") Integer idGrado) {
	    List<Estudiante> estudiantes = estudianteService.findEstudiantesByGrado(idGrado);
	    List<EstudianteDTO> estudiantesDTO = new ArrayList<>();

	    for (Estudiante estudiante : estudiantes) {
	        EstudianteDTO estudianteDTO = new EstudianteDTO();
	        estudianteDTO.setNombres(estudiante.getNombres());
	        estudianteDTO.setApellidos(estudiante.getApellidos());
	        estudianteDTO.setCarnet(estudiante.getCarnet());
	        estudiantesDTO.add(estudianteDTO);
	    }

	    return estudiantesDTO;
	}
	
	 @GetMapping
	    public ResponseEntity<List<Estudiante>> obtenerEstudiantes() {
	        List<Estudiante> estudiantes = estudianteService.findAll();
	        return ResponseEntity.ok(estudiantes);
	    }

		 @GetMapping("/{carnet}")
		 public ResponseEntity<Estudiante> obtenerEstudiantePorId(@PathVariable String carnet) {
		     Estudiante estudiante = estudianteService.findByCarnet(carnet);
		     if (estudiante != null) {
		         return ResponseEntity.ok(estudiante);
		     } else {
		         return ResponseEntity.notFound().build();
		     }
		 }
	 
	    @PostMapping("/agregar1")
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Estudiante> agregarEstudiante(@RequestBody Estudiante estudiante) {
	    	String estudianteId = generarEstudianteId(estudiante);
	        estudiante.setCarnet(estudianteId);
	    	
	    	Estudiante nuevoEstudiante = estudianteService.save(estudiante);
	        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
	    }

	    @PutMapping("/{carnet}")
	    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable String carnet, @RequestBody Estudiante estudiante) {
	        Estudiante estudianteExistente = estudianteService.findByCarnet(carnet);
	        if (estudianteExistente != null) {
	            estudiante.setCarnet(carnet);
	            estudianteService.save(estudiante);
	            return ResponseEntity.ok(estudiante);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{carnet}")
	    public ResponseEntity<Void> eliminarEstudiante(@PathVariable String carnet) {
	    	Estudiante estudianteExistente = estudianteService.findByCarnet(carnet);
	        if (estudianteExistente != null) {
	            estudianteExistente.setActivo(0); 
	            estudianteService.save(estudianteExistente); 
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	    @GetMapping("/apellido/{apellidos}")
	    public List<Estudiante> findEstudiantesByApellidos(@PathVariable("apellidos") String apellidos) {
	        return estudianteService.findByApellidos(apellidos);
	    }
	    
	    
	    
	    
	    private String generarEstudianteId(Estudiante estudiante) {
	        char primeraLetraNombre = estudiante.getNombres().isEmpty() ? 'a' : estudiante.getNombres().charAt(0);
	        char primeraLetraApellido = estudiante.getApellidos().isEmpty() ? 'b' : estudiante.getApellidos().charAt(0);

	        int year = LocalDate.now().getYear();  
	        int numeroIncremental = obtenerNumeroIncremental();

	        String estudianteId = String.format("%c%c%04d%04d", primeraLetraNombre, primeraLetraApellido, numeroIncremental, year);
	        return estudianteId;
	    }

	    private int obtenerNumeroIncremental() {
	        Estudiante ultimoEstudiante = estudianteService.obtenerUltimoEstudiante();
	        if (ultimoEstudiante == null) {
	            return 1;
	        }
	        String ultimoEstudianteId = ultimoEstudiante.getCarnet();
	        
	        int ultimoNumeroIncremental = 0;
	        try {
	            // Ajusta las posiciones de inicio y fin para extraer correctamente el número incremental
	            ultimoNumeroIncremental = Integer.parseInt(ultimoEstudianteId.substring(2, 6));
	        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
	            // Manejo de excepciones
	        }
	        int siguienteNumeroIncremental = ultimoNumeroIncremental + 1;  

	        return siguienteNumeroIncremental;
	    }


	
}
