package test;

import models.MySparceMatrix;

public class TestMatrix {

	public static void main(String[] args) {

		MySparceMatrix<String, Integer, String> matrix = new MySparceMatrix<>((x, y) -> x.compareTo(y),
				(x, y) -> x - y);

		matrix.set(11, "A", "carlitos");
		matrix.set(1134, "C", "Mario");
		matrix.set(1134, "C", "Mario");
		matrix.set(1135, "C", "Mario 2");
		matrix.set(1135, "D", "Mario 3");
		matrix.set(1134, "B", "pepita");
		matrix.set(12, "B", "lola");

		System.out.println(matrix.get(12, "A"));
		System.out.println(matrix.get(1134, "B"));
		System.out.println(matrix.get(1134, "C"));
		System.out.println(matrix.get(12, "B"));

		matrix.test();

		matrix.removeCell(11, "A");
		matrix.removeCell(1134, "B");
		matrix.removeCell(12, "B");
		System.out.println("==================");
		matrix.test();
	}
}
