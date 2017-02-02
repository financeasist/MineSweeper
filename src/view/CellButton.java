package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.ImgManager;
import model.Board;
import model.Cell;

public class CellButton extends JButton {
	private Cell cell;

	public CellButton() {
		super();
		Dimension preferredSize = new Dimension(18, 18);
		this.setPreferredSize(preferredSize);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cell.getHasBomb() == false) {
					cell.findCellsArround();
					draw(true);
					if (cell.isEmpty()) {
						showNeibourEmptyCells();
					}
				} else {
					showBang();
				}
			}
		});

	}

	public void initCellButton(Cell cell) {
		this.cell = cell;
	}

	public void showNeibourEmptyCells() {
		Board bord = cell.getBord();
		CellButton[][] cellButtons = bord.getCellButtons();
		for (CellButton[] cells : cellButtons) {
			for (CellButton cellButton : cells) {
				if (cellButton.getCell().isEmpty()) {
					cellButton.getCell().findCellsArround();
					if (cellButton.getCell().isEmpty())
						cellButton.draw(true);
					
				}
			}
		}
	}

	public void showBang() {
		cell.setCurrentStateImgType("BUTTON_BANG");
		Board bord = cell.getBord();
		CellButton[][] cellButtons = bord.getCellButtons();
		for (CellButton[] cells : cellButtons) {
			for (CellButton cellButton : cells) {
				cellButton.getCell().findCellsArround();
				cellButton.draw(true);
			}
		}
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public void draw(boolean isReal) {
		if (isReal) {
			if (cell.getHasBomb()) {
				drawBomb(); // draws bomb
				if ("BUTTON_BANG".equals(cell.getCurrentStateImgType())) {
					drawBang(); // draws bang
				}
			} else {
				drawCurrentNumber();// draw real cell value
			}
		} else {
			try {
				if (cell.isSuggestBomb()) {

					drawFlag(); // draws flag
				}

				else {
					if (cell.isSuggestEmpty())

					{
						drawCurrentNumber(); // draws real cell value
					} else {
						drawClosed();// draws closed cell
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void drawClosed() {
		// this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_CLOSED));
	}

	public void drawBang() {
		this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_BANG));
	}

	public void drawBomb() {
		this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_BOMB));
	}

	public void drawFlag() {
		this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_FLAG));
	}

	public void drawCurrentNumber() {
		if (cell.getBombArround() == 0) {
			this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_EMPTY));
		}
		if (cell.getBombArround() == 1) {
			this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_ONE));
		}
		if (cell.getBombArround() == 2) {
			this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_TWO));
		}
		if (cell.getBombArround() == 3) {
			this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_THREE));
		}
		if (cell.getBombArround() == 4) {
			this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_FOUR));
		}
		if (cell.getBombArround() == 5) {
			this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_FIVE));
		}
		if (cell.getBombArround() == 6) {
			this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_SIX));
		}
		if (cell.getBombArround() == 7) {
			this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_SEVEN));
		}
		if (cell.getBombArround() == 8) {
			this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_EIGHT));
		}
	}
}
