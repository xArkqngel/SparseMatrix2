package myMatrix;

import doubleList.MyDoubleList;

/**
 * @Description
 * @Author Sofia Suesca
 * @Author Miguel Rubiano
 * @Author Martin Chiquillo
 * @Date 9/10/2021
 **/
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
