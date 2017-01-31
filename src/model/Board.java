package model;

import java.util.Random;

import controller.ImgManager;

public class Board {
	private int countOfBombs;
	private int boardWidth;
	private int boardHeight;
	private Cell[][] cells;
	private boolean isReal = false;

	public Board(int width, int height, int countOfBombs) {
		this.boardWidth = width;
		this.boardHeight = height;
		this.cells = new Cell[width][height];
		this.countOfBombs = countOfBombs;
		fillBoardEmptyCells();
		setBombs();
		
	}

	public int getCountOfBombs() {
		return countOfBombs;
	}

	public void setCountOfBombs(int countOfBombs) {
		this.countOfBombs = countOfBombs;
	}

	public int getWidth() {
		return boardWidth;
	}

	public void setWidth(int width) {
		this.boardWidth = width;
	}

	public int getHeight() {
		return boardHeight;
	}

	public void setHeight(int height) {
		this.boardHeight = height;
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
	
	/**
	 *  initializes board with empty cells values;
	 */
	public void fillBoardEmptyCells(){
			for (int x = 0; x < boardWidth; x++) {
				for (int y = 0; y < boardHeight; y++) {
					cells[x][y] = new Cell(false, x, y, ImgManager.BUTTON_EMPTY, this);
				}
			}
	}
	/**
	 * method sets bombs on the board by chance, according to countOfBombs;
	 */
	public void setBombs() {
		int bombCount=0;
		Random random = new Random();
		while (bombCount<countOfBombs){
			int	randomX = random.nextInt(boardWidth);
			int	randomY = random.nextInt(boardHeight);
			if(cells[randomX][randomY].getHasBomb()!=true){
				cells[randomX][randomY].setHasBomb(true);
				bombCount++;
			}
		}
	}
	
}
