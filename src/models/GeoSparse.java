package models;

import java.util.Comparator;

public class GeoSparse<TC,TR,T> extends MySparceMatrix{

    private MySparceMatrix mySparceMatrix;
    private Comparator<TC> comparatorCols;
    private Comparator<TR> comparatorRows;

    public GeoSparse(Comparator<TC> sortCols, Comparator<TR> sortRows){
        super(sortCols,sortRows);
        this.comparatorCols=sortCols;
        this.comparatorRows=sortRows;

    }
    //distancia entre 2 objetos en la tierra
    //area circular, rectangular

    /**
     * Metodo basado en la formula de Haversine
     * @param origen posicion origen
     * @param destino posicion destino
     * @return
     */
    public float distanceBetween(Coordinate origen, Coordinate destino){
        float radtierra = 6378.0F;
        double difLat = Math.toRadians(destino.lat- origen.lat);
        double difLong = Math.toRadians(destino.lon- origen.lon);
        float aux = (float) (Math.pow(Math.sin(difLat/2),2) + Math.cos(Math.toRadians(origen.lat)) * Math.cos(Math.toRadians(destino.lat)) * Math.pow(Math.sin(difLong/2),2));
        float aux1 = (float) (2*Math.atan2(Math.sqrt(aux),Math.sqrt(1-aux)));
        return (float) (radtierra* aux1);
    }

    public String numberOfElementsIntoCircularArea(Coordinate origen, int radio){
        return "";
    }

    public String numberOfElementsIntoRectangularArea(Coordinate origen, Coordinate fin){
//        int elements = 0;
//        Node<T> node = this.getHeader();
//        while (node.){
//        }
//
        return "";
    }




}
