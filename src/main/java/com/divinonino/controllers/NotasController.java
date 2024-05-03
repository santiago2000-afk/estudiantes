package com.divinonino.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.divinonino.models.entities.Notas;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notases")
@CrossOrigin(origins = "http://localhost:3000")
public class NotasController {

    @GetMapping
    public List<Notas> obtenerNotas() {
        // Aquí va la lógica para obtener las notas (temporalmente, devolvemos una lista vacía)
        return new ArrayList<>();
    }
}
