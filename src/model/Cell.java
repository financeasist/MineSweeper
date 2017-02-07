package model;

import java.util.ArrayList;
import java.util.List;

import view.CellButton;

public class Cell {
	private boolean hasBomb;
	private boolean suggestBomb = false;
	private boolean suggestEmpty = false;
	private int positionX;
	private int positionY;
	private int bombArround;
	private String currentStateImgType;
	private Board board;
	private boolean wasSelected;
	private boolean needsToOpen;

	/**
	 * @param hasBomb
	 * @param positionX
	 * @param positionY
	 * @param currentStateImgType
	 * @param bord
	 */
	public Cell(boolean hasBomb, int positionX, int positionY, String currentStateImgType, Board bord) {

		this.hasBomb = hasBomb;
		this.positionX = positionX;
		this.positionY = positionY;
		this.currentStateImgType = currentStateImgType;
		this.board = bord;
	}

	public boolean isBomb() {
		return hasBomb;
	}

	public void setHasBomb(boolean hasBomb) {
		this.hasBomb = hasBomb;
	}

	public boolean isSuggestBomb() {
		return suggestBomb;
	}

	public void setSuggestBomb(boolean suggestBomb) {
		this.suggestBomb = suggestBomb;
	}

	public boolean isWasSelected() {
		return wasSelected;
	}

	public void setWasSelected(boolean wasSelected) {
		this.wasSelected = wasSelected;
	}

	public boolean isSuggestEmpty() {
		return suggestEmpty;
	}

	public void setSuggestEmpty(boolean suggestEmpty) {
		this.suggestEmpty = suggestEmpty;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getBombArround() {
		return bombArround;
	}

	public void setBombArround(int bombArround) {
		this.bombArround = bombArround;
	}

	public Board getBord() {
		return board;
	}

	public void setBord(Board bord) {
		this.board = bord;
	}

	public String getCurrentStateImgType() {
		return currentStateImgType;
	}

	public void setCurrentStateImgType(String currentStateImgType) {
		this.currentStateImgType = currentStateImgType;
	}

	// public Cell getNeibourCell() {
	// return neibourCell;
	// }
	//
	// public void setNeibourCell(Cell neibourCell) {
	// this.neibourCell = neibourCell;
	// }

	public boolean isNeedsToOpen() {
		return needsToOpen;
	}

	public void setNeedsToOpen(boolean needsToOpen) {
		this.needsToOpen = needsToOpen;
	}

	public boolean isEmpty() {

		return this.bombArround == 0;

	}

	public void findCellsArround() {
		Cell neibourCell;
		System.out.println("start findCellsArround()");
		Cell[][] allCells = board.getCells();

		int boardWidth = board.getBoardWidth();
		int boardHeight = board.getBoardHeight();
		int bombCount = 0;
		List<Cell> neibourCells = new ArrayList<Cell>();
		for (int deltaX = -1; deltaX <= 1; deltaX++) {
			for (int deltaY = -1; deltaY <= 1; deltaY++) {
				// if (deltaX != 0 && deltaY != 0) {
				int assumedX = positionX + deltaX;
				int assumedY = positionY + deltaY;
				if (assumedX >= 0 && assumedY >= 0 && assumedX < boardWidth && assumedY < boardHeight) {
					neibourCell = allCells[assumedX][assumedY];
					if (!neibourCell.isWasSelected()) {
						if (neibourCell.hasBomb) {
							bombCount++;
						} else {
							neibourCells.add(neibourCell);
							neibourCell.wasSelected = true;
							needsToOpen = true;
						}
					}
				}
			}
		}
		this.bombArround = bombCount;
		if (bombCount == 0) {
			needsToOpen = true;
			for (Cell cell : neibourCells) {
				cell.findCellsArround();

			}

		}
	}
}

// }
