package models;


public class CellMatrix<TC, TR, C> {

	private C content;
	private TC column;
	private TR row;

	public CellMatrix(C content, TC column, TR row) {
		super();
		this.content = content;
		this.column = column;
		this.row = row;
	}

	public C getContent() {
		return content;
	}

	public TC getColumn() {
		return column;
	}

	public TR getRow() {
		return row;
	}


	public void setContent(C content) {
		this.content = content;
	}
	
}
