package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import controller.ImgManager;
import model.Board;
import model.Cell;

public class CellButton extends JButton {
	private Cell cell;
	private int allFlags;
	private int available¿lags;

	public CellButton() {
		super();
		Dimension preferredSize = new Dimension(18, 18);
		this.setPreferredSize(preferredSize);
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Timer timer = StartFrameView.getTimerInstance();
				timer.start();
				Board board = cell.getBord();
				allFlags = board.getCountOfBombs();
				int mouseButton = e.getButton();
				if (mouseButton == 1) {
					if (!cell.isBomb()) {
						cell.setSuggestEmpty(true);
						cell.getBord().unSelectCells();
						cell.findCellsArround();
						draw(true);
						if (cell.isEmpty()) {
							showEmpty();
						}

					} else {
						StartFrameView.getTimerInstance().stop();
						showBang();
					}
				}
				if (mouseButton == 3) {
					if (cell.isSuggestBomb()) {
						cell.setSuggestBomb(false);
						available¿lags++;
						StartFrameView.setBombCountIntoControlPanel(available¿lags);
						draw(false);
					} else {
						available¿lags = countAvailableFlags();
						if (available¿lags > 0) {
							cell.setSuggestBomb(true);
							draw(false);
							available¿lags = countAvailableFlags();
							StartFrameView.setBombCountIntoControlPanel(available¿lags);
							Board bord = cell.getBord();
							if (bord.isFinish(bord.getCountOfBombs())) {
								timer.stop();
								drowCongretulate();
							}
						}
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

	}

	public void initCellButton(Cell cell) {
		this.cell = cell;
	}

	public void drowCongretulate() {
		Board bord = cell.getBord();
		CellButton[][] cellButtons = bord.getCellButtons();
		for (CellButton[] cells : cellButtons) {
			for (CellButton cellButton : cells) {
				if (cellButton.getCell().isSuggestBomb())
					cellButton.drawFlag();
				else
					cellButton.draw(true);
			}
		}
		JOptionPane.showMessageDialog(null, "congratulations! You Win!");
	}

	public void showEmpty() {
		CellButton[][] cellButtons = cell.getBord().getCellButtons();
		for (CellButton[] cells : cellButtons) {
			for (CellButton cellButton : cells) {
				// cellButton.getCell().findCellsArround();
				if (cellButton.getCell().isNeedsToOpen())
					cellButton.draw(true);
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
		JOptionPane.showMessageDialog(null, "It was ‡ bomb! You lose!");
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public void draw(boolean isReal) {
		if (isReal) {
			if (cell.isBomb()) {
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
		this.setIcon(null);
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

	public int countAvailableFlags() {
		int countFlags = 0;
		Board bord = cell.getBord();
		Cell[][] cells = bord.getCells();
		for (Cell[] cellsRow : cells) {
			for (Cell cell : cellsRow) {
				if (cell.isSuggestBomb())
					countFlags++;
			}
		}
		if (countFlags <= allFlags)
			return allFlags - countFlags;
		else
			return 0;
	}

}
