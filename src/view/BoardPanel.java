package view;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import model.Board;
import model.Cell;

public class BoardPanel extends JPanel {
	private Board board;
	private  CellButton[][] cellButtons;
	private boolean isReal;
	
	public BoardPanel(Board board) {
		super();
		this.board = board;
		this.cellButtons = board.getCellButtons();
		this.isReal = board.isReal();
		this.setLayout(new GridLayout(board.getWidth(),board.getHeight(),0,0));
		this.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10),
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if (cellButtons != null) {
			for (int x = 0; x != cellButtons.length; x++) {
				for (int y = 0; y != cellButtons[0].length; y++) {
					CellButton cellButton = cellButtons[x][y];
					cellButton.draw(isReal);

				}
			}
		}
	}

	public void drawBoard(CellButton[][] cells) {
		this.cellButtons = cells;
		isReal = false;
		this.repaint();
	}

	public void drawCell(int x, int y) {
		this.repaint();

	}

	public void drawBang(int x, int y) {
		CellButton cellButton = cellButtons[x][y];
		Cell cell = cellButton.getCell();
		cell.setCurrentStateImgType("BUTTON_BANG");
		this.isReal = true;
		this.repaint();

	}

	public void drawCongratulate() {
		this.isReal = true;
		this.repaint();

	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	public CellButton[][] getCellButtons() {
		return cellButtons;
	}

	
}
