package DAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.loginView;

public class adminController implements ActionListener {
	private loginView view;

	public adminController(loginView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Đăng nhập")) {
			this.view.loginAdmin();
		}
	} 
	
}
