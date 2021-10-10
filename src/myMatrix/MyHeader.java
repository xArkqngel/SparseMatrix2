package myMatrix;

import doubleList.MyDoubleList;
import doubleList.MyDoubleNode;

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

    public MyHeader(T info,TC cells) {
        this.info = info;
        this.cells = new MyDoubleList<>();
    }

    public T getInfo() {
        return info;
    }
    public void search(MyDoubleNode<TC> node){
        cells.search(node);
    }
    public void add(MyDoubleNode<TC> info){
        cells.addNode(info);
    }
}
