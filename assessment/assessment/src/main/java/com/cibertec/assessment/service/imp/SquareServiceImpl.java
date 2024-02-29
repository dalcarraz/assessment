package com.cibertec.assessment.service.imp;

import com.cibertec.assessment.beans.PolygonBean;
import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.repo.SquareRepo;
import com.cibertec.assessment.service.PolygonService;
import com.cibertec.assessment.service.SquareService;
import com.cibertec.assessment.util.IntersectionChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class SquareServiceImpl implements SquareService {

	
    @Autowired
    private SquareRepo squareRepo;

    @Autowired
    private PolygonService polygonService;

    @Transactional
    @Override
    public Square create(Square square) {
        Square createdSquare = squareRepo.save(square);

        // Validar la intersección y actualizar los polígonos intersectados
        validateAndSetIntersectingPolygons(createdSquare);

        return createdSquare;
    }

    @Override
    public List<Square> list() {
        return squareRepo.findAll();
    }

    public void validateAndSetIntersectingPolygons(Square square) {
        List<PolygonBean> allPolygons = polygonService.list();
        List<Integer> intersectingPolygonIds = new ArrayList<>();

        // Lógica para validar la intersección con cada polígono
        for (PolygonBean polygon : allPolygons) {
            boolean isIntersecting = IntersectionChecker.isSquareIntersectingPolygon(square, polygon);
            if (isIntersecting) {
                intersectingPolygonIds.add(polygon.getId());
                System.out.println("Intersection detected with polygon ID: " + polygon.getId());
            }
        }

        // Otras impresiones de log para debugging
        System.out.println("Square ID: " + square.getId());
        System.out.println("Intersecting Polygon IDs: " + intersectingPolygonIds);

        // Actualizar el campo polygons del cuadrado
        square.setPolygons(convertListToFormattedString(intersectingPolygonIds));

        // Guardar el cuadrado actualizado
        squareRepo.save(square);
    }


    // Método para convertir una lista de enteros a un string con formato de array
    private String convertListToFormattedString(List<Integer> list) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i));
            if (i < list.size() - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}
