package myMatrix;


import doubleList.DoubleListSort;
import doubleList.MyDoubleNode;
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

    /**
     * @Description
     * @Author Sofia Suesca
     * @Author Miguel Rubiano
     * @Author Martin Chiquillo
     * @Date 9/10/2021
     **/
    public MyMatrix(Comparator<TC> sortCols, Comparator<TR> sortRow) {
        this.cols = new DoubleListSort<>((o1, o2) -> sortCols.compare(o1.getInfo(), o2.getInfo()));
        this.rows = new DoubleListSort<>((o1, o2) -> sortRow.compare(o1.getInfo(), o2.getInfo()));
    }

    /**
     * Añade un dato a una fila y columna especifica
     * @param column columna
     * @param row fila
     * @param info dato
     */
    public void add(TC column,TR row,C info) {
        MyHeader<TC,C> columnH = new MyHeader<>(column,info);
        MyHeader<TR,C> rowHeader = new MyHeader<>(row,info);
        MyHeader<TC,C> col = cols.search(columnH);
        MyHeader<TR,C> rowH = rows.search(rowHeader);
        if (col!=null && rowH!=null){
            col.add(info);
            rowH.add(info);
        }else if(col==null && rowH!=null){
            cols.add(columnH);
            rowH.add(info);
        }else if (col!=null && rowH==null){
            rows.add(rowHeader);
            col.add(info);
        }else {
            cols.add(columnH);
            rows.add(rowHeader);
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
