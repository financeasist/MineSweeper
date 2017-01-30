package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Board;

/**
 * Class represents start Frame for game.
 * 
 * @author Roman Grupskyi
 * @version 1.1 since 28.01.2017
 */
public class StartFrameView {

	private final JPanel controlPanel = new JPanel();;
	private JTextField jt_mines, jt_time;
	final JFrame frame = new JFrame();
	private BoardPanel board = new BoardPanel(new Board(10, 10, 10));

	/**
	 * initializes a start window
	 */
	public StartFrameView() {

		frame.setTitle("Saper by Roman Grupskyi");
		frame.setSize(211, 292);

		BorderLayout borderLayout = new BorderLayout();
		board.setLayout(new GridLayout(10, 10));
		board.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7),
				BorderFactory.createLoweredBevelBorder()));
		setmenu();
		frame.setLayout(borderLayout);
		jt_mines = new JTextField();
		jt_time = new JTextField();
		jt_mines.setColumns(3);
		jt_time.setColumns(3);
		jt_mines.setBorder(BorderFactory.createLoweredBevelBorder());
		jt_time.setBorder(BorderFactory.createLoweredBevelBorder());
		jt_mines.setFont(new Font("DigtalFont.TTF", Font.BOLD, 20));
		jt_time.setFont(new Font("DigtalFont.TTF", Font.BOLD, 20));
		jt_mines.setHorizontalAlignment(SwingConstants.LEFT);
		jt_mines.setForeground(Color.RED);
		jt_time.setForeground(Color.RED);
		jt_mines.setBackground(Color.black);
		jt_time.setBackground(Color.black);
		JButton btnsmile = new JButton();
		btnsmile.setIcon(new ImageIcon("resources\\new game.gif"));
		// btnsmile.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		btnsmile.addActionListener(null);
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		controlPanel.add(jt_mines);
		controlPanel.add(btnsmile);
		controlPanel.add(jt_time);

		frame.getContentPane().add(controlPanel, BorderLayout.NORTH);

		frame.getContentPane().add(board, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centre(frame);

		frame.setVisible(true);

	}

	/**
	 * sets menu into Frame;
	 */
	public void setmenu() {
		JMenuBar bar = new JMenuBar();
		JMenu game = new JMenu("Game");
		JMenuItem menuitem = new JMenuItem("new game");
		final JCheckBoxMenuItem beginner = new JCheckBoxMenuItem("Begineer");
		final JCheckBoxMenuItem intermediate = new JCheckBoxMenuItem("Intermediate");
		final JCheckBoxMenuItem expert = new JCheckBoxMenuItem("Expert");
		final JCheckBoxMenuItem custom = new JCheckBoxMenuItem("Custom");
		final JMenuItem exit = new JMenuItem("Exit");
		final JMenu help = new JMenu("Help");
		final JMenuItem helpitem = new JMenuItem("Help");

		ButtonGroup status = new ButtonGroup();
		frame.setJMenuBar(bar);
		status.add(beginner);
		status.add(intermediate);
		status.add(expert);
		status.add(custom);

		game.add(menuitem);
		game.addSeparator();
		game.add(beginner);
		game.add(intermediate);
		game.add(expert);
		game.add(custom);
		game.addSeparator();
		game.add(exit);
		help.add(helpitem);

		bar.add(game);
		bar.add(help);

		menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setSize(211, 292);
			}

		});
		beginner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setSize(211, 292);
			}

		});

		intermediate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setSize(320, 400);
			}

		});
		custom.addActionListener(null);

		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		helpitem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "instruction");

			}
		});

	}

	/**
	 * Placing specified as parameter window at the center of the screen
	 *
	 * @param w
	 *            centered window
	 */
	public static void centre(Window w) {
		Dimension us = w.getSize();
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		// Coordinates top left corner for application window
		int newX = (them.width - us.width) / 2;
		int newY = (them.height - us.height) / 2;
		w.setLocation(newX, newY);

	}
}
