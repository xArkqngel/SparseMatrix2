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

    public MyHeader(T info) {
        this.info = info;
        this.cells = new MyDoubleList<>();
    }

    public T getInfo() {
        return info;
    }
    public void search(MyDoubleNode<TC> node){
        cells.search(node);
    }
    public MyDoubleNode<TC> searchInfo(Comparator<TC> info){
        return cells.searchInfo(info);
    }
    public void setInfoCell(Comparator<TC> infoCell,TC info){
        cells.searchInfo(infoCell).setInfo(info);
    }
    public void deleteCell(Comparator<TC> comparator,TC info){
        cells.remove(comparator,info);
    }

    /**
     * Añade un nuevo nodo con su información
     * @param info
     */
    public void add(MyDoubleNode<TC> info){
        cells.addNode(info);
    }
}
