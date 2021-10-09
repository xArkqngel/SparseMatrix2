package myMatrix;


/**
 * Package: myMatrix
 * Name: Test
 *
 * @Description
 * @Author Sofia Suesca
 * @Date 9/10/2021
 **/
public class Test {
    public static void main(String[] args) {
        MyMatrix<Integer,Integer, MyCoordinate> myMatrix = new MyMatrix<>(Integer::compareTo, Integer::compareTo);

    }
}
