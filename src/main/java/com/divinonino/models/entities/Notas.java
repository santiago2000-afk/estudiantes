package com.divinonino.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notas")
public class Notas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnotas")
    private int idNotas;

    @ManyToOne
    @JoinColumn(name = "carnet_estudiante", referencedColumnName = "carnet")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idmateria", referencedColumnName = "idmaterias")
    private Materia materia;

    @ManyToOne
    @JoinColumn(name = "idperiodo", referencedColumnName = "idperiodo")
    private Periodo periodo;

    @Column(name = "tipo_evaluacion")
    private String tipoEvaluacion;

    @Column(name = "ponderacion")
    private Double ponderacion;

    @Column(name = "nota")
    private Double nota;
    
    @Column(name = "nota_ponderada")
    private Double notaPonderada;
    
    

	public Double getNotaPonderada() {
		return notaPonderada;
	}

	public void setNotaPonderada(Double notaPonderada) {
		this.notaPonderada = notaPonderada;
	}

	public int getIdNotas() {
		return idNotas;
	}

	public void setIdNotas(int idNotas) {
		this.idNotas = idNotas;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public String getTipoEvaluacion() {
		return tipoEvaluacion;
	}

	public void setTipoEvaluacion(String tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}

	public Double getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(Double ponderacion) {
		this.ponderacion = ponderacion;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

    
}