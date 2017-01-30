package model;

public class Board {
	private int countOfBombs;
	private int width;
	private int height;
	private Cell[][] cells;
	private boolean isReal = false;

	public Board(int width, int height, int countOfBombs) {
		this.width = width;
		this.height = height;
		this.cells = new Cell[width][height];
		this.countOfBombs = countOfBombs;
	}

	public int getCountOfBombs() {
		return countOfBombs;
	}

	public void setCountOfBombs(int countOfBombs) {
		this.countOfBombs = countOfBombs;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCell(Cell[][] cells) {
		this.cells = cells;
	}

	public boolean isReal() {
		return isReal;
	}

	public void setReal(boolean real) {
		this.isReal = real;
	}
	
	
	
}
