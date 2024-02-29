package com.cibertec.assessment.controller;

import com.cibertec.assessment.beans.PolygonBean;
import com.cibertec.assessment.model.Polygon;
import com.cibertec.assessment.service.PolygonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polygons")
public class PolygonController {

    @Autowired
    private PolygonService polygonService;

    @PostMapping
    public ResponseEntity<Polygon> createPolygon(@RequestBody Polygon polygon) {
        // Llamar al servicio para crear el polígono
        polygonService.create(polygon);
        return new ResponseEntity<>(polygon, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<PolygonBean>> getAllPolygons() {
        // Llamar al servicio para obtener todos los polígonos
        List<PolygonBean> polygons = polygonService.list();
        return new ResponseEntity<>(polygons, HttpStatus.OK);
    }

    //
}
