package models;

import list.SimpleList;

import java.util.Comparator;


public class MyHeader<TC, TR, C> extends SimpleList<CellMatrix<TC, TR, C>> {

	private TC nameColumn;
	private TR nameRow;
	private Comparator<CellMatrix<TC, TR, C>> comparator;


	public MyHeader(TC nameColumn, TR nameRow, C info, Comparator<CellMatrix<TC, TR, C>> comparator) {
		this.nameColumn = nameColumn;
		this.nameRow = nameRow;
		this.comparator = comparator;
		this.addSort(new CellMatrix<>(info, nameColumn, nameRow), comparator);
	}


	public TC getColumn() {
		return nameColumn;
	}

	public TR getRow(){
		return nameRow;
	}
	
	public void resetRows() {
		this.reset();
	}

	public TR getNextRow() {
		if (this.isInto()) {
			return this.getNext().getRow();
		}
		return null;
	}
	
	public C getCell(TR row) {
		this.reset();
		while (this.isInto()) {
			CellMatrix<TC, TR, C> cell = this.getNext();
			if (cell.getRow().equals(row)) {
				return cell.getContent();
			}
		}
		return null;
	}


	public void setCell(TR row, C info) {
		this.reset();
		while (this.isInto()) {
			CellMatrix<TC, TR, C> cell = this.getNext();
			if (cell.getRow().equals(row)) {
				cell.setContent(info);
			}
		}
	}


	public void removeCell(TR row) {
		this.reset();
		while (this.isInto()) {
			CellMatrix<TC, TR, C> cell = this.getNext();
			if (cell.getRow().equals(row)) {
				this.remove(cell);
			}
		}
	}


	public boolean isEmptyRows() {
		return this.isEmpty();
	}


	public void addRow(TR row, C info) {
		this.addSort(new CellMatrix<>(info, nameColumn, row), comparator);
	}
}
