package myMatrix;


import doubleList.DoubleListSort;
import list.SimpleList;
import list.SimpleListSort;

import java.util.Comparator;

/*
 * 1. revisar la estructura -- Solucion
 * 2. reducir la complejidad tiempo --> modificando
 * */
public class MyMatrix<TC,TR,C>{
    private DoubleListSort<MyHeader<TC,C>> cols;
    private DoubleListSort<MyHeader<TR,C>> rows;


    public MyMatrix(Comparator<TC> sortCols, Comparator<TR> sortRow) {
        this.cols = new DoubleListSort<>((o1, o2) -> sortCols.compare(o1.getInfo(), o2.getInfo()));
        this.rows = new DoubleListSort<>((o1, o2) -> sortRow.compare(o1.getInfo(), o2.getInfo()));
    }
    public void add(TC column,TR row,C info) {
        MyHeader<TC,C> col = cols.search(new MyHeader<>(column,info));
        if (col!=null){

        }
    }
    public void insert(TC column,TR row,C info) {
    }
    public C get(TC column,TR row){
        return null;
    }
    public void set(TC column,TR row,C info){

    }
}
