package models;

import java.util.Comparator;

public class Coordinates<TC,TR,T> extends MySparceMatrix{

    private MySparceMatrix mySparceMatrix;
    private Comparator<TC> comparatorCols;
    private Comparator<TR> comparatorRows;

    public Coordinates(Comparator<TC> sortCols, Comparator<TR> sortRows){
        super(sortCols,sortRows);
        this.comparatorCols=sortCols;
        this.comparatorRows=sortRows;

    }
    //distancia entre 2 objetos en la tierra
    //area circular, rectangular

    /**
     * Metodo basado en la formula de Haversine
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @return
     */
    public double distanceBetween(int x, int y, int x1, int y1){
        int radtierra = 6378;
        int difX = x1- x;
        int difY = y1- y;
        double aux = Math.pow(Math.sin(difX/2),2) + Math.cos(Math.toRadians(x)) * Math.cos(Math.toRadians(x1)) * Math.pow(Math.sin(difY/2),2);
        System.out.println(aux);
        double aux1 = 2*Math.atan2(Math.sqrt(aux),Math.sqrt(Math.abs(1-aux)));
        System.out.println(aux1);
        return radtierra* aux1;
    }



}
