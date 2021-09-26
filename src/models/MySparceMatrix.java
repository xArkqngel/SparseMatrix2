package models;

import java.util.Comparator;


public class MySparceMatrix<TC, TR, C> extends SimpleList<MyHeader<TC, TR, C>> {

	private Comparator<MyHeader<TC, TR, C>> sortCols;
	private Comparator<CellMatrix<TC, TR, C>> sortRows;

	public MySparceMatrix(Comparator<TC> sortCols, Comparator<TR> sortRows) {

		Comparator<MyHeader<TC, TR, C>> sortColsMatrix = new Comparator<MyHeader<TC, TR, C>>() {
			@Override
			public int compare(MyHeader<TC, TR, C> o1, MyHeader<TC, TR, C> o2) {
				return sortCols.compare(o1.getColumn(), o2.getColumn());
			}
		};

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
				System.out.println(header.getColumn()+" / "+row+" : "+ header.getCell(row));
				row = header.getNextRow();
			}
		}
	}
	
}
