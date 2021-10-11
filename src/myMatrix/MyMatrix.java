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
    private Comparator<TC> comparatorCol;
    private Comparator<TR> comparatorRow;

    /**
     * Constructor
     * @param sortCols
     * @param sortRow
     * @param comparatorInfo
     */
    public MyMatrix(Comparator<TC> sortCols, Comparator<TR> sortRow,Comparator<C> comparatorInfo) {
        this.comparatorInfo = comparatorInfo;
        this.comparatorCol = sortCols;
        this.comparatorRow = sortRow;
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
            while (aux!=null){
                MyDoubleNode<C> auxR = rowSearch.cells.getFirst();
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
     public void delete(TC column,TR row,C info){
    	MyHeader<TC,C> colSearch = cols.search(new MyHeader<>(column));
        MyHeader<TR,C> rowSearch = rows.search(new MyHeader<>(row));
        System.out.println(colSearch);
        if(rowSearch!=null&&colSearch!=null){
            colSearch.deleteCell(this.comparatorInfo, info);
            rowSearch.deleteCell(this.comparatorInfo,info);
        }
       
    }
    public String elementsRectangular(TR x,TR x1,TC y,TC y1){
        String result = " ";
        int count =0;

        MyDoubleNode<MyHeader<TR,C>> auxRow = rows.getFirst();
        while (auxRow!=null  &&comparatorRow.compare(auxRow.getInfo().getInfo(), x1)<=0){
            if (comparatorRow.compare(auxRow.getInfo().getInfo(), x)>=0) {
                MyDoubleNode<MyHeader<TC,C>> auxCol = cols.getFirst();
                while (auxCol != null && comparatorCol.compare(auxCol.getInfo().getInfo(), y1) <= 0) {
                    if (comparatorCol.compare(auxCol.getInfo().getInfo(), y) >= 0) {
                        C data = get(auxCol.getInfo().getInfo(), auxRow.getInfo().getInfo());
                        if (data != null) {
                            result += data+" ";
                            count++;
                        }
                    }
                    auxCol = auxCol.getNext();
                }
            }
            auxRow = auxRow.getNext();
        }
        return result;
    }
    public String numberInCircualArea(TR circleX, TC circleY, int radius){
        int count = 0;
        double circleRadius = Math.sqrt(radius);
        MyDoubleNode<MyHeader<TR,C>> auxRow = rows.getFirst();
        while (auxRow!=null){
        	MyDoubleNode<MyHeader<TC,C>> auxCol = cols.getFirst();
            while (auxCol!=null){
                double aux = this.obtainSQRT((Float) auxRow.getInfo().getInfo(),(Float)auxCol.getInfo().getInfo(),(Float) circleX,(Float) circleY);
                if (aux<=circleRadius){
                    count++;

                }
                auxCol = auxCol.getNext();
            }
            auxRow = auxRow.getNext();
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
