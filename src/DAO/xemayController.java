package DAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.xemayGUI;

public class xemayController implements ActionListener,MouseListener {
	private xemayGUI gui;
	
	public xemayController(xemayGUI gui) {
		this.gui = gui;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.gui.hienThongTinXeMay();
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
			this.gui.addXeMay();
		} else if (src.equals("Sửa")) {
			this.gui.editXeMay();
		} else if (src.equals("Xóa")) {
			this.gui.removeRowXeMay();
		} else if (src.equals("Tải ảnh")) {
			this.gui.xuLyChonAnh();
		} else if (src.equals("Import Excel")) {
			this.gui.xuLyNhapFileExcel();
		}  else if (src.equals("Export Excel")) {
			this.gui.ExportExcel();
		} 
	}

}
