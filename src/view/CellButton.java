package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.ImgManager;
import model.Cell;

public class CellButton extends JButton {
	private Cell cell;

	public CellButton() {
		super();
		Dimension preferredSize = new Dimension(18, 18);
		this.setPreferredSize(preferredSize);
		this.addActionListener(new ActionListener(){
	         public void actionPerformed(ActionEvent e) {
	            draw(true);
	          }          
	       });
	}

	public void initCellButton(Cell cell) {
		this.cell = cell;
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
//		this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_CLOSED));
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
		this.setIcon(ImgManager.GetImg(ImgManager.BUTTON_EMPTY));
	}
}
