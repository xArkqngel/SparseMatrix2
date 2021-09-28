package models;

import java.util.Comparator;


public class MySparceMatrix<TC, TR, C> extends SimpleList<MyHeader<TC, TR, C>> {

	private Comparator<MyHeader<TC, TR, C>> sortCols;
	private Comparator<CellMatrix<TC, TR, C>> sortRows;
	private Comparator<TR> compareRows;
	private Comparator<TC> compareCols;

	public MySparceMatrix(Comparator<TC> sortCols, Comparator<TR> sortRows) {
		this.compareCols=sortCols;
		this.compareRows=sortRows;

		Comparator<MyHeader<TC, TR, C>> sortColsMatrix = (o1, o2) -> sortCols.compare(o1.getColumn(), o2.getColumn());

		Comparator<CellMatrix<TC, TR, C>> sortRowsMatrix = new Comparator<CellMatrix<TC, TR, C>>() {
			@Override
			public int compare(CellMatrix<TC, TR, C> o1, CellMatrix<TC, TR, C> o2) {
				return sortRows.compare(o1.getRow(), o2.getRow());
			}
		};

		this.sortCols = sortColsMatrix;
		this.sortRows = sortRowsMatrix;

	}



	public C get(TR row, TC column) {
		this.reset();
		while (this.isInto()) {
			MyHeader<TC, TR, C> myColAndRows = this.getNext();
			if (myColAndRows.getColumn().equals(column)) {
				myColAndRows.resetRows();
				TR rowMatrix = myColAndRows.getNextRow();
				while (rowMatrix != null) {
					if (rowMatrix.equals(row)) {
						return myColAndRows.getCell(rowMatrix);
					}
					rowMatrix = myColAndRows.getNextRow();
				}
			}
		}
		return null;
	}

	
	public boolean set(TR row, TC column, C info) {
		if (existCol(column)) {
			if (get(row, column) == null) {
				addRow(row, column, info);
				return true;
			}
		}else {
			this.addSort(new MyHeader<TC, TR, C>(column, row, info, sortRows), sortCols);
			return true;
		}
		return false;
	}


	private void addRow(TR row, TC column, C info) {
		this.reset();
		while (this.isInto()) {
			MyHeader<TC, TR, C> myColAndRows = this.getNext();
			if (myColAndRows.getColumn().equals(column)) {
				myColAndRows.addRow(row, info);
			}
		}
	}

	private boolean existCol(TC column) {
		this.reset();
		while (this.isInto()) {
			if (this.getNext().getColumn().equals(column)) {
				return true;
			}
		}
		return false;
	}


	private void setItem(TR row, TC column, C info) {
		this.reset();
		while (this.isInto()) {
			MyHeader<TC, TR, C> myColAndRows = this.getNext();
			if (myColAndRows.getColumn().equals(column)) {
				myColAndRows.resetRows();
				TR rowMatrix = myColAndRows.getNextRow();
				while (rowMatrix != null) {
					if (rowMatrix.equals(row)) {
						myColAndRows.setCell(row, info);
					}
					rowMatrix = myColAndRows.getNextRow();
				}
			}
		}
	}

	public void removeCell(TR row, TC col) {
		if (get(row, col) != null) {
			this.reset();
			while (this.isInto()) {
				MyHeader<TC, TR, C> aux = this.getNext();
				remove(row, col, aux);
			}
		}
	}


	private void remove(TR row, TC col, MyHeader<TC, TR, C> aux) {
		if (aux.getColumn().equals(col)) {
			aux.reset();
			TR rowAux = aux.getNextRow();
			while (rowAux != null) {
				if (rowAux.equals(row)) {
					aux.removeCell(row);
				}
				rowAux = aux.getNextRow();
			}
			if (aux.isEmptyRows()) {
				this.remove(aux);
			}
		}
	}

	
	public void test() {
		this.reset();
		while (this.isInto()) {
			MyHeader<TC, TR, C> header = this.getNext();
			header.reset();
			TR row = header.getNextRow();
			while(row != null) {
				System.out.println(header.getColumn().toString()+" / "+row.toString()+" : "+ header.getCell(row));
				row = header.getNextRow();
			}
		}
	}

	public String numberOfElementsIntoRectangularArea(TR x, TR x1, TC y, TC y1){
		this.reset();
		int count = 0;
		while (this.isInto()) {
			MyHeader<TC,TR,C> header = this.getNext();
			header.reset();
			TR row = header.getNextRow();
			while(row != null) {
				if (compareRows.compare(row, x)>=0 && compareRows.compare(row, x1)<=0
				&& compareCols.compare(header.getColumn(), y)>=0 && compareCols.compare(header.getColumn(), y1)<=0){
					System.out.println(header.getCell(row));
					count++;
				}
				row = header.getNextRow();
			}
		}
		return count+" ";
	}

	public String numberInCircualArea(TR circleX, TC circleY, int radius){
		this.reset();
		int count = 0;
		double circleRadius = Math.sqrt(radius);
		while (this.isInto()){
			MyHeader<TC,TR,C> header = this.getNext();
			header.reset();
			TR row = header.getNextRow();
			while (row!=null){
				double aux = this.obtainSQRT((Float) row,(Float)header.getColumn(),(Float) circleX,(Float) circleY);
				if (aux<=circleRadius){
					count++;

				}
				row = header.getNextRow();
			}
		}
		return "Numero de elementos dentro del circulo ---> " +count;
	}


	public double obtainSQRT(float xOrigin, float yOrigin, float xCircle,float yCircle){
		double aux1 = Math.pow(xOrigin - xCircle,2);
		double aux2 = Math.pow(yOrigin - yCircle,2);

		return Math.sqrt(aux1+aux2);

	}
	public float distanceBetween(TR origenX, TC origenY, TR destinoX, TC destinoY){
		float radtierra = 6378.0F;
		double difLat = Math.toRadians((Float) destinoX- (Float) origenX);
		double difLong = Math.toRadians((Float)destinoY- (Float)origenY);
		float aux = (float) (Math.pow(Math.sin(difLat/2),2) + Math.cos(Math.toRadians((Float) origenX)) * Math.cos(Math.toRadians((Float) destinoX)) * Math.pow(Math.sin(difLong/2),2));
		float aux1 = (float) (Math.atan2(Math.sqrt(aux),Math.sqrt(1-aux)));
		return  (radtierra * aux1);
	}
	
}
