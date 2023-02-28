package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.NhatKyBUS;
import DTO.NhanVienDTO;
import DTO.NhatKyDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NhatKyGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_ngay;
	private JTextField textField_mucChi;
	private JTextField textField_soTien;
	private JLabel lblNhpGhiCh;
	private JTextField textField_ghiChu;
	private JLabel lblTngTin;
	private JTextField textField_tongTien;
	private JButton btnNewButton_them;
	private JButton btnTongTien;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhatKyGUI frame = new NhatKyGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NhatKyGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập id");
		lblNewLabel.setBounds(71, 38, 73, 14);
		contentPane.add(lblNewLabel);
		
		textField_id = new JTextField();
		textField_id.setBounds(182, 35, 146, 20);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		textField_ngay = new JTextField();
		textField_ngay.setColumns(10);
		textField_ngay.setBounds(182, 66, 146, 20);
		contentPane.add(textField_ngay);
		
		JLabel lblNhpNgy = new JLabel("Nhập ngày");
		lblNhpNgy.setBounds(71, 69, 73, 14);
		contentPane.add(lblNhpNgy);
		
		textField_mucChi = new JTextField();
		textField_mucChi.setColumns(10);
		textField_mucChi.setBounds(182, 110, 146, 20);
		contentPane.add(textField_mucChi);
		
		JLabel lblNhpMcChi = new JLabel("Nhập mức chi");
		lblNhpMcChi.setBounds(71, 113, 73, 14);
		contentPane.add(lblNhpMcChi);
		
		textField_soTien = new JTextField();
		textField_soTien.setColumns(10);
		textField_soTien.setBounds(182, 147, 146, 20);
		contentPane.add(textField_soTien);
		
		JLabel lblNhpSTin = new JLabel("Nhập số tiền");
		lblNhpSTin.setBounds(71, 150, 73, 14);
		contentPane.add(lblNhpSTin);
		
		lblNhpGhiCh = new JLabel("Nhập ghi chú");
		lblNhpGhiCh.setBounds(71, 192, 73, 14);
		contentPane.add(lblNhpGhiCh);
		
		textField_ghiChu = new JTextField();
		textField_ghiChu.setColumns(10);
		textField_ghiChu.setBounds(182, 189, 146, 20);
		contentPane.add(textField_ghiChu);
		
		lblTngTin = new JLabel("Tổng tiền");
		lblTngTin.setBounds(71, 223, 73, 14);
		contentPane.add(lblTngTin);
		
		textField_tongTien = new JTextField();
		textField_tongTien.setColumns(10);
		textField_tongTien.setBounds(182, 220, 146, 20);
		contentPane.add(textField_tongTien);
		
		btnNewButton_them = new JButton("Thêm");
		btnNewButton_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themNhatKy();
			}
		});
		btnNewButton_them.setBounds(94, 270, 89, 23);
		contentPane.add(btnNewButton_them);
		
		btnTongTien = new JButton("Tông tiền");
		btnTongTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NhatKyBUS bus = new NhatKyBUS();
					String kq = String.valueOf(bus.tongSoTien());
					textField_tongTien.setText(kq);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTongTien.setBounds(221, 270, 89, 23);
		contentPane.add(btnTongTien);
	}
	public void themNhatKy() {
		try {
			NhatKyBUS bus = new NhatKyBUS();
			String id = this.textField_id.getText();
			String ngay = this.textField_ngay.getText();
			float mucChi = Float.valueOf(this.textField_mucChi.getText());
			float soTien = Float.valueOf(this.textField_soTien.getText());
			String ghiCHu = this.textField_ghiChu.getText();
			NhatKyDTO nk = new NhatKyDTO(id, ngay, mucChi, soTien, ghiCHu);
			JOptionPane.showMessageDialog(this, bus.themNhatKy(nk));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
