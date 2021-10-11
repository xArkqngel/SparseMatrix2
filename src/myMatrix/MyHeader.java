package myMatrix;

import doubleList.MyDoubleList;
import doubleList.MyDoubleNode;

import java.util.Comparator;

/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
public class MyHeader <T,TC>{
    private T info;
    protected MyDoubleList<TC> cells;
    /**
     * Constructor de la clase MyHeader
     * @param info informacion de tipo T
     */
    public MyHeader(T info) {
        this.info = info;
        this.cells = new MyDoubleList<>();
    }
    /**
     * Metodo que devuelve la info
     * @return T info
     */
    public T getInfo() {
        return info;
    }
    /**
     * Metodo que busca un nodo de tipo TC
     * @param node nodo a buscar
     * @return MyDoubleNode<TC> nodo encontrado o null
     */
    public MyDoubleNode<TC> search(MyDoubleNode<TC> node){
        return cells.search(node);
    }
    /**
     * Metodo que busca un nodo con un comparator
     * @param info comparator para buscar la informacion
     * @return MyDoubleNode<TC> nodo con la informacion o null
     */
    public MyDoubleNode<TC> searchInfo(Comparator<TC> info){
        return cells.searchInfo(info);
    }
    /**
     * Metodo que busca un nodo con un comparator
     * @param info comparator para buscar la informacion
     * @return MyDoubleNode<TC> nodo con la informacion o null
     */
    public void setInfoCell(Comparator<TC> infoCell,TC info){
        cells.searchInfo(infoCell).setInfo(info);
    }
    /**
     * Metodo que busca un nodo con un comparator
     * @param info comparator para buscar la informacion
     * @return MyDoubleNode<TC> nodo con la informacion o null
     */
    public void deleteCell(Comparator<TC> comparator,TC info){
        cells.remove(comparator,info);
    }
    /**
     * Metodo que añade un nuevo nodo a la lista de celdas
     * @param info nodo nuevo
     */
    public void add(MyDoubleNode<TC> info){
        cells.addNode(info);
    }
}
