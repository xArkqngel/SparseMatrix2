package models;

public class Coordinate  {

    protected float lat;
    protected float lon;

    public Coordinate(float lat, float lon){
        this.lat = lat;
        this.lon = lon;
    }
    public float distanceBetween(Coordinate destino){
        float radtierra = 6378.0F;
        double difLat = Math.toRadians(destino.lat- this.lat);
        double difLong = Math.toRadians(destino.lon- this.lon);
        float aux = (float) (Math.pow(Math.sin(difLat/2),2) + Math.cos(Math.toRadians(this.lat)) * Math.cos(Math.toRadians(destino.lat)) * Math.pow(Math.sin(difLong/2),2));
        float aux1 = (float) (2*Math.atan2(Math.sqrt(aux),Math.sqrt(1-aux)));
        return (float) (radtierra* aux1);
    }



}
