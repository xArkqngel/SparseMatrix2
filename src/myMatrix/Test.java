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
        matrix.add(1F,123F,"S2S no col");
        matrix.add(332F,123F,"S3S no col");//
        matrix.add(12F,132F,"S4S nono");
        matrix.add(332F,124F,"S5S no row");//
        matrix.add(1F,124F,"ambos");//

//		matrix.test();
        System.out.println( matrix.get(332F,123F));

//        System.out.println(matrix.numberOfElementsIntoRectangularArea(331F,340F,122F,140F));
//        System.out.println(matrix.numberInCircualArea(330F,120F,100));
//        System.out.println(matrix.distanceBetween(1F,1F,5F,5F));

    }
}
