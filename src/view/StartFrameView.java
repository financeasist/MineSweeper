package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.Timer;

import commons.Constants;
import controller.TimerActionListener;
import model.Board;
import model.Cell;

/**
 * Class represents start Frame for game.
 * 
 * @author Roman Grupskyi
 * @version 1.1 since 28.01.2017
 */
public class StartFrameView {
	private JFrame frame;
	private JPanel controlPanel;
	private Board board;
	private BoardPanel boardPanel;
	private static JTextField jt_mines;
	private JTextField jt_time;
	private static JButton btnsmile;
	private static Timer timer;
	private boolean intermediateState;
	private boolean beginerState;
	private boolean expertState;

	public boolean isIntermediateState() {
		return intermediateState;
	}

	public void setIntermediateState(boolean intermediateState) {
		this.intermediateState = intermediateState;
	}

	public boolean isBeginerState() {
		return beginerState;
	}

	public void setBeginerState(boolean beginerState) {
		this.beginerState = beginerState;
	}

	public boolean isExpertState() {
		return expertState;
	}

	public void setExpertState(boolean expertState) {
		this.expertState = expertState;
	}

	public static Timer getTimerInstance() {
		ActionListener listener = new TimerActionListener();
		if (timer == null) {
			timer = new Timer(1000, listener);
		}
		return timer;
	}

	/**
	 * initializes a start window
	 */
	public StartFrameView() {
		initFrame();
		initAndAddComponentsToControlePanel();
		addCellButtonsToBoardPanel(Constants.BOARD_WIDTH_EASY, Constants.BOARD_HEIGHT_EASY,
				Constants.COUNT_OF_BOMBS_EASY);
	}

	public void initFrame() {
		frame = new JFrame();
		frame.setTitle("Saper by Roman Grupskyi");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		centre(frame);
		setmenu();

	}

	public void initTimer() {
		Timer timer = StartFrameView.getTimerInstance();
		timer.stop();
		ActionListener[] actionListeners = timer.getActionListeners();
		TimerActionListener listener = (TimerActionListener) actionListeners[0];
		listener.resetCount();
		listener.setTextField(jt_time);
	}

	public void initAndAddComponentsToControlePanel() {
		jt_mines = new JTextField();
		jt_time = new JTextField();
		btnsmile = new JButton("");
		controlPanel = new JPanel();
		jt_mines.setColumns(3);
		jt_mines.setFont(new Font("DigtalFont.TTF", Font.BOLD, 20));
		jt_mines.setBorder(BorderFactory.createLoweredBevelBorder());
		jt_mines.setHorizontalAlignment(SwingConstants.LEFT);
		jt_mines.setForeground(Color.RED);
		jt_mines.setBackground(Color.black);
		jt_time.setColumns(3);
		jt_time.setBorder(BorderFactory.createLoweredBevelBorder());
		jt_time.setFont(new Font("DigtalFont.TTF", Font.BOLD, 20));
		jt_time.setForeground(Color.RED);
		jt_time.setBackground(Color.black);
		jt_time.setText("000");
		btnsmile.setIcon(new ImageIcon("resources\\new game.gif"));
		btnsmile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnsmile.setIcon(new ImageIcon("resources\\new game.gif"));
				insertBoardPanelDependsOnSelectedMenu();
			}
		});
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		controlPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		controlPanel.add(jt_mines);
		controlPanel.add(btnsmile);
		controlPanel.add(jt_time);
		frame.getContentPane().add(controlPanel, BorderLayout.NORTH);
		initTimer();
		frame.setVisible(true);
	}

	/**
	 * this method creates and initializes BordPanel with CellButtons
	 * 
	 * @param width
	 * @param height
	 * @param bombCount
	 */
	public void addCellButtonsToBoardPanel(int width, int height, int bombCount) {
		System.out.println(" start addCellButtonsToBoardPanel()");
		setBombCountIntoControlPanel(bombCount);
		if (boardPanel != null) {
			frame.getContentPane().remove(boardPanel);
		}
		board = new Board(width, height, bombCount);
		boardPanel = new BoardPanel(board);
		CellButton[][] cellButtons = board.getCellButtons();
		if (cellButtons != null) {
			for (int x = 0; x != cellButtons.length; x++) {
				for (int y = 0; y != cellButtons[0].length; y++) {
					boardPanel.add(cellButtons[x][y]);
				}
			}
		}
		boardPanel.revalidate();
		frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
		frame.pack();
	}

	/**
	 * sets menu into Frame;
	 */
	public void setmenu() {
		final JMenuBar bar = new JMenuBar();
		final JMenu game = new JMenu("Game");
		final JMenu help = new JMenu("Help");
		final JMenuItem newGame = new JMenuItem("new game");	
		final JMenuItem helpitem = new JMenuItem("help");
		final JMenuItem exit = new JMenuItem("Exit");	
		final JCheckBoxMenuItem beginner = new JCheckBoxMenuItem("Begineer");
		final JCheckBoxMenuItem intermediate = new JCheckBoxMenuItem("Intermediate");
		final JCheckBoxMenuItem expert = new JCheckBoxMenuItem("Expert");
		final JCheckBoxMenuItem custom = new JCheckBoxMenuItem("Custom");
		
		ButtonGroup status = new ButtonGroup();
		status.add(beginner);
		status.add(intermediate);
		status.add(expert);
		status.add(custom);
		game.add(newGame);
		game.addSeparator();
		game.add(beginner);
		game.add(intermediate);
		game.add(expert);
		// game.add(custom);
		game.addSeparator();
		game.add(exit);
		help.add(helpitem);
		bar.add(game);
		bar.add(help);
		frame.setJMenuBar(bar);
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertBoardPanelDependsOnSelectedMenu();

			}
		});
		beginner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beginerState = beginner.getState();
				setIntermediateState(false);
				setExpertState(false);
				addCellButtonsToBoardPanel(Constants.BOARD_WIDTH_EASY, Constants.BOARD_HEIGHT_EASY,
						Constants.COUNT_OF_BOMBS_EASY);
				initTimer();

			}
		});

		intermediate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intermediateState = intermediate.getState();
				setBeginerState(false);
				setExpertState(false);
				addCellButtonsToBoardPanel(Constants.BOARD_WIDTH_MEDIUM, Constants.BOARD_HEIGHT_MEDIUM,
						Constants.COUNT_OF_BOMBS_MEDIUM);
				initTimer();

			}
		});
		expert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expertState = expert.getState();
				setBeginerState(false);
				setIntermediateState(false);
				addCellButtonsToBoardPanel(Constants.BOARD_WIDTH_EXPERT, Constants.BOARD_HEIGHT_EXPERT,
						Constants.COUNT_OF_BOMBS_EXPERT);
				initTimer();

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

	public static void setBombCountIntoControlPanel(Integer flagCount) {
		jt_mines.setText(flagCount.toString());
		// jt_mines.repaint();
	}

	public void insertBoardPanelDependsOnSelectedMenu() {
		jt_time.setText("000");
		if (isExpertState()) {
			addCellButtonsToBoardPanel(Constants.BOARD_WIDTH_EXPERT, Constants.BOARD_HEIGHT_EXPERT,
					Constants.COUNT_OF_BOMBS_EXPERT);
		} else if (isIntermediateState()) {
			addCellButtonsToBoardPanel(Constants.BOARD_WIDTH_MEDIUM, Constants.BOARD_HEIGHT_MEDIUM,
					Constants.COUNT_OF_BOMBS_MEDIUM);
		} else if (isBeginerState()) {
			addCellButtonsToBoardPanel(Constants.BOARD_WIDTH_EASY, Constants.BOARD_HEIGHT_EASY,
					Constants.COUNT_OF_BOMBS_EASY);
		} else
			addCellButtonsToBoardPanel(Constants.BOARD_WIDTH_EASY, Constants.BOARD_HEIGHT_EASY,
					Constants.COUNT_OF_BOMBS_EASY);
		initTimer();
	}

	public static void setSadBtnSmileIcon() {
		btnsmile.setIcon(new ImageIcon("resources\\crape.gif"));

	}

}
