package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import BUS.khachHangBUS;
import DTO.KhachHangDTO;

import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class ChiTietKhachHang extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_maKH;
	private JTextField textField_tenKh;
	private JTextField textField_diaChi;
	private JTextField textField_sdt;
	private JTextField textField_Sex;
	private JTextField textField_MaKHNEW;
	private JTextField textField_tenKhNEW;
	private JTextField textField_diaChiNEW;
	private JTextField textField_sdtNEW;
	private JRadioButton rdbtnN;
	private JRadioButton rdbtnNewRadioButton;
	private ButtonGroup buttonGroup_PK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
//			ChiTietKhachHang dialog = new ChiTietKhachHang();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ChiTietKhachHang(String maKH, String tenKH, String diaChi, int sdt, String gioTinh) {
		this.init();
		this.textField_maKH.setText(maKH);
		this.textField_tenKh.setText(tenKH);
		this.textField_diaChi.setText(diaChi);
		this.textField_sdt.setText(String.valueOf(sdt));
		this.textField_Sex.setText(gioTinh);
		this.dispose();
	}

	public void init() {
		setBounds(100, 100, 491, 362);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 475, 324);
			contentPanel.add(tabbedPane);
			{
				JPanel panel_thongTin = new JPanel();
				panel_thongTin.setBackground(Color.DARK_GRAY);
				tabbedPane.addTab("Thông tin khách hàng", null, panel_thongTin, null);
				panel_thongTin.setLayout(null);
				{
					JLabel lblNewLabel = new JLabel("THÔNG TIN KHÁCH HÀNG");
					lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 20));
					lblNewLabel.setForeground(Color.WHITE);
					lblNewLabel.setBounds(10, 11, 464, 31);
					panel_thongTin.add(lblNewLabel);
				}
				{
					JPanel panel = new JPanel();
					panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					panel.setBackground(Color.DARK_GRAY);
					panel.setBounds(10, 42, 450, 231);
					panel_thongTin.add(panel);
					panel.setLayout(null);
					{
						JLabel lblNewLabel_1 = new JLabel("Mã khách hàng");
						lblNewLabel_1.setForeground(Color.WHITE);
						lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
						lblNewLabel_1.setBounds(10, 18, 154, 23);
						panel.add(lblNewLabel_1);
					}
					{
						textField_maKH = new JTextField();
						textField_maKH.setBounds(146, 11, 276, 23);
						panel.add(textField_maKH);
						textField_maKH.setColumns(10);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("Tên khách hàng");
						lblNewLabel_1.setForeground(Color.WHITE);
						lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
						lblNewLabel_1.setBounds(10, 46, 154, 23);
						panel.add(lblNewLabel_1);
					}
					{
						textField_tenKh = new JTextField();
						textField_tenKh.setColumns(10);
						textField_tenKh.setBounds(146, 45, 276, 23);
						panel.add(textField_tenKh);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("Địa chỉ");
						lblNewLabel_1.setForeground(Color.WHITE);
						lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
						lblNewLabel_1.setBounds(10, 81, 154, 23);
						panel.add(lblNewLabel_1);
					}
					{
						textField_diaChi = new JTextField();
						textField_diaChi.setColumns(10);
						textField_diaChi.setBounds(146, 80, 276, 23);
						panel.add(textField_diaChi);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("Số điện thoại");
						lblNewLabel_1.setForeground(Color.WHITE);
						lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
						lblNewLabel_1.setBounds(10, 116, 154, 23);
						panel.add(lblNewLabel_1);
					}
					{
						textField_sdt = new JTextField();
						textField_sdt.setColumns(10);
						textField_sdt.setBounds(146, 115, 276, 23);
						panel.add(textField_sdt);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("Giới tính");
						lblNewLabel_1.setForeground(Color.WHITE);
						lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
						lblNewLabel_1.setBounds(10, 151, 154, 23);
						panel.add(lblNewLabel_1);
					}
					{
						textField_Sex = new JTextField();
						textField_Sex.setColumns(10);
						textField_Sex.setBounds(146, 150, 276, 23);
						panel.add(textField_Sex);
					}
					{
						JButton btnNewButton = new JButton("Cập nhập");
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								UpdateKh();
							}
						});
						btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
						btnNewButton.setBounds(169, 195, 131, 23);
						panel.add(btnNewButton);
					}
				}
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Thêm", null, panel, null);
				panel.setLayout(null);
				{
					JPanel panel_1 = new JPanel();
					panel_1.setBackground(Color.DARK_GRAY);
					panel_1.setBounds(0, 0, 470, 324);
					panel.add(panel_1);
					panel_1.setLayout(null);
					{
						JLabel lblNewLabel_2 = new JLabel("Thêm khách hàng");
						lblNewLabel_2.setForeground(Color.WHITE);
						lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
						lblNewLabel_2.setBounds(10, 11, 450, 24);
						panel_1.add(lblNewLabel_2);
					}
					{
						JPanel panel_2 = new JPanel();
						panel_2.setBackground(Color.DARK_GRAY);
						panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
						panel_2.setBounds(10, 37, 450, 248);
						panel_1.add(panel_2);
						panel_2.setLayout(null);
						{
							JLabel lblNewLabel_1 = new JLabel("Mã khách hàng");
							lblNewLabel_1.setForeground(Color.WHITE);
							lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
							lblNewLabel_1.setBounds(10, 18, 126, 23);
							panel_2.add(lblNewLabel_1);
						}
						{
							textField_MaKHNEW = new JTextField();
							textField_MaKHNEW.setColumns(10);
							textField_MaKHNEW.setBounds(146, 11, 276, 23);
							panel_2.add(textField_MaKHNEW);
						}
						{
							textField_tenKhNEW = new JTextField();
							textField_tenKhNEW.setColumns(10);
							textField_tenKhNEW.setBounds(146, 45, 276, 23);
							panel_2.add(textField_tenKhNEW);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("Tên khách hàng");
							lblNewLabel_1.setForeground(Color.WHITE);
							lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
							lblNewLabel_1.setBounds(10, 46, 126, 23);
							panel_2.add(lblNewLabel_1);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("Địa chỉ");
							lblNewLabel_1.setForeground(Color.WHITE);
							lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
							lblNewLabel_1.setBounds(10, 81, 126, 23);
							panel_2.add(lblNewLabel_1);
						}
						{
							textField_diaChiNEW = new JTextField();
							textField_diaChiNEW.setColumns(10);
							textField_diaChiNEW.setBounds(146, 80, 276, 23);
							panel_2.add(textField_diaChiNEW);
						}
						{
							textField_sdtNEW = new JTextField();
							textField_sdtNEW.setColumns(10);
							textField_sdtNEW.setBounds(146, 115, 276, 23);
							panel_2.add(textField_sdtNEW);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("Số điện thoại");
							lblNewLabel_1.setForeground(Color.WHITE);
							lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
							lblNewLabel_1.setBounds(10, 116, 126, 23);
							panel_2.add(lblNewLabel_1);
						}
						{
							JLabel lblNewLabel_1 = new JLabel("Giới tính");
							lblNewLabel_1.setForeground(Color.WHITE);
							lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
							lblNewLabel_1.setBounds(10, 151, 126, 23);
							panel_2.add(lblNewLabel_1);
						}
						{
							rdbtnNewRadioButton = new JRadioButton("Nam");
							rdbtnNewRadioButton.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
							rdbtnNewRadioButton.setForeground(Color.WHITE);
							rdbtnNewRadioButton.setOpaque(false);
							rdbtnNewRadioButton.setBounds(146, 150, 109, 23);
							panel_2.add(rdbtnNewRadioButton);
						}
						{
							rdbtnN = new JRadioButton("Nữ");
							rdbtnN.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
							rdbtnN.setForeground(Color.WHITE);
							rdbtnN.setOpaque(false);
							rdbtnN.setBounds(279, 150, 109, 23);
							panel_2.add(rdbtnN);
						}
						{
							buttonGroup_PK = new ButtonGroup();
							buttonGroup_PK.add(rdbtnNewRadioButton);
							buttonGroup_PK.add(rdbtnN);
						}
						{
							JButton btnNewButton_1 = new JButton("Thêm khách hàng");
							btnNewButton_1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									ThemKH();
								}
							});
							btnNewButton_1.setBounds(162, 202, 126, 23);
							panel_2.add(btnNewButton_1);
						}
					}
				}
			}
		}
	}

	public void UpdateKh() {
		try {
			String maKh = this.textField_maKH.getText();
			String tenKh = this.textField_tenKh.getText();
			String diaChi = this.textField_diaChi.getText();
			int sdt = Integer.valueOf(this.textField_sdt.getText());
			String sex = this.textField_Sex.getText();
			KhachHangDTO hangDTO = new KhachHangDTO(maKh, tenKh, diaChi, sdt, sex);
			khachHangBUS bus = new khachHangBUS();
			JOptionPane.showMessageDialog(contentPanel, bus.sua(hangDTO));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ThemKH() {
		try {
			khachHangBUS bus = new khachHangBUS();
			String maKh = this.textField_MaKHNEW.getText();
			String tenKh = this.textField_tenKhNEW.getText();
			String diaChi = this.textField_diaChiNEW.getText();
			int sdt = Integer.valueOf(this.textField_sdtNEW.getText());
			String gioiTinh = "";
			if (rdbtnNewRadioButton.isSelected()) {
				gioiTinh = "Nam";
			} else {
				gioiTinh = "Nữ";
			}
			KhachHangDTO kh = new KhachHangDTO();
			kh.setMaKH(maKh);
			kh.setTenKH(tenKh);
			kh.setDiaChi(diaChi);
			kh.setSDT(sdt);
			kh.setGioiTinh(gioiTinh);
			JOptionPane.showMessageDialog(contentPanel, bus.them(kh));
			BanHangGUI bh = new BanHangGUI();
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
