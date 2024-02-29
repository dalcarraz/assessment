package com.cibertec.assessment.util;

import com.cibertec.assessment.beans.PolygonBean;
import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.model.Polygon;


public class IntersectionChecker {

    // Método para verificar la intersección entre un cuadrado y un polígono
	public static boolean isSquareIntersectingPolygon(Square square, PolygonBean polygon) {
	    int[] squareXPoints = parseArrayString(square.getXPoints());
	    int[] squareYPoints = parseArrayString(square.getYPoints());

	    Integer[] polygonXPoints = polygon.getXPoints();
	    Integer[] polygonYPoints = polygon.getYPoints();

	    // Verificar que ambos arreglos tengan al menos 1 punto
	    if (squareXPoints.length < 1 || polygonXPoints.length < 1) {
	        return false;
	    }
		return false;
	}

    // Método para convertir una cadena con formato de array a un arreglo de enteros
    private static int[] parseArrayString(String arrayString) {
        if (arrayString == null) {
            // Manejar el caso cuando arrayString es nulo
            return new int[0]; // O puedes lanzar una excepción o manejarlo de otra manera según tus necesidades.
        }

        String[] parts = arrayString.substring(1, arrayString.length() - 1).split(", ");
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i]);
        }
        return result;
    }
}
