package myMatrix;

import doubleList.MyDoubleList;

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


    public void add(TC info){
        cells.add(info);
    }
}
