package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Board;

public class BoardPanel extends JPanel {
	private Board board;
	
	public BoardPanel(Board board) {
		super();
		this.board = board;
		this.setLayout(new GridLayout(board.getWidth(),board.getHeight(),0,0));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLoweredBevelBorder()));
	}


}
