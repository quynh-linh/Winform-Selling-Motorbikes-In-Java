package DAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.NhaCungCapView;

public class NhaCungCapController implements ActionListener , MouseListener {
	private NhaCungCapView view ;
	@Override
	
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Thêm")) {
			this.view.addNhaCungCap();
		} else if (src.equals("Sửa")) {
			this.view.editNhaCungCap();
		} else if (src.equals("Xóa")) {
			this.view.removeRowNCC();
		} 
	}
	public NhaCungCapController(NhaCungCapView view) {
		super();
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.view.hienThongTinNCC();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
