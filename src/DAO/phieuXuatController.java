package DAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.BanHangGUI;

public class phieuXuatController implements ActionListener , MouseListener {

	private BanHangGUI view ;
	
	public phieuXuatController(BanHangGUI view) {
		this.view = view;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.view.hienThongTinXeMay();
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
		if (src.equals("Mua")) {
			this.view.loadDBtoNhap();
		} else if(src.equals("Xác nhận")) {
			try {
				this.view.xacnhanNhap();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(src.equals("Search")) {
			this.view.search();
			this.view.searchName();
			this.view.searchGiaXe();
			this.view.searchLoaiXe();
		}
		
	}
}