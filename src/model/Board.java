package model;

import java.util.Random;

import controller.ImgManager;
import view.CellButton;

public class Board {
	private int countOfBombs;
	private int boardWidth;
	private int boardHeight;
	private Cell[][] cells;
	private CellButton[][] cellButtons;
	private boolean isReal = false;

	public Board(int width, int height, int countOfBombs) {
		this.boardWidth = width;
		this.boardHeight = height;
		this.cells = new Cell[width][height];
		this.countOfBombs = countOfBombs;
		fillBoardEmptyCells();
		setBombs();
		unSelectCells();
		cellButtons = new CellButton[width][height];
		for (int x = 0; x != cellButtons.length; x++) {
			for (int y = 0; y != cellButtons[0].length; y++) {
				Cell cell = cells[x][y];
				cellButtons[x][y] = new CellButton();
				cellButtons[x][y].initCellButton(cell);
			}
		}

	}

	public void unSelectCells() {
		for (Cell[] cellsWidth : cells) {
			for (Cell cell : cellsWidth) {
				cell.setWasSelected(false);

			}
		}
	}

	/**
	 * initializes board with empty cells values;
	 */
	public void fillBoardEmptyCells() {
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
		int bombCount = 0;
		Random random = new Random();
		while (bombCount < countOfBombs) {
			int randomX = random.nextInt(boardWidth);
			int randomY = random.nextInt(boardHeight);
			if (cells[randomX][randomY].isBomb() != true) {
				cells[randomX][randomY].setHasBomb(true);
				bombCount++;
			}
		}
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

	public int getBoardWidth() {
		return boardWidth;
	}

	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

	public CellButton[][] getCellButtons() {
		return cellButtons;
	}

	public void setCellButtons(CellButton[][] cellButtons) {
		this.cellButtons = cellButtons;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public boolean isFinish(int bombCount) {
		boolean finish = false;
		int count = 0;
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				boolean isRightBomb = cell.isSuggestBomb() && cell.isBomb();
				// boolean isRightEmpty = cell.isSuggestEmpty() &&
				// !cell.isBomb();
				if (isRightBomb)
					count++;
			}
		}
		if (count == bombCount)
			finish = true;
		return finish;

	}
}
