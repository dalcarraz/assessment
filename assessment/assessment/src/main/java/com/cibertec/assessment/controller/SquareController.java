package com.cibertec.assessment.controller;

import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.service.SquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/squares")
public class SquareController {

    @Autowired
    private SquareService squareService;

    @PostMapping
    public ResponseEntity<Square> createSquare(@RequestBody Square square) {
        Square createdSquare = squareService.create(square);

        // Validar la intersección y actualizar los polígonos intersectados
        squareService.validateAndSetIntersectingPolygons(createdSquare);

        return new ResponseEntity<>(createdSquare, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Square>> getAllSquares() {
        List<Square> squares = squareService.list();
        return new ResponseEntity<>(squares, HttpStatus.OK);
    }

}