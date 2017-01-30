package controller;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImgManager {
	public static final String BUTTON_CLOSED = "resources/closed.gif";
	public static final String BUTTON_EMPTY = "resources/0.gif";
	public static final String BUTTON_ONE = "resources/1.gif";
	public static final String BUTTON_TWO = "resources/2.gif";
	public static final String BUTTON_THREE = "resources/3.gif";
	public static final String BUTTON_FOUR = "resources/4.gif";
	public static final String BUTTON_FIVE = "resources/5.gif";
	public static final String BUTTON_SIX = "resources/6.gif";
	public static final String BUTTON_SEVEN = "resources/7.gif";
	public static final String BUTTON_EIGHT = "resources/8.gif";
	public static final String BUTTON_BOMB = "resources/bomb.gif";
	public static final String BUTTON_BANG = "resources/bang.gif";
	public static final String BUTTON_FLAG = "resources/flag0.gif";

	public static ImageIcon GetImg(String ImgType) {
		ImageIcon img = null;
		try {
			img = new ImageIcon(ImageIO.read(new File(ImgType)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
