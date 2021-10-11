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
        MyHeader<TC,C> columnH = new MyHeader<>(column);
        MyHeader<TR,C> rowH = new MyHeader<>(row);
        MyHeader<TC,C> colSearch = cols.search(columnH);
        MyHeader<TR,C> rowSearch = rows.search(rowH);
        MyDoubleNode<C> nodeRow = new MyDoubleNode<>(info);
        MyDoubleNode<C> nodeCol = new MyDoubleNode<>(info);
        if (colSearch!=null && rowSearch!=null){
            colSearch.add(nodeCol);
            rowSearch.add(nodeRow);
        }else if(colSearch==null && rowSearch!=null){
            columnH.add(nodeCol);
            cols.add(columnH);
            rowSearch.add(nodeRow);
        }else if (colSearch!=null && rowSearch==null){
            rowH.add(nodeRow);
            rows.add(rowH);
            colSearch.add(nodeCol);
        }else {
            columnH.add(nodeCol);
            cols.add(columnH);
            rowH.add(nodeRow);
            rows.add(rowH);
        }
        cols.insertionSort();
        rows.insertionSort();
    }

    /**
     * Obtiene el dato de una fila y columna especificada
     * @param column columna
     * @param row fila
     * @return dato encontrado
     */
    public C get(TC column,TR row){
        MyHeader<TC,C> colSearch = cols.search(new MyHeader<>(column));
        MyHeader<TR,C> rowSearch = rows.search(new MyHeader<>(row));
        if (colSearch!=null&&rowSearch!=null){
            MyDoubleNode<C> aux = colSearch.cells.getFirst();
            MyDoubleNode<C> auxR = rowSearch.cells.getFirst();
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
        MyHeader<TC,C> col = cols.search(new MyHeader<>(column));
        MyHeader<TR,C> rowH = rows.search(new MyHeader<>(row));
        if (col!=null&&rowH!=null){
            MyDoubleNode<C> node = col.cells.searchInfo((o1, o2) -> comparatorInfo.compare(o1,info));
            node.setInfo(info);
            rowH.cells.search(node).setInfo(info);
        }
    }

    public String numberOfElementsIntoRectangularArea(TR x, TR x1, TC y, TC y1){
        MyDoubleNode<MyHeader<TC,C>> auxCols = cols.getFirst();
        MyDoubleNode<MyHeader<TR,C>> auxCols = cols.getFirst();
        while (auxCols!=null){

        }

    }

    public String numberInCircualArea(TR circleX, TC circleY, int radius){
        this.reset();
        int count = 0;
        double circleRadius = Math.sqrt(radius);
        while (this.isInto()){
            models.MyHeader<TC,TR,C> header = this.getNext();
            header.reset();
            TR row = header.getNextRow();
            while (row!=null){
                double aux = this.obtainSQRT((Float) row,(Float)header.getColumn(),(Float) circleX,(Float) circleY);
                if (aux<=circleRadius){
                    count++;

                }
                row = header.getNextRow();
            }
        }
        return "Numero de elementos dentro del circulo ---> " +count;
    }


    public double obtainSQRT(float xOrigin, float yOrigin, float xCircle,float yCircle){
        double aux1 = Math.pow(xOrigin - xCircle,2);
        double aux2 = Math.pow(yOrigin - yCircle,2);

        return Math.sqrt(aux1+aux2);

    }
    public float distanceBetween(TR origenX, TC origenY, TR destinoX, TC destinoY){
        float radtierra = 6378.0F;
        double difLat = Math.toRadians((Float) origenX- (Float) destinoX);
        double difLong = Math.toRadians((Float)origenY- (Float)destinoY);
        float aux = (float) (Math.pow(Math.sin(difLat/2),2) + Math.cos(Math.toRadians((Float) origenX)) * Math.cos(Math.toRadians((Float) destinoX)) * Math.pow(Math.sin(difLong/2),2));
        System.out.println(aux);
        float aux1 = (float) (Math.atan2(Math.sqrt(aux),Math.sqrt(1-aux)));
        System.out.println(aux1);
        return (float) (radtierra * aux1);
    }
}
