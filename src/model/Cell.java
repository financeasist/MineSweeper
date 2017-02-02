package model;

public class Cell {
	private boolean hasBomb;
	private boolean suggestBomb = false;
	private boolean suggestEmpty = false;
	private int positionX;
	private int positionY;
	private int bombArround;
	private String currentStateImgType;
	private Board bord;
	
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
		this.bord = bord;
	}
	public boolean getHasBomb() {
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
		return bord;
	}
	public void setBord(Board bord) {
		this.bord = bord;
	}
	public String getCurrentStateImgType() {
		return currentStateImgType;
	}
	public void setCurrentStateImgType(String currentStateImgType) {
		this.currentStateImgType = currentStateImgType;
	}
	public void findCellsArround() {
		
	}
	
}
