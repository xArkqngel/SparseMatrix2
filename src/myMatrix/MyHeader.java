package myMatrix;

import doubleList.MyDoubleList;
import doubleList.MyDoubleNode;

public class MyHeader <T,TC>{
    private T info;
    private MyDoubleList<TC> cells;

    public MyHeader(T info,TC cells) {
        this.info = info;
        this.cells = new MyDoubleList<>();
    }

    public T getInfo() {
        return info;
    }
    public void search(MyDoubleNode<TC> node){
        cells.
    }
    public void add(MyDoubleNode<TC> info){
        cells.addNode(info);
    }
}
