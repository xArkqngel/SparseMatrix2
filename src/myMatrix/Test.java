package myMatrix;


import models.Coordinate;

import java.util.Comparator;


/**
 * Package: myMatrix
 * Name: Test
 *
 * @Description
 *
 *  @Author Sofia Suesca
 *  @Author Miguel Rubiano
 *  @Author Martin Chiquillo
 *  @Date 9/10/2021
 *
 **/
public class Test {
    public static void main(String[] args) {
        Coordinate start = new Coordinate(0,0);
        MyMatrix<Float, Float, String> matrix = new MyMatrix<Float, Float, String>((x, y) -> x.compareTo(y), (x, y) -> x.compareTo(y), new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        matrix.add(333F,123F,"1 nono");
        matrix.add(1F,123F,"2 no col");
        matrix.add(332F,123F,"3 no col");//
        matrix.add(12F,132F,"4 nono");
        matrix.add(332F,124F,"5 no row");//
        matrix.add(1F,124F,"6 ambos");//
        matrix.add(1F,125F,"7 no row");//
        matrix.add(1F,126F,"8 no row");//
        matrix.add(334F,132F,"9 no col");

//		matrix.test();
        System.out.println( matrix.get(332F,124F));

        System.out.println(matrix.elementsRectangular(123F,133F,332F,335F));
//        System.out.println(matrix.numberInCircualArea(330F,120F,100));
//        System.out.println(matrix.distanceBetween(331F,1F,5F,5F));

    }
}
