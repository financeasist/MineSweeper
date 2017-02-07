package view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import model.Board;

public class BoardPanel extends JPanel {
	private Board board;
	
	public BoardPanel(Board board) {
		super();
		this.board = board;
		this.setLayout(new GridLayout(board.getWidth(),board.getHeight(),0,0));
		this.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10),
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
	}


}
