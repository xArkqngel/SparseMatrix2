package myMatrix;


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
        MyMatrix<Integer,Integer, MyCoordinate> myMatrix = new MyMatrix<>(Integer::compareTo, Integer::compareTo);

    }
}
