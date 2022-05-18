package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import DTO.ChiTietHoaDonXuat;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.XeMayDTO;

import javax.swing.table.DefaultTableModel;

import BUS.khachHangBUS;
import BUS.loaixeBUS;
import BUS.NhanVienBUS;
import BUS.xeMayBUS;
import DAO.KhachHangDAO;
import DAO.phieuXuatController;
import DAO.xemayDAO;

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JSlider;

public class BanHangGUI extends JFrame {

	private JPanel contentPane;
	private JTable table_phieuNhap;
	private JTable table_Sp;
	private JTextField textField_MaSp;
	private JTextField textField_tenSp;
	private JTextField textField_soLuong;
	private JTextField textField_donGia;
	private JTextField textField_LoaiXe;
	private XeMayDTO xMayDTO;
	File fileAnhSP;
	private DefaultTableModel model, model_choxn;
	private JComboBox<String> comboBox_NhanVien;
	private JComboBox<String> comboBox_KH;
	private JTextField textField_maPX;
	private xeMayBUS mayBLL = new xeMayBUS();
	private loaixeBUS lxBll = new loaixeBUS();
	private JLabel lblNewLabel_image;
	public JTextField textField_nameXe;
	private JDateChooser dateChooser;
	public JTextField textField_giaMIN;
	public JTextField textField_giaMAX;
	public ButtonGroup buttonGroup_PK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					BanHangGUI frame = new BanHangGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BanHangGUI() throws Exception {
		this.init();
		loadDataIntoJTable();
		nhapThgTinKH();
		nhapThongTin_NV();
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setForeground(Color.BLACK);
		setTitle("Bán hàng");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets/ImgeIconJava/motorcycle-icon.png"));
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1158, 730);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		phieuXuatController controller = new phieuXuatController(this);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.DARK_GRAY);
		tabbedPane.setBackground(Color.DARK_GRAY);
		tabbedPane.setBounds(0, 0, 253, 691);
		contentPane.add(tabbedPane);

		JPanel panel_danhmuc = new JPanel();
		tabbedPane.addTab("Home", null, panel_danhmuc, null);
		panel_danhmuc.setForeground(Color.WHITE);
		panel_danhmuc.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_danhmuc.setBackground(Color.DARK_GRAY);
//		panel_danhmuc.setBounds(0, 26, 253, 665);
//		contentPane.add(panel_danhmuc);
		panel_danhmuc.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(0, 0, 253, 61);
		panel_danhmuc.add(panel_3);
		panel_3.setLayout(null);

		// Coppy
		JLabel lblXeMay = new JLabel("Xe M\u00E1y");
		lblXeMay.setHorizontalAlignment(SwingConstants.CENTER);
		lblXeMay.setIcon(new ImageIcon("Assets/ImgeIconJava/motorcycle-icon.png"));
		lblXeMay.setForeground(Color.WHITE);
		lblXeMay.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblXeMay.setBounds(0, 0, 253, 60);
		panel_3.add(lblXeMay);

		JButton btn_DSsanPham = new JButton("Danh s\u00E1ch s\u1EA3n ph\u1EA9m");
		btn_DSsanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				xemayGUI View;
				try {
					View = new xemayGUI();
					View.show();
					setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btn_DSsanPham.setIcon(new ImageIcon("Assets/ImgeIconJava/text-editor-icon.png"));
		btn_DSsanPham.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_DSsanPham.setHorizontalAlignment(SwingConstants.LEFT);
		btn_DSsanPham.setForeground(Color.WHITE);
		btn_DSsanPham.setBackground(Color.DARK_GRAY);
		btn_DSsanPham.setFocusable(false);
		btn_DSsanPham.setBorderPainted(false);
		btn_DSsanPham.setBounds(0, 231, 253, 31);
		panel_danhmuc.add(btn_DSsanPham);

		JButton btn_DMsanpham = new JButton("Danh m\u1EE5c s\u1EA3n ph\u1EA9m");
		btn_DMsanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String src = e.getActionCommand();
				if (src.equals("Danh mục sản phẩm")) {
					DanhMucSPView danhMucSPView = new DanhMucSPView();
					danhMucSPView.show();
					setVisible(false);
				}
			}
		});
		btn_DMsanpham.setIcon(new ImageIcon("Assets/ImgeIconJava/file-manager-icon.png"));
		btn_DMsanpham.setHorizontalAlignment(SwingConstants.LEFT);
		btn_DMsanpham.setForeground(Color.WHITE);
		btn_DMsanpham.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_DMsanpham.setFocusable(false);
		btn_DMsanpham.setBorderPainted(false);
		btn_DMsanpham.setBackground(Color.DARK_GRAY);
		btn_DMsanpham.setBounds(0, 284, 253, 31);
		panel_danhmuc.add(btn_DMsanpham);

		JButton btn_DSNhanvien = new JButton("Danh s\u00E1ch nh\u00E2n vi\u00EAn");
		btn_DSNhanvien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nhanvienGUI view = new nhanvienGUI();
				view.show();
				setVisible(false);
			}
		});
		btn_DSNhanvien.setIcon(new ImageIcon("Assets/ImgeIconJava/calligra-krita-icon.png"));
		btn_DSNhanvien.setHorizontalAlignment(SwingConstants.LEFT);
		btn_DSNhanvien.setForeground(Color.WHITE);
		btn_DSNhanvien.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_DSNhanvien.setFocusable(false);
		btn_DSNhanvien.setBorderPainted(false);
		btn_DSNhanvien.setBackground(Color.DARK_GRAY);
		btn_DSNhanvien.setBounds(0, 390, 253, 31);
		panel_danhmuc.add(btn_DSNhanvien);

		JButton btn_DSKhachHang = new JButton("Danh s\u00E1ch kh\u00E1ch h\u00E0ng");
		btn_DSKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHangGUI kh = new KhachHangGUI();
				kh.show();
				setVisible(false);

			}
		});
		btn_DSKhachHang.setIcon(new ImageIcon("Assets/ImgeIconJava/gdebi-icon.png"));
		btn_DSKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		btn_DSKhachHang.setForeground(Color.WHITE);
		btn_DSKhachHang.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_DSKhachHang.setFocusable(false);
		btn_DSKhachHang.setBorderPainted(false);
		btn_DSKhachHang.setBackground(Color.DARK_GRAY);
		btn_DSKhachHang.setBounds(0, 443, 253, 31);
		panel_danhmuc.add(btn_DSKhachHang);

		JButton btn_HDNhap = new JButton("Danh s\u00E1ch h\u00F3a \u0111\u01A1n nh\u1EADp");
		btn_HDNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DanhSachPhieuNhap ds = new DanhSachPhieuNhap();
					ds.show();
					setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_HDNhap.setIcon(new ImageIcon("Assets/ImgeIconJava/gimp-icon.png"));
		btn_HDNhap.setHorizontalAlignment(SwingConstants.LEFT);
		btn_HDNhap.setForeground(Color.WHITE);
		btn_HDNhap.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_HDNhap.setFocusable(false);
		btn_HDNhap.setBorderPainted(false);
		btn_HDNhap.setBackground(Color.DARK_GRAY);
		btn_HDNhap.setBounds(0, 496, 253, 31);
		panel_danhmuc.add(btn_HDNhap);

		JButton btn_HDXuat = new JButton("Danh s\u00E1ch h\u00F3a \u0111\u01A1n xu\u1EA5t");
		btn_HDXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DanhSachPhieuXuat ds;
				try {
					ds = new DanhSachPhieuXuat();
					ds.show();
					setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btn_HDXuat.setIcon(new ImageIcon("Assets/ImgeIconJava/gnome-calendar-icon.png"));
		btn_HDXuat.setHorizontalAlignment(SwingConstants.LEFT);
		btn_HDXuat.setForeground(Color.WHITE);
		btn_HDXuat.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_HDXuat.setFocusable(false);
		btn_HDXuat.setBorderPainted(false);
		btn_HDXuat.setBackground(Color.DARK_GRAY);
		btn_HDXuat.setBounds(0, 549, 253, 31);
		panel_danhmuc.add(btn_HDXuat);

		JButton btn_Thongke = new JButton("Th\u1ED1ng k\u00EA s\u1EA3n ph\u1EA9m");
		btn_Thongke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKeGUI gui = new ThongKeGUI();
				gui.show();
				setVisible(false);
			}
		});
		btn_Thongke.setIcon(new ImageIcon("Assets/ImgeIconJava/Apps-calendar-icon (1).png"));
		btn_Thongke.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Thongke.setForeground(Color.WHITE);
		btn_Thongke.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_Thongke.setFocusable(false);
		btn_Thongke.setBorderPainted(false);
		btn_Thongke.setBackground(Color.DARK_GRAY);
		btn_Thongke.setBounds(0, 602, 253, 31);
		panel_danhmuc.add(btn_Thongke);

		JButton btn_home = new JButton("Trang chủ");
		btn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrangChuView chuView = new TrangChuView();
				chuView.show();
				setVisible(false);
			}
		});
		btn_home.setIcon(new ImageIcon("Assets/ImgeIconJava/Home-icon.png"));
		btn_home.setHorizontalAlignment(SwingConstants.LEFT);
		btn_home.setForeground(Color.WHITE);
		btn_home.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_home.setFocusable(false);
		btn_home.setBorderPainted(false);
		btn_home.setBackground(Color.DARK_GRAY);
		btn_home.setBounds(0, 72, 253, 31);
		panel_danhmuc.add(btn_home);

		JButton btn_ncc = new JButton("Danh sách nhà cung cấp");
		btn_ncc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhaCungCapView view = new NhaCungCapView();
				view.show();
				setVisible(false);
			}
		});
		btn_ncc.setIcon(new ImageIcon("Assets/ImgeIconJava/text-editor-icon.png"));
		btn_ncc.setHorizontalAlignment(SwingConstants.LEFT);
		btn_ncc.setForeground(Color.WHITE);
		btn_ncc.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_ncc.setFocusable(false);
		btn_ncc.setBorderPainted(false);
		btn_ncc.setBackground(Color.DARK_GRAY);
		btn_ncc.setBounds(0, 337, 253, 31);
		panel_danhmuc.add(btn_ncc);

		JButton btn_home_1 = new JButton("Bán hàng");
		btn_home_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanHangGUI bh;
				try {
					bh = new BanHangGUI();
					bh.show();
					setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btn_home_1.setIcon(new ImageIcon("Assets/ImgeIconJava/calligra-krita-icon.png"));
		btn_home_1.setHorizontalAlignment(SwingConstants.LEFT);
		btn_home_1.setForeground(Color.WHITE);
		btn_home_1.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_home_1.setFocusable(false);
		btn_home_1.setBorderPainted(false);
		btn_home_1.setBackground(Color.DARK_GRAY);
		btn_home_1.setBounds(0, 125, 253, 31);
		panel_danhmuc.add(btn_home_1);

		JButton btn_home_1_1 = new JButton("Nhập hàng");
		btn_home_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuNhapView pn;
				try {
					pn = new PhieuNhapView();
					pn.show();
					setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_home_1_1.setIcon(new ImageIcon("Assets/ImgeIconJava/motorcycle-icon.png"));
		btn_home_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		btn_home_1_1.setForeground(Color.WHITE);
		btn_home_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_home_1_1.setFocusable(false);
		btn_home_1_1.setBorderPainted(false);
		btn_home_1_1.setBackground(Color.DARK_GRAY);
		btn_home_1_1.setBounds(0, 178, 253, 31);
		panel_danhmuc.add(btn_home_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Tìm kiếm", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm sản phẩm");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 5, 228, 41);
		panel_1.add(lblNewLabel_2);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBackground(Color.DARK_GRAY);
		panel_6.setBounds(10, 266, 228, 270);
		panel_1.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Theo phân khối xe");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(10, 11, 208, 25);
		panel_6.add(lblNewLabel_3);

		JRadioButton cb_50cc = new JRadioButton("50cc");
		cb_50cc.setForeground(Color.WHITE);
		cb_50cc.setOpaque(false);
		cb_50cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_50cc.setBounds(6, 34, 97, 23);
		panel_6.add(cb_50cc);

		JRadioButton cb_75cc = new JRadioButton("75cc");
		cb_75cc.setForeground(Color.WHITE);
		cb_75cc.setOpaque(false);
		cb_75cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_75cc.setBounds(6, 71, 97, 23);
		panel_6.add(cb_75cc);

		JRadioButton cb_125cc = new JRadioButton("125cc");
		cb_125cc.setForeground(Color.WHITE);
		cb_125cc.setOpaque(false);
		cb_125cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_125cc.setBounds(6, 152, 97, 23);
		panel_6.add(cb_125cc);

		JRadioButton cb_100cc = new JRadioButton("100cc");
		cb_100cc.setForeground(Color.WHITE);
		cb_100cc.setOpaque(false);
		cb_100cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_100cc.setBounds(6, 111, 97, 23);
		panel_6.add(cb_100cc);

		JRadioButton cb_175cc = new JRadioButton("175cc");
		cb_175cc.setForeground(Color.WHITE);
		cb_175cc.setOpaque(false);
		cb_175cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_175cc.setBounds(6, 240, 97, 23);
		panel_6.add(cb_175cc);

		JRadioButton cb_150cc = new JRadioButton("150cc");
		cb_150cc.setForeground(Color.WHITE);
		cb_150cc.setOpaque(false);
		cb_150cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_150cc.setBounds(6, 194, 97, 23);
		panel_6.add(cb_150cc);

		JRadioButton cb_200cc = new JRadioButton("200cc");
		cb_200cc.setForeground(Color.WHITE);
		cb_200cc.setOpaque(false);
		cb_200cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_200cc.setBounds(121, 34, 97, 23);
		panel_6.add(cb_200cc);

		JRadioButton cb_225cc = new JRadioButton("225cc");
		cb_225cc.setForeground(Color.WHITE);
		cb_225cc.setOpaque(false);
		cb_225cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_225cc.setBounds(121, 71, 97, 23);
		panel_6.add(cb_225cc);

		JRadioButton cb_250cc = new JRadioButton("250cc");
		cb_250cc.setForeground(Color.WHITE);
		cb_250cc.setOpaque(false);
		cb_250cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_250cc.setBounds(121, 111, 97, 23);
		panel_6.add(cb_250cc);

		JRadioButton cb_275cc = new JRadioButton("275cc");
		cb_275cc.setForeground(Color.WHITE);
		cb_275cc.setOpaque(false);
		cb_275cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_275cc.setBounds(121, 152, 97, 23);
		panel_6.add(cb_275cc);

		JRadioButton cb_300cc = new JRadioButton("300cc");
		cb_300cc.setForeground(Color.WHITE);
		cb_300cc.setOpaque(false);
		cb_300cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_300cc.setBounds(121, 194, 97, 23);
		panel_6.add(cb_300cc);

		JRadioButton cb_325cc = new JRadioButton("325cc");
		cb_325cc.setForeground(Color.WHITE);
		cb_325cc.setOpaque(false);
		cb_325cc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_325cc.setBounds(121, 240, 97, 23);
		panel_6.add(cb_325cc);
		
		buttonGroup_PK = new ButtonGroup();
		buttonGroup_PK.add(cb_50cc);
		buttonGroup_PK.add(cb_75cc);
		buttonGroup_PK.add(cb_100cc);
		buttonGroup_PK.add(cb_125cc);
		buttonGroup_PK.add(cb_150cc);
		buttonGroup_PK.add(cb_175cc);
		buttonGroup_PK.add(cb_200cc);
		buttonGroup_PK.add(cb_225cc);
		buttonGroup_PK.add(cb_250cc);
		buttonGroup_PK.add(cb_275cc);
		buttonGroup_PK.add(cb_300cc);
		buttonGroup_PK.add(cb_325cc);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_7.setBackground(Color.DARK_GRAY);
		panel_7.setBounds(10, 153, 228, 87);
		panel_1.add(panel_7);
		panel_7.setLayout(null);

		JLabel lblNewLabel_3_1 = new JLabel("Theo giá xe");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		lblNewLabel_3_1.setBounds(10, 11, 208, 25);
		panel_7.add(lblNewLabel_3_1);

		textField_giaMIN = new JTextField();
		textField_giaMIN.setBounds(10, 42, 76, 25);
		panel_7.add(textField_giaMIN);
		textField_giaMIN.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("to");
		lblNewLabel_4.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(96, 42, 30, 25);
		panel_7.add(lblNewLabel_4);

		textField_giaMAX = new JTextField();
		textField_giaMAX.setColumns(10);
		textField_giaMAX.setBounds(136, 42, 82, 25);
		panel_7.add(textField_giaMAX);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 57, 228, 72);
		panel_1.add(panel);
		panel.setLayout(null);

		textField_nameXe = new JTextField();
		textField_nameXe.setBounds(10, 36, 208, 31);
		panel.add(textField_nameXe);
		textField_nameXe.setColumns(10);

		JLabel lblNewLabel_3_2 = new JLabel("Theo tên xe");
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		lblNewLabel_3_2.setBounds(10, 11, 208, 25);
		panel.add(lblNewLabel_3_2);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(controller);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setIcon(null);
		btnNewButton.setBounds(10, 575, 105, 33);
		panel_1.add(btnNewButton);
		
		JButton btnXa = new JButton("Refresh");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		btnXa.setIcon(null);
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnXa.setBounds(133, 575, 105, 33);
		panel_1.add(btnXa);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(10, 562, 228, 2);
		panel_1.add(separator_1);
		//
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(253, 0, 889, 691);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 11, 497, 343);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2_1_1 = new JLabel("Danh sách sản phẩm");
		lblNewLabel_2_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(10, 11, 181, 27);
		panel_4.add(lblNewLabel_2_1_1);

		table_Sp = new JTable();
		table_Sp.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "New column", "New column", "New column", "New column", "New column", "New column" }));
		table_Sp.addMouseListener(controller);
		JScrollPane scrollPane_1 = new JScrollPane(table_Sp);
		scrollPane_1.setBounds(10, 49, 481, 288);
		panel_4.add(scrollPane_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 352, 847, 2);
		panel_2.add(separator);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 365, 869, 315);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("Thông tin sản phẩm đã mua");
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 11, 245, 22);
		panel_5.add(lblNewLabel_2_1);

		model_choxn = new DefaultTableModel();
		model_choxn.addColumn("Mã Xe");
		model_choxn.addColumn("Tên Xe");
		model_choxn.addColumn("Loại Xe");
		model_choxn.addColumn("Số lượng");
		model_choxn.addColumn("Đơn giá");
		model_choxn.addColumn("Tổng tiền");
		table_phieuNhap = new JTable();
		table_phieuNhap.setModel(model_choxn);
		table_phieuNhap.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane(table_phieuNhap);
		scrollPane.setBounds(10, 32, 400, 272);
		panel_5.add(scrollPane);

		JButton btn_xacNhan = new JButton("Xác nhận");
		btn_xacNhan.setIcon(new ImageIcon("Assets/ImgeIconJava/Accept-icon.png"));
		btn_xacNhan.setBounds(532, 260, 116, 44);
		panel_5.add(btn_xacNhan);
		btn_xacNhan.addActionListener(controller);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(controller);
		btnXoa.setIcon(new ImageIcon("Assets/ImgeIconJava/Shopping-basket-remove-icon.png"));
		btnXoa.setBounds(682, 260, 116, 44);
		panel_5.add(btnXoa);

		JLabel lblNhnVin = new JLabel("Nhân viên");
		lblNhnVin.setBounds(420, 206, 102, 28);
		panel_5.add(lblNhnVin);
		lblNhnVin.setFont(new Font("Bahnschrift", Font.PLAIN, 15));

		comboBox_NhanVien = new JComboBox();
		comboBox_NhanVien.setBounds(532, 206, 305, 28);
		panel_5.add(comboBox_NhanVien);
		comboBox_NhanVien.setEditable(true);

		comboBox_KH = new JComboBox();
		comboBox_KH.setBounds(532, 161, 279, 28);
		panel_5.add(comboBox_KH);
		comboBox_KH.setEditable(true);

		JLabel lblNhCungCp = new JLabel("Khách hàng");
		lblNhCungCp.setBounds(420, 165, 102, 22);
		panel_5.add(lblNhCungCp);
		lblNhCungCp.setFont(new Font("Bahnschrift", Font.PLAIN, 15));

		JLabel lblMPhiuNhp = new JLabel("Mã phiếu xuất");
		lblMPhiuNhp.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblMPhiuNhp.setBounds(420, 110, 102, 22);
		panel_5.add(lblMPhiuNhp);

		textField_maPX = new JTextField();
		textField_maPX.setColumns(10);
		textField_maPX.setBounds(532, 106, 305, 28);
		panel_5.add(textField_maPX);

		JLabel lblNewLabel_2_1_2 = new JLabel("Thông tin phiếu mua hàng");
		lblNewLabel_2_1_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1_2.setBounds(420, 14, 245, 22);
		panel_5.add(lblNewLabel_2_1_2);

		JLabel lblNgayNhap = new JLabel("Ngày bán");
		lblNgayNhap.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNgayNhap.setBounds(420, 49, 82, 28);
		panel_5.add(lblNgayNhap);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(532, 49, 305, 28);
		panel_5.add(dateChooser);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ChiTietKhachHang();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("Assets/ImgeIconJava/category-icon.png"));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(821, 164, 16, 23);
		panel_5.add(btnNewButton_1);

		JPanel panel_nhapthongtin = new JPanel();
		panel_nhapthongtin.setBackground(Color.WHITE);
		panel_nhapthongtin.setBounds(508, 11, 371, 343);
		panel_2.add(panel_nhapthongtin);
		panel_nhapthongtin.setLayout(null);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Mua hàng");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1_1_1.setBounds(10, 6, 351, 22);
		panel_nhapthongtin.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel = new JLabel("Mã SP");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblNewLabel.setBounds(6, 184, 82, 14);
		panel_nhapthongtin.add(lblNewLabel);

		textField_MaSp = new JTextField();
		textField_MaSp.setBounds(104, 177, 239, 28);
		panel_nhapthongtin.add(textField_MaSp);
		textField_MaSp.setEnabled(false);
		textField_MaSp.setColumns(10);

		JLabel lblTnSnPhm = new JLabel("Tên SP");
		lblTnSnPhm.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblTnSnPhm.setBounds(6, 224, 82, 14);

		panel_nhapthongtin.add(lblTnSnPhm);

		textField_tenSp = new JTextField();
		textField_tenSp.setColumns(10);
		textField_tenSp.setBounds(104, 217, 239, 28);
		textField_tenSp.setEnabled(false);
		panel_nhapthongtin.add(textField_tenSp);

		textField_soLuong = new JTextField();
		textField_soLuong.setColumns(10);
		textField_soLuong.setBounds(104, 257, 239, 28);
		panel_nhapthongtin.add(textField_soLuong);

		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblSLng.setBounds(6, 264, 82, 14);
		panel_nhapthongtin.add(lblSLng);

		JButton btn_xacNhan_1 = new JButton("Mua");
		btn_xacNhan_1.setIcon(new ImageIcon("Assets/ImgeIconJava/cart-add-icon.png"));
		btn_xacNhan_1.addActionListener(controller);
		btn_xacNhan_1.setBounds(147, 297, 104, 40);
		panel_nhapthongtin.add(btn_xacNhan_1);

		lblNewLabel_image = new JLabel("");
		lblNewLabel_image.setBounds(10, 28, 351, 147);
		panel_nhapthongtin.add(lblNewLabel_image);
		lblNewLabel_image.setHorizontalAlignment(SwingConstants.CENTER);

	}
	private ImageIcon createImage(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	// lấy thông tin từ nhà cung cấp lên combobox
	public void nhapThgTinKH() throws Exception {
		khachHangBUS lx = new khachHangBUS();
		ArrayList<KhachHangDTO> arr = lx.getList_kh();
		String[] s = new String[lx.getNumbKH() + 1];
		s[0] = "--Select--";
		int i = 1;
		for (KhachHangDTO dto : arr) {
			s[i] = dto.getMaKH() + "-" + dto.getTenKH();
			i++;
		}
		this.comboBox_KH.setModel(new DefaultComboBoxModel<>(s));
	}

	// lấy thông tin từ nhân viên lên combobox
	public void nhapThongTin_NV() throws Exception {
		NhanVienBUS lx = new NhanVienBUS();
		ArrayList<NhanVienDTO> arr = lx.getList_SP();
		String[] s = new String[lx.getNumbNV() + 1];
		s[0] = "--Select--";
		int i = 1;
		for (NhanVienDTO dto : arr) {
			s[i] = dto.getMaNV() + "-" + dto.getTenNV();
			i++;
		}
		this.comboBox_NhanVien.setModel(new DefaultComboBoxModel<>(s));
	}

	// lấy dữ liệu từ DB vào Jtable
	private void loadDataIntoJTable() {
		model = new DefaultTableModel();
		xemayDAO dal = new xemayDAO();
		try {
			// Set Column Title
			Vector column = new Vector();
			column.add("Mã xe");
			column.add("Tên xe");
			column.add("Đơn giá");
			column.add("Hiện có");
			column.add("Loại xe");
			column.add("");

			model.setColumnIdentifiers(column);
			List<XeMayDTO> list = dal.docDB();
			for (int i = 0; i < list.size(); i++) {
				XeMayDTO ncc = (XeMayDTO) list.get(i);
				Vector row = new Vector();
				row.add(ncc.getMaXe());
				row.add(ncc.getTenXe());
				row.add(ncc.getGiaXe());
				row.add(ncc.getSoLuong());
				String tenLoai = lxBll.getTenLoai(ncc.getLoaiXe());
				row.add(tenLoai);
				row.add(ncc.getMyImage());
				model.addRow(row);

			}
			table_Sp.setModel(model);
			// remove the second column
			table_Sp.removeColumn(table_Sp.getColumnModel().getColumn(5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// lấy ra ảnh sản phẩm
	private ImageIcon getAnhSP(String src) {
		src = src.trim().equals("") ? "default.png" : src;
		// Xử lý ảnh
		BufferedImage img = null;
		File fileImg = new File(src);

		if (!fileImg.exists()) {
			src = "default.png";
			fileImg = new File("Assets/Image/" + src);
		}

		try {
			img = ImageIO.read(fileImg);
			fileAnhSP = new File(src);
		} catch (IOException e) {
			fileAnhSP = new File("imgs/anhthe/avatar.jpg");
		}
		if (img != null) {
			Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			return new ImageIcon(dimg);
		}
		return null;
	}

	// lấy dữ liệu từ text
	public void getDLText() {
		model = (DefaultTableModel) table_Sp.getModel();
		String maXe = this.textField_MaSp.getText();
		String tenXe = this.textField_tenSp.getText();
		Double giaXe = Double.valueOf(this.textField_donGia.getText());
		int soLuong = Integer.valueOf(this.textField_soLuong.getText());
		String cbbloaiXe = this.textField_LoaiXe.getText();
		String[] loaiTmp = cbbloaiXe.split("-");
		String Loaixe = loaiTmp[0].trim();
		String anh = fileAnhSP.getName();
		xMayDTO = new XeMayDTO(maXe, tenXe, giaXe, soLuong, Loaixe, anh);
	}

	// gán ảnh cho jLabel
	public void loadAnh(String anh) {
		lblNewLabel_image.setIcon(getAnhSP(anh));
	}

//
	public void hienThongTinXeMay() {
		model = (DefaultTableModel) table_Sp.getModel();
		int i_row = table_Sp.getSelectedRow();
		if (i_row > -1) {
			String ma = model.getValueAt(i_row, 0) + "";
			this.textField_MaSp.setEditable(false);
			String ten = model.getValueAt(i_row, 1) + "";
			String anh = model.getValueAt(i_row, 5) + "";
			textField_MaSp.setText(ma);
			textField_tenSp.setText(ten);
			loadAnh("Assets/Image/" + anh);
		}
	}

	// khi chọn nhập dữ liệu sẽ đưa xuống hàng chờ xác nhận thêm vào
	public void loadDBtoNhap() {
		model = (DefaultTableModel) table_Sp.getModel();
		int row = table_Sp.getSelectedRow();
		int soLuong = 0;
		soLuong = Integer.parseInt(textField_soLuong.getText());
		Double giaXe = Double.valueOf(model.getValueAt(row, 2) + "");

		if (row > -1) {
			String maSP = table_Sp.getValueAt(row, 0) + "";
			for (int i = 0; i < table_phieuNhap.getRowCount(); i++) {
				if (maSP.equals(table_phieuNhap.getValueAt(i, 0))) {
					int soLuongCu = Integer.parseInt(table_phieuNhap.getValueAt(i, 3) + "");
					soLuong += soLuongCu;
					double thanhTien = soLuong * giaXe;
					table_phieuNhap.setValueAt(soLuong, i, 2);
					table_phieuNhap.setValueAt(giaXe, i, 4);
					table_phieuNhap.setValueAt(thanhTien, i, 5);
				}
			}
			String tenSP = table_Sp.getValueAt(row, 1) + "";
			String loaiXe = table_Sp.getValueAt(row, 4) + "";
			double thanhTien = soLuong * giaXe;
			Vector vec = new Vector();
			vec.add(maSP);
			vec.add(tenSP);
			vec.add(loaiXe);
			vec.add(soLuong);
			vec.add(giaXe);
			vec.add(thanhTien);
			model_choxn.addRow(vec);
		}
	}

	public void xacnhanNhap() throws Exception {
		int row = table_phieuNhap.getRowCount();
		String kh = comboBox_KH.getSelectedItem() + "";
		String[] loaiTmp = kh.split("-");
		String KhachHang = loaiTmp[0].trim();
		//
		String cbbnhanVien = comboBox_NhanVien.getSelectedItem() + "";
		String[] loaiTmp_1 = cbbnhanVien.split("-");
		String nhanVien = loaiTmp_1[0].trim();
		//
		String maPX = textField_maPX.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngaylap = sdf.format(dateChooser.getDate());
		ArrayList<ChiTietHoaDonXuat> dsct = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			String maXe = table_phieuNhap.getValueAt(i, 0) + "";
			String maLoai = table_phieuNhap.getValueAt(i, 2) + "";

			int soLuong = Integer.parseInt(table_phieuNhap.getValueAt(i, 3) + "");
			double donGia = Double.parseDouble(table_phieuNhap.getValueAt(i, 4) + "");
			double tongTien = Double.parseDouble(table_phieuNhap.getValueAt(i, 5) + "");
//			System.out.println(tongTien);
			ChiTietHoaDonXuat ctpn = new ChiTietHoaDonXuat(maPX, donGia, soLuong, maLoai, maXe, tongTien);
			dsct.add(ctpn);
		}

		XuatPhieuXuatView xuatPhieuNhap = new XuatPhieuXuatView(ngaylap, maPX, KhachHang, nhanVien, dsct);
		xuatPhieuNhap.setVisible(true);
		mayBLL.getList_SP();
		loadDataIntoJTable();
	}
	public void refresh() {
		textField_nameXe.setText("");
		textField_giaMIN.setText("");
		textField_giaMAX.setText("");
		buttonGroup_PK.clearSelection();
		loadDataIntoJTable();
	}
	public void searchGiaXe() {
		try {
			xemayDAO dal = new xemayDAO();
			loaixeBUS lxBus = new loaixeBUS();
			String giaMIn = this.textField_giaMIN.getText();
			String giaMAX = this.textField_giaMAX.getText();	
			model.setRowCount(0);
			ArrayList<XeMayDTO> dsxm = dal.searchGiaXe(giaMIn,giaMAX);
			for (XeMayDTO xm : dsxm) {
				Vector vec = new Vector();
				vec.add(xm.getMaXe());
				vec.add(xm.getTenXe());
				vec.add(xm.getGiaXe());
				vec.add(xm.getSoLuong());
				vec.add(lxBus.getTenLoai(xm.getLoaiXe()));
				vec.add(xm.getMyImage());
				model.addRow(vec);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void searchLoaiXe() {
		try {
			xemayDAO dal = new xemayDAO();
			loaixeBUS lxBus = new loaixeBUS();
			String maLoai = "";
			Enumeration<AbstractButton> allRadioButton=buttonGroup_PK.getElements();  
			while(allRadioButton.hasMoreElements())  
			{  
			   JRadioButton temp=(JRadioButton)allRadioButton.nextElement();  
			   if(temp.isSelected())  
			   {  
				   maLoai = lxBus.getMaLoai(temp.getText());  
			   }  
			}
			model.setRowCount(0);
			ArrayList<XeMayDTO> dsxm = dal.searchLoaiXe(maLoai);
			for (XeMayDTO xm : dsxm) {
				Vector vec = new Vector();
				vec.add(xm.getMaXe());
				vec.add(xm.getTenXe());
				vec.add(xm.getGiaXe());
				vec.add(xm.getSoLuong());
				vec.add(lxBus.getTenLoai(xm.getLoaiXe()));
				vec.add(xm.getMyImage());
				model.addRow(vec);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void searchName() {
		try {
			xemayDAO dal = new xemayDAO();
			loaixeBUS lxBus = new loaixeBUS();
			String name = this.textField_nameXe.getText();
			model.setRowCount(0);
			ArrayList<XeMayDTO> dsxm = dal.search(name);
			for (XeMayDTO xm : dsxm) {
				Vector vec = new Vector();
				vec.add(xm.getMaXe());
				vec.add(xm.getTenXe());
				vec.add(xm.getGiaXe());
				vec.add(xm.getSoLuong());
				vec.add(lxBus.getTenLoai(xm.getLoaiXe()));
				vec.add(xm.getMyImage());
				model.addRow(vec);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void search() {
		try {
			xemayDAO dal = new xemayDAO();
			loaixeBUS lxBus = new loaixeBUS();
			String name = this.textField_nameXe.getText();
			String giaMIn = this.textField_giaMIN.getText();
			String giaMAX = this.textField_giaMAX.getText();
			String maLoai = "";
			Enumeration<AbstractButton> allRadioButton=buttonGroup_PK.getElements();  
			while(allRadioButton.hasMoreElements())  
			{  
			   JRadioButton temp=(JRadioButton)allRadioButton.nextElement();  
			   if(temp.isSelected())  
			   {  
				   maLoai = lxBus.getMaLoai(temp.getText());  
			   }  
			}	
			model.setRowCount(0);
			ArrayList<XeMayDTO> dsxm = dal.searchNangCao(name,giaMIn,giaMAX,maLoai);
			for (XeMayDTO xm : dsxm) {
				Vector vec = new Vector();
				vec.add(xm.getMaXe());
				vec.add(xm.getTenXe());
				vec.add(xm.getGiaXe());
				vec.add(xm.getSoLuong());
				vec.add(lxBus.getTenLoai(xm.getLoaiXe()));
				vec.add(xm.getMyImage());
				model.addRow(vec);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ChiTietKhachHang() {
		try {
			KhachHangDAO dao = new KhachHangDAO();
			String kh = comboBox_KH.getSelectedItem() + "";
			String[] loaiTmp = kh.split("-");
			String KhachHang = loaiTmp[0].trim();
			KhachHangDTO list = dao.selectMaKH(KhachHang);
			String maKh = list.getMaKH();
			System.out.println(maKh);
			String tenKh = list.getTenKH();
			System.out.println(tenKh);
			String diaChi = list.getDiaChi();
			System.out.println(diaChi);
			int sdt = list.getSDT();
			System.out.println(sdt);
			String gioiTinh = list.getGioiTinh();
			System.out.println(gioiTinh);
			ChiTietKhachHang chiTietKhachHang = new ChiTietKhachHang(maKh, tenKh, diaChi, sdt, gioiTinh);
			chiTietKhachHang.show();
			nhapThgTinKH();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void xoaKhoiHangCHO() {
		model_choxn = (DefaultTableModel) table_phieuNhap.getModel();
		int i_row = table_phieuNhap.getSelectedRow();
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn xóa dòng đã chọn?");
		if (luaChon == JOptionPane.YES_OPTION) {
			model_choxn.removeRow(i_row);
		}
	}
}
