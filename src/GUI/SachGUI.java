 package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.SachBUS;
import DTO.SACHDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SachGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_maS;
	private JTextField textField_tenS;
	private JTextField textField_soL;
	private JTextField textField_giaSach;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SachGUI frame = new SachGUI();
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
	public SachGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập mã sách");
		lblNewLabel.setBounds(91, 49, 95, 14);
		contentPane.add(lblNewLabel);
		
		textField_maS = new JTextField();
		textField_maS.setBounds(235, 46, 180, 20);
		contentPane.add(textField_maS);
		textField_maS.setColumns(10);
		
		JLabel lblNhpTnSch = new JLabel("Nhập tên sách");
		lblNhpTnSch.setBounds(91, 80, 95, 14);
		contentPane.add(lblNhpTnSch);
		
		textField_tenS = new JTextField();
		textField_tenS.setColumns(10);
		textField_tenS.setBounds(235, 77, 180, 20);
		contentPane.add(textField_tenS);
		
		JLabel lblNhpSLng = new JLabel("Nhập số lượng");
		lblNhpSLng.setBounds(91, 121, 95, 14);
		contentPane.add(lblNhpSLng);
		
		textField_soL = new JTextField();
		textField_soL.setColumns(10);
		textField_soL.setBounds(235, 118, 180, 20);
		contentPane.add(textField_soL);
		
		JLabel lblNhpGiSch = new JLabel("Nhập giá sách");
		lblNhpGiSch.setBounds(91, 167, 95, 14);
		contentPane.add(lblNhpGiSch);
		
		textField_giaSach = new JTextField();
		textField_giaSach.setColumns(10);
		textField_giaSach.setBounds(235, 164, 180, 20);
		contentPane.add(textField_giaSach);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themSach();
			}
		});
		btnNewButton.setBounds(209, 228, 89, 23);
		contentPane.add(btnNewButton);
	}
	public void themSach() {
		try {
			SachBUS bus = new SachBUS();
			String maSach = this.textField_maS.getText();
			String tenSach = this.textField_tenS.getText();
			int soLuong = Integer.valueOf(this.textField_soL.getText());
			float gia = Float.valueOf(this.textField_giaSach.getText());
			SACHDTO sach = new SACHDTO(maSach, tenSach, soLuong, gia);
			JOptionPane.showMessageDialog(this, bus.themSach(sach));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
