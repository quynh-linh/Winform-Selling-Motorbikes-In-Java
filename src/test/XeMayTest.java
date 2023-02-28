package test;

import javax.swing.UIManager;
import GUI.loginView;

public class XeMayTest {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new loginView();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
