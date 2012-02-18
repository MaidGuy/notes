package pl.edu.pjwstk.mpr.notes.app;

import java.awt.EventQueue;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
