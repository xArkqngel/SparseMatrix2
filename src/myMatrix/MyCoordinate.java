package myMatrix;

/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
public class MyCoordinate{
    private double lat;
    private double lon;

    public MyCoordinate(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Calcula la distancia entre 2 puntos, usando el metodo de Haversine
     * @param destino
     * @return
     */
    public double distanceBetween(MyCoordinate destino){
        double radius = 6378.0;
        double difLat = Math.toRadians(destino.lat- this.lat);
        double difLong = Math.toRadians(destino.lon- this.lon);
        double aux = Math.pow(Math.sin(difLat/2),2) + Math.cos(Math.toRadians(this.lat)) * Math.cos(Math.toRadians(destino.lat)) * Math.pow(Math.sin(difLong/2),2);
        double aux1 = 2*Math.atan2(Math.sqrt(aux),Math.sqrt(1-aux));
        return radius* aux1;
    }
}
