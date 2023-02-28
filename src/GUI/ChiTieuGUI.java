package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.ChiTieuBUS;
import DAO.ChiTieuDAO;
import DAO.xemayDAO;
import DTO.ChiTieuDTO;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChiTieuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_maNV;
	private JLabel lblNhpHo;
	private JTextField textField_ho;
	private JLabel lblNhpTn;
	private JTextField textField_ten;
	private JLabel lblNgySinh;
	private JTextField textField_NS;
	private JLabel lblNgyLm;
	private JTextField textField_NL;
	private JButton btn_CApNhap;
	private JButton btn_CApNhap_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiTieuGUI frame = new ChiTieuGUI();
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
	public ChiTieuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_Xoa = new JButton("Xóa");
		btn_Xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaNV();
			}
		});
		btn_Xoa.setBounds(88, 202, 89, 23);
		contentPane.add(btn_Xoa);
		
		textField_maNV = new JTextField();
		textField_maNV.setBounds(206, 21, 101, 20);
		contentPane.add(textField_maNV);
		textField_maNV.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nhập mã nhân viên");
		lblNewLabel.setBounds(60, 24, 106, 14);
		contentPane.add(lblNewLabel);
		
		lblNhpHo = new JLabel("Nhập họ");
		lblNhpHo.setBounds(60, 55, 106, 14);
		contentPane.add(lblNhpHo);
		
		textField_ho = new JTextField();
		textField_ho.setColumns(10);
		textField_ho.setBounds(206, 52, 101, 20);
		contentPane.add(textField_ho);
		
		lblNhpTn = new JLabel("Nhập tên");
		lblNhpTn.setBounds(60, 88, 106, 14);
		contentPane.add(lblNhpTn);
		
		textField_ten = new JTextField();
		textField_ten.setColumns(10);
		textField_ten.setBounds(206, 85, 101, 20);
		contentPane.add(textField_ten);
		
		lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setBounds(60, 119, 106, 14);
		contentPane.add(lblNgySinh);
		
		textField_NS = new JTextField();
		textField_NS.setColumns(10);
		textField_NS.setBounds(206, 116, 101, 20);
		contentPane.add(textField_NS);
		
		lblNgyLm = new JLabel("Ngày làm");
		lblNgyLm.setBounds(60, 150, 106, 14);
		contentPane.add(lblNgyLm);
		
		textField_NL = new JTextField();
		textField_NL.setColumns(10);
		textField_NL.setBounds(206, 147, 101, 20);
		contentPane.add(textField_NL);
		
		btn_CApNhap = new JButton("Cập nhập");
		btn_CApNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sua();
			}
		});
		btn_CApNhap.setBounds(228, 202, 89, 23);
		contentPane.add(btn_CApNhap);
		
		btn_CApNhap_1 = new JButton("Tìm nhân viên");
		btn_CApNhap_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String maNV = textField_maNV.getText();
					ChiTieuBUS bus = new ChiTieuBUS();
					JOptionPane.showMessageDialog(contentPane, bus.timKiem(maNV)) ;
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_CApNhap_1.setBounds(335, 202, 89, 23);
		contentPane.add(btn_CApNhap_1);
	}
	public void  xoaNV() {
		try {
			ChiTieuBUS dao = new ChiTieuBUS();
			String maNV = this.textField_maNV.getText();
			JOptionPane.showMessageDialog(this, dao.xoaNVBUS(maNV));
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}	
	public void sua() {
		try {
			ChiTieuBUS dao = new ChiTieuBUS();
			String maNV = this.textField_maNV.getText();
			String ho = this.textField_ho.getText();
			String ten = this.textField_ten.getText();
			String ns = this.textField_NS.getText();
			String nl = this.textField_NL.getText();
			ChiTieuDTO dto = new  ChiTieuDTO(maNV, ho, ten, ns, nl);
			JOptionPane.showMessageDialog(this, dao.suaNVBUS(dto));		
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
