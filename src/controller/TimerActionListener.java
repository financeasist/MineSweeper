package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class TimerActionListener implements ActionListener {
	private long count;
	private JTextField jt_time;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		displayElapsedTime(count);
		count++;
	}

	public void setTextField(JTextField jt_time){
		this.jt_time = jt_time;
	}
	
	public void resetCount() {
		count = 0;
	}

	private void displayElapsedTime(long elapsedTime) {

		if (elapsedTime >= 0 && elapsedTime < 9) {
			jt_time.setText("00" + elapsedTime);
		} else if (elapsedTime > 9 && elapsedTime < 99) {
			jt_time.setText("0" + elapsedTime);
		} else if (elapsedTime > 99 && elapsedTime < 999) {
			jt_time.setText("" + elapsedTime);
		}
	}
}
