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
        MyHeader<TR,C> rowH = rows.search(new MyHeader<>(row,info));
        if (col!=null && rowH!=null){

        }else if(){

        }else if (){

        }else {
            if (cols.getFirst().getInfo()==null){
                MyHeader<TC,C> colsCells = new MyHeader<TC,C>(column);
                colsCells.add(info);
                cols.add(colsCells);
            }else if (rows.getFirst().getInfo()== null){
                MyHeader<TR,C> rowsCells = new MyHeader<TR,C>(row);
                rowsCells.add(info);
                rows.add(rowsCells);
            }
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
