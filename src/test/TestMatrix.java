package test;

import models.Coordinate;
import models.GeoSparse;
import models.MySparceMatrix;

import java.util.Comparator;

public class TestMatrix {

	public static void main(String[] args) {
		Coordinate start = new Coordinate(0,0);
		Comparator<Coordinate> comparator = new Comparator<Coordinate>() {
			@Override
			public int compare(Coordinate o1, Coordinate o2) {
				float d = o1.distanceBetween(start);
				float d2 = o2.distanceBetween(start);
				return d<d2?-1:d>d2?1:0;
			}
		};
		GeoSparse<Coordinate, Coordinate, String> matrix = new GeoSparse<>(comparator,comparator);
		GeoSparse<Integer,Integer,String> m = new GeoSparse<>((x, y) -> x.compareTo(y),
				(x, y) -> x - y);

		System.out.println(m.distanceBetween(new Coordinate(41.57879F,  1.617221F), new Coordinate(37.176487F, -3.597929F))+"");

//		matrix.set()

//		matrix.set(11, "A", "carlitos");
//		matrix.set(1134, "C", "Mario");
//		matrix.set(1134, "C", "Mario");
//		matrix.set(1135, "C", "Mario 2");
//		matrix.set(1135, "D", "Mario 3");
//		matrix.set(1134, "B", "pepita");
//		matrix.set(12, "B", "lola");
//
//		System.out.println(matrix.get(12, "A"));
//		System.out.println(matrix.get(1134, "B"));
//		System.out.println(matrix.get(1134, "C"));
//		System.out.println(matrix.get(12, "B"));
//
//		matrix.test();
//
//		matrix.removeCell(11, "A");
//		matrix.removeCell(1134, "B");
//		matrix.removeCell(12, "B");
//		System.out.println("==================");
//		matrix.test();
	}
}
