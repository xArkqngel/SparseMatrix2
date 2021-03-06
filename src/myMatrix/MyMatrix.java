package myMatrix;


import doubleList.DoubleListSort;
import doubleList.MyDoubleNode;

import java.util.ArrayList;
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
    /**
     * Metodo que cambia la informacion de la celda en un fla y una columna
     * @param column columna en la que esta la celda
     * @param row fila en la que esta la celda
     * @param info informacion nueva
     */
    public void set(TC column,TR row,C info){
    	MyHeader<TC,C> colSearch = cols.search(new MyHeader<>(column));
        MyHeader<TR,C> rowSearch = rows.search(new MyHeader<>(row));
        if (colSearch!=null&&rowSearch!=null){
        	C infoNode = this.get(column, row);
            MyDoubleNode<C> aux = colSearch.cells.getFirst();
            while (aux!=null){
                MyDoubleNode<C> auxR = rowSearch.cells.getFirst();
                while (auxR!=null){
                    if (comparatorInfo.compare(aux.getInfo(), auxR.getInfo())==0){
                        auxR.setInfo(info);
                        aux.setInfo(info);
                        return;
                    }
                    auxR = auxR.getNext();
                }
                aux = aux.getNext();
            }
        }
    }
    /**
     * Metodo que busca la fila de una informacion
     * @param info informacion a buscar fila
     * @return TR fila en la que esta la informacion o null
     */
    public TR searchRow(C info) {
    	MyDoubleNode<MyHeader<TR,C>> aux = rows.getFirst();
    	while(aux!=null) {
    		if(aux.getInfo().cells.searchInfoBoolean(info)) {
    			return aux.getInfo().getInfo();
    		}
    		aux = aux.getNext();
    	}
    	return null;
    }
    /**
     * Metodo que busca la columna de una informacion
     * @param info informacion a buscar columna
     * @return TC columna en la que esta la informacion o null
     */
    public TC searchColumn(C info) {
    	MyDoubleNode<MyHeader<TC,C>> aux = cols.getFirst();
    	while(aux!=null) {
    		if(aux.getInfo().cells.searchInfoBoolean(info)) {
    			return aux.getInfo().getInfo();
    		}
    		aux = aux.getNext();
    	}
    	return null;
    }
    /**
     * Metodo que borra una informacion
     * @param column columna donde esta la informacion
     * @param row fila donde esta la informacion
     * @param info informacion de la celda
     */
    public void delete(TC column,TR row,C info){
    	MyHeader<TC,C> colSearch = cols.search(new MyHeader<>(column));
        MyHeader<TR,C> rowSearch = rows.search(new MyHeader<>(row));
        if(rowSearch!=null&&colSearch!=null){
            colSearch.deleteCell(this.comparatorInfo, info);
            rowSearch.deleteCell(this.comparatorInfo,info);
        }
    }
    /**
     * Metodo que cuenta cuantos elementos se encuentrar en el area rectangular dada
     * @param x primera fila del rectangulo
     * @param x1 ultima fla del rectangulo
     * @param y primera columna del rectangulo
     * @param y1 ultima columna del rectangulo
     * @return ArrayList<C> elementos en el area y su informacion
     */
    public ArrayList<C> elementsRectangular(TR x,TR x1,TC y,TC y1){
    	ArrayList<C> list = new ArrayList<>();
        MyDoubleNode<MyHeader<TR,C>> auxRow = rows.getFirst();
        while (auxRow!=null  &&comparatorRow.compare(auxRow.getInfo().getInfo(), x1)<=0){
            if (comparatorRow.compare(auxRow.getInfo().getInfo(), x)>=0) {
                MyDoubleNode<MyHeader<TC,C>> auxCol = cols.getFirst();
                while (auxCol != null && comparatorCol.compare(auxCol.getInfo().getInfo(), y1) <= 0) {
                    if (comparatorCol.compare(auxCol.getInfo().getInfo(), y) >= 0) {
                        C data = get(auxCol.getInfo().getInfo(), auxRow.getInfo().getInfo());
                        if (data != null) {
                            list.add(data);
                        }
                    }
                    auxCol = auxCol.getNext();
                }
            }
            auxRow = auxRow.getNext();
        }
        return list;
    }
    /**
     * Metodo que cuenta los elementos ubicada en un area circular
     * @param circleX fila del centro del circulo
     * @param circleY columna del centro del circulo
     * @param radius radio del circulo
     * @return ArrayList<C> elementos en el circulo
     */
    public ArrayList<C> numberInCircualArea(TR circleX, TC circleY, int radius){
        int count = 0;
        ArrayList<C> pokeFound = new ArrayList<>();
        double circleRadius = Math.sqrt(radius);
        MyDoubleNode<MyHeader<TR,C>> auxRow = rows.getFirst();
        while (auxRow!=null){
        	MyDoubleNode<MyHeader<TC,C>> auxCol = cols.getFirst();
            while (auxCol!=null){
                double aux = this.obtainSQRT((Float) auxRow.getInfo().getInfo(),(Float)auxCol.getInfo().getInfo(),(Float) circleX,(Float) circleY);
                if (get(auxCol.getInfo().getInfo(), auxRow.getInfo().getInfo())!=null) {
                    if (aux<=circleRadius){
                        count++;
                        pokeFound.add(get(auxCol.getInfo().getInfo(), auxRow.getInfo().getInfo()));
                    }
                }
                auxCol = auxCol.getNext();
            }
            auxRow = auxRow.getNext();
        }
        return pokeFound;
    }


    /**
     * Metodo que expone a dos y luego calcula la raiz
     * @param xOrigin 
     * @param yOrigin
     * @param xCircle
     * @param yCircle
     * @return double raiz de los cuatro elementos
     */
    public double obtainSQRT(float xOrigin, float yOrigin, float xCircle,float yCircle){
        double aux1 = Math.pow(xOrigin - xCircle,2);
        double aux2 = Math.pow(yOrigin - yCircle,2);
        return Math.sqrt(aux1+aux2);

    }
    /**
     * Metodo que calcula la distancia entre cuatro parametros dos filas y dos columnas con el radio de la tierra
     * @param origenX primera fila
     * @param origenY primera columna
     * @param destinoX segunda fila
     * @param destinoY segunda columna
     * @return float la distancia entre las dos
     */
    public float distanceBetween(TR origenX, TC origenY, TR destinoX, TC destinoY){
        float radtierra = 6378.0F;
        double difLat = Math.toRadians((Float) origenX- (Float) destinoX);
        double difLong = Math.toRadians((Float)origenY- (Float)destinoY);
        float aux = (float) (Math.pow(Math.sin(difLat/2),2) + Math.cos(Math.toRadians((Float) origenX)) * Math.cos(Math.toRadians((Float) destinoX)) * Math.pow(Math.sin(difLong/2),2));
        System.out.println(aux);
        float aux1 = (float) (Math.atan2(Math.sqrt(aux),Math.sqrt(1-aux)));
        System.out.println(aux1);
        return (float) (radtierra * aux1)*2;
    }
}
