package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BUS.adminBUS;
import DAO.adminController;
import DTO.adminDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.io.File;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class loginView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_login;
	private JPasswordField passwordField;
	private JButton btn_login;

	/**
	 * Launch the application.
	 */
	public loginView() {
		this.init();
	}

	public void init() {
		Container content = getContentPane();
		setTitle("Đăng nhập");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Quynh Linh\\OneDrive\\Pictures\\Cuộn phim\\ImgeIconJava\\Administrator-2-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 488);
		setLocationRelativeTo(null);
		contentPane = new ImagePanel(new ImageIcon("Assets/ImgeIconJava/1907944.jpg").getImage());
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
		contentPane.setLayout(null);

		adminController controller = new adminController(this);
		JLabel lblNewLabel = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(135, 213, 145, 22);
		contentPane.add(lblNewLabel);

		textField_login = new JTextField();
		textField_login.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		textField_login.setForeground(Color.WHITE);
		textField_login.setOpaque(false);
		textField_login.setBounds(352, 210, 240, 28);
		contentPane.add(textField_login);
		textField_login.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		passwordField.setForeground(Color.WHITE);
		passwordField.setOpaque(false);
		passwordField.setBounds(352, 275, 240, 28);
		contentPane.add(passwordField);

		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u");
		lblMtKhu.setForeground(Color.WHITE);
		lblMtKhu.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblMtKhu.setBounds(135, 278, 90, 22);
		contentPane.add(lblMtKhu);

		btn_login = new JButton("\u0110\u0103ng nh\u1EADp");
		btn_login.setBounds(315, 380, 147, 36);
		btn_login.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btn_login.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent evt) {
				btn_login.setBackground(null);
			}
		});
		btn_login.addActionListener(controller);
		contentPane.add(btn_login);
		content.add(contentPane);
		this.setVisible(true);
	}

	public void loginAdmin() {
		try {
			adminBUS bll = new adminBUS();
			if (this.textField_login.getText().equals(" ") || this.passwordField.getText().equals("")) {
				System.out.println("2");
				JOptionPane.showMessageDialog(this, "Điền đầy đủ thông tin");
			} else {
				adminDTO ad = new adminDTO();
				ad.setAdminUser(textField_login.getText());
				ad.setAdminPass(new String(passwordField.getPassword()));
				if (bll.kiemTraDangNhap(ad)) {
					JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
					System.out.println("1");
					TrangChuView home = new TrangChuView();
					home.show();
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "Đăng nhập thất bại");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
