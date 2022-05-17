package DAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.PhieuNhapView;

public class phieuNhapController implements ActionListener , MouseListener {

	private PhieuNhapView view ;
	
	public phieuNhapController(PhieuNhapView view) {
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
		if (src.equals("Nhập")) {
			this.view.loadDBtoNhap();
		} else if(src.equals("Xác nhận")) {
			try {
				this.view.xacnhanNhap();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(src.equals("Xóa")) {
			this.view.thuchienXoa();
		}
	}

}
