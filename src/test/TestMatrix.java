package test;

import models.Coordinate;
import models.MySparceMatrix;

import java.util.Comparator;

public class TestMatrix {

	public static void main(String[] args) {
		Coordinate start = new Coordinate(0,0);
		MySparceMatrix<Float, Float, String> matrix = new MySparceMatrix<Float, Float, String>((x, y) -> x.compareTo(y), (x, y) ->  x.compareTo(y));

		matrix.set(333F,123F,"1");
		matrix.set(1F,123F,"S2S");
		matrix.set(332F,123F,"S3S");
		matrix.set(12F,132F,"S4S");
		matrix.set(332F,124F,"S5S");

		System.out.println(matrix.existCol(1F));

//		matrix.test();

		System.out.println(matrix.numberOfElementsIntoRectangularArea(331F,340F,122F,140F));
		System.out.println(matrix.numberInCircualArea(330F,120F,100));
		System.out.println(matrix.distanceBetween(1F,1F,5F,5F));
	}
}
