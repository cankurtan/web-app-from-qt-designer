package main.model;

import org.w3c.dom.Element;

public class GridObject {
	private Element element;
	private int row;
	private int column;
	private int colSpan;
	
	public GridObject(Element element, int row, int column, int colSpan) {		
		this.element = element;
		this.row = row;
		this.column = column;
		this.colSpan = colSpan;
	}
	
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}

	public int getColSpan() {
		return colSpan;
	}

	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}

}
