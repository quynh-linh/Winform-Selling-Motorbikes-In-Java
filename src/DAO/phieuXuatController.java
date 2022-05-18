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
			if(this.view.textField_giaMAX.getText().equals("") 
					&& this.view.textField_giaMIN.getText().equals("") 
					&& this.view.buttonGroup_PK.isSelected(null)) {
				this.view.searchName();
				System.out.println("Name");
			} else if(this.view.textField_giaMAX.getText().equals("") 
					&& this.view.textField_giaMIN.getText().equals("") 
					&& this.view.textField_nameXe.getText().equals("")) {
				this.view.searchLoaiXe();
				System.out.println("Loại xe");
			} else if(this.view.textField_nameXe.getText().equals("")
					&& this.view.buttonGroup_PK.isSelected(null)) {
				this.view.searchGiaXe();
				System.out.println("Giá xe");
			} else {
				this.view.search();
				System.out.println("Cả 3");
			}
		} else if(src.equals("Xóa")) {
			this.view.xoaKhoiHangCHO();
		}
		
	}
}