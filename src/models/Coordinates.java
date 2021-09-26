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

    

}
