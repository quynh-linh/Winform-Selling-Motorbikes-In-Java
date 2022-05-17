package DAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.DanhMucSPView;

public class loaixeController implements ActionListener , MouseListener {

	private DanhMucSPView view;
	
	public loaixeController(DanhMucSPView view) {
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.view.hienThongTinLoaiXe();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Thêm")) {
			this.view.addLoaiXe();
		} else if (src.equals("Sửa")) {
			this.view.editLoaiXe();
		}else if (src.equals("Xóa")) {
			this.view.removeRowLoaiXe();
		}
	}
}
