package myMatrix;


import doubleList.DoubleListSort;
import doubleList.MyDoubleNode;

import java.util.Comparator;

/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
public class MyMatrix<TC,TR,C>{
    private DoubleListSort<MyHeader<TC,C>> cols;
    private DoubleListSort<MyHeader<TR,C>> rows;
    private Comparator<C> comparatorInfo;

    /**
     * Constructor
     * @param sortCols
     * @param sortRow
     * @param comparatorInfo
     */
    public MyMatrix(Comparator<TC> sortCols, Comparator<TR> sortRow,Comparator<C> comparatorInfo) {
        this.comparatorInfo = comparatorInfo;
        this.cols = new DoubleListSort<>((o1, o2) -> sortCols.compare(o1.getInfo(), o2.getInfo()));
        this.rows = new DoubleListSort<>((o1, o2) -> sortRow.compare(o1.getInfo(), o2.getInfo()));
    }

    /**
     * Añade un dato en una fila y columna especificada
     * @param column columna
     * @param row fila
     * @param info dato
     */
    public void add(TC column,TR row,C info) {
        MyHeader<TC,C> columnH = new MyHeader<>(column,info);
        MyHeader<TR,C> rowHeader = new MyHeader<>(row,info);
        MyHeader<TC,C> col = cols.search(columnH);
        MyHeader<TR,C> rowH = rows.search(rowHeader);
        MyDoubleNode<C> node = new MyDoubleNode<>(info);
        if (col!=null && rowH!=null){
            col.add(node);
            rowH.add(node);
        }else if(col==null && rowH!=null){
            cols.add(columnH);
            rowH.add(node);
        }else if (col!=null && rowH==null){
            rows.add(rowHeader);
            col.add(node);
        }else {
            cols.add(columnH);
            rows.add(rowHeader);
        }
    }

    /**
     * Obtiene el dato de una fila y columna especificada
     * @param column columna
     * @param row fila
     * @return dato encontrado
     */
    public C get(TC column,TR row){
        MyHeader<TC,C> col = cols.search(new MyHeader<>(column,null));
        MyHeader<TR,C> rowH = rows.search(new MyHeader<>(row,null));
        if (col!=null&&rowH!=null){
            MyDoubleNode<C> aux = col.cells.getFirst();
            MyDoubleNode<C> auxR = rowH.cells.getFirst();
            while (aux!=null){
                while (auxR!=null){
                    if (comparatorInfo.compare(aux.getInfo(), auxR.getInfo())==0){
                        return auxR.getInfo();
                    }
                    auxR = auxR.getNext();
                }
                aux = aux.getNext();
            }
        }
        return null;
    }
    public void set(TC column,TR row,C info){

    }
}
