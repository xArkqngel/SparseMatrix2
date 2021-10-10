package myMatrix;


import doubleList.DoubleListSort;

import java.util.Comparator;

public class MyMatrix<TC,TR,C>{
    private DoubleListSort<MyHeader<TC,C>> cols;
    private DoubleListSort<MyHeader<TR,C>> rows;
    private Comparator<TC> sortCols;
    private Comparator<TR> sortRow;

    public MyMatrix(Comparator<TC> sortCols, Comparator<TR> sortRow) {
        this.sortCols = sortCols;
        this.sortRow = sortRow;
        this.cols = new DoubleListSort<>((o1, o2) -> sortCols.compare(o1.getInfo(), o2.getInfo()));
        this.rows = new DoubleListSort<>((o1, o2) -> sortRow.compare(o1.getInfo(), o2.getInfo()));
    }
    public void add(TC column,TR row,C info) {
        MyHeader<TC,C> columnH = new MyHeader<>(column,info);
        MyHeader<TR,C> rowHeader = new MyHeader<>(row,info);
        MyHeader<TC,C> col = cols.search(columnH);
        MyHeader<TR,C> rowH = rows.search(rowHeader);
        if (col!=null && rowH!=null){
            col.add(info);
            rowH.add(info);
        }else if(col==null && rowH!=null){
            cols.add(columnH);
            rowH.add(info);
        }else if (col!=null && rowH==null){
            rows.add(rowHeader);
            col.add(info);
        }else {
            cols.add(columnH);
            rows.add(rowHeader);
        }
    }
    public C get(TC column,TR row){
        MyHeader<TC,C> col = cols.search(new MyHeader<>(column,null));
        MyHeader<TR,C> rowH = rows.search(new MyHeader<>(row,null));
        if (col!=null&&rowH!=null){
        }
        return null;
    }
    public void set(TC column,TR row,C info){

    }
}
