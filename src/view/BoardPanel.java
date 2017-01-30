package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import model.Board;
import model.Cell;

public class BoardPanel extends JPanel {
	private Board board;
	private int countOfBombs;
	private int width;
	private int height;
	private CellButton[][] cellButtons;
	private boolean isReal = false;

	public BoardPanel(Board board) {
		this.board = board;
		this.width = board.getWidth();
		this.height = board.getHeight();
		this.cellButtons = new CellButton[width][height];
		Cell[][] cells = board.getCells();
		for (int x = 0; x != cellButtons.length; x++) {
			for (int y = 0; y != cellButtons[0].length; y++) {
				Cell cell = cells[x][y];
				cellButtons[x][y] = new CellButton(cell);
			}
		}
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
}
