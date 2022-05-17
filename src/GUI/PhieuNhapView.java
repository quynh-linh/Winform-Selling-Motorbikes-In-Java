package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
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

import DTO.ChiTietHoaDonNhap;
import DTO.NhaCungCap;
import DTO.NhanVienDTO;
import DTO.XeMayDTO;
import javax.swing.table.DefaultTableModel;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.xeMayBUS;
import DAO.phieuNhapController;
import DAO.xemayDAO;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;

public class PhieuNhapView extends JFrame {

	private JPanel contentPane;
	private JTable table_phieuNhap;
	private JTable table_Sp;
	private JTextField textField_MaSp;
	private JTextField textField_tenSp;
	private JTextField textField_soLuong;
	private JTextField textField_donGia;
	private DefaultTableModel model, model_choxn;
	private JComboBox<String> comboBox_NhanVien;
	private JComboBox<String> comboBox_NCC;
	private JTextField textField_maPN;
	private xeMayBUS mayBLL = new xeMayBUS();
	private JTextField textField_search;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					PhieuNhapView frame = new PhieuNhapView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PhieuNhapView() throws Exception {
		this.init();
		loadDBtoTableSP();
		nhapThongTin_NCC();
		nhapThongTin_NV();
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setForeground(Color.BLACK);
		setTitle("Nhập hàng");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("Assets/ImgeIconJava/motorcycle-icon.png"));
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1136, 730);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		phieuNhapController controller = new phieuNhapController(this);
		JPanel panel_danhmuc = new JPanel();
		panel_danhmuc.setForeground(Color.WHITE);
		panel_danhmuc.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_danhmuc.setBackground(Color.DARK_GRAY);
		panel_danhmuc.setBounds(0, 0, 253, 691);
		contentPane.add(panel_danhmuc);
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
		//
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(253, 0, 867, 691);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 11, 420, 326);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2_1_1 = new JLabel("Sản phẩm trong kho");
		lblNewLabel_2_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(10, 68, 181, 22);
		panel_4.add(lblNewLabel_2_1_1);

		table_Sp = new JTable();
		table_Sp.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "New column", "New column", "New column" }));
		table_Sp.addMouseListener(controller);
		JScrollPane scrollPane_1 = new JScrollPane(table_Sp);
		scrollPane_1.setBounds(10, 88, 400, 227);
		panel_4.add(scrollPane_1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 400, 46);
		panel_4.add(panel);
		panel.setLayout(null);

		textField_search = new JTextField();
		textField_search.setBounds(68, 0, 332, 46);
		panel.add(textField_search);
		textField_search.setColumns(10);

		JButton btn_search = new JButton("");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btn_search.setIcon(new ImageIcon("Assets/ImgeIconJava/06-magnify-icon.png"));
		btn_search.setHorizontalAlignment(SwingConstants.LEFT);
		btn_search.setForeground(Color.WHITE);
		btn_search.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_search.setFocusable(false);
		btn_search.setBorderPainted(false);
		btn_search.setBackground(Color.WHITE);
		btn_search.setBounds(0, 0, 68, 43);
		panel.add(btn_search);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 352, 847, 2);
		panel_2.add(separator);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 365, 847, 315);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("Thông tin hàng nhập chờ xác nhận");
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 11, 245, 22);
		panel_5.add(lblNewLabel_2_1);

		model_choxn = new DefaultTableModel();
		model_choxn.addColumn("Mã SP");
		model_choxn.addColumn("Tên SP");
		model_choxn.addColumn("Số lượng");
		model_choxn.addColumn("Đơn giá");
		model_choxn.addColumn("Thành tiền");
		table_phieuNhap = new JTable();
		table_phieuNhap.setModel(model_choxn);
		table_phieuNhap.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane(table_phieuNhap);
		scrollPane.setBounds(10, 32, 400, 272);
		panel_5.add(scrollPane);

		JButton btn_xacNhan = new JButton("Xác nhận");
		btn_xacNhan.setIcon(new ImageIcon("Assets/ImgeIconJava/Accept-icon.png"));
		btn_xacNhan.setBounds(515, 263, 127, 41);
		panel_5.add(btn_xacNhan);
		btn_xacNhan.addActionListener(controller);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(controller);
		btnXoa.setIcon(new ImageIcon("Assets/ImgeIconJava/Shopping-basket-remove-icon.png"));
		btnXoa.setBounds(663, 263, 127, 41);
		panel_5.add(btnXoa);

		JLabel lblNhnVin = new JLabel("Nhân viên");
		lblNhnVin.setBounds(440, 214, 82, 14);
		panel_5.add(lblNhnVin);
		lblNhnVin.setFont(new Font("Bahnschrift", Font.PLAIN, 13));

		comboBox_NhanVien = new JComboBox();
		comboBox_NhanVien.setBounds(532, 206, 305, 28);
		panel_5.add(comboBox_NhanVien);
		comboBox_NhanVien.setEditable(true);

		comboBox_NCC = new JComboBox();
		comboBox_NCC.setBounds(532, 161, 305, 28);
		panel_5.add(comboBox_NCC);
		comboBox_NCC.setEditable(true);

		JLabel lblNhCungCp = new JLabel("Nhà cung cấp");
		lblNhCungCp.setBounds(440, 169, 82, 14);
		panel_5.add(lblNhCungCp);
		lblNhCungCp.setFont(new Font("Bahnschrift", Font.PLAIN, 13));

		JLabel lblMPhiuNhp = new JLabel("Mã phiếu nhập");
		lblMPhiuNhp.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblMPhiuNhp.setBounds(440, 114, 82, 14);
		panel_5.add(lblMPhiuNhp);

		textField_maPN = new JTextField();
		textField_maPN.setColumns(10);
		textField_maPN.setBounds(532, 106, 305, 28);
		panel_5.add(textField_maPN);

		JLabel lblNewLabel_2_1_2 = new JLabel("Thông tin hàng nhập chờ xác nhận");
		lblNewLabel_2_1_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1_2.setBounds(420, 14, 245, 22);
		panel_5.add(lblNewLabel_2_1_2);

		JLabel lblNgayNhap = new JLabel("Ngày nhập");
		lblNgayNhap.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblNgayNhap.setBounds(440, 63, 82, 14);
		panel_5.add(lblNgayNhap);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(532, 49, 305, 28);
		panel_5.add(dateChooser);

		JPanel panel_nhapthongtin = new JPanel();
		panel_nhapthongtin.setBackground(Color.WHITE);
		panel_nhapthongtin.setBounds(440, 11, 417, 326);
		panel_2.add(panel_nhapthongtin);
		panel_nhapthongtin.setLayout(null);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Nhập hàng");
		lblNewLabel_2_1_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1_1_1.setBounds(10, 11, 82, 22);
		panel_nhapthongtin.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel = new JLabel("Mã sản phẩm");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 52, 82, 14);
		panel_nhapthongtin.add(lblNewLabel);

		textField_MaSp = new JTextField();
		textField_MaSp.setBounds(102, 44, 305, 28);
		panel_nhapthongtin.add(textField_MaSp);
		textField_MaSp.setEnabled(false);
		textField_MaSp.setColumns(10);

		JLabel lblTnSnPhm = new JLabel("Tên sản phẩm");
		lblTnSnPhm.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblTnSnPhm.setBounds(10, 113, 82, 14);

		panel_nhapthongtin.add(lblTnSnPhm);

		textField_tenSp = new JTextField();
		textField_tenSp.setColumns(10);
		textField_tenSp.setBounds(102, 105, 305, 28);
		textField_tenSp.setEnabled(false);
		panel_nhapthongtin.add(textField_tenSp);

		textField_soLuong = new JTextField();
		textField_soLuong.setColumns(10);
		textField_soLuong.setBounds(102, 157, 305, 28);
		panel_nhapthongtin.add(textField_soLuong);

		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblSLng.setBounds(10, 165, 82, 14);
		panel_nhapthongtin.add(lblSLng);

		JLabel lblnGi = new JLabel("Đơn giá");
		lblnGi.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblnGi.setBounds(10, 225, 82, 14);
		panel_nhapthongtin.add(lblnGi);

		textField_donGia = new JTextField();
		textField_donGia.setColumns(10);
		textField_donGia.setEnabled(false);
		textField_donGia.setBounds(102, 217, 305, 28);
		panel_nhapthongtin.add(textField_donGia);

		JButton btn_xacNhan_1 = new JButton("Nhập");
		btn_xacNhan_1.setIcon(new ImageIcon("Assets/ImgeIconJava/cart-add-icon.png"));
		btn_xacNhan_1.addActionListener(controller);
		btn_xacNhan_1.setBounds(146, 273, 158, 42);
		panel_nhapthongtin.add(btn_xacNhan_1);
	}

	// lấy thông tin từ nhà cung cấp lên combobox
	public void nhapThongTin_NCC() throws Exception {
		NhaCungCapBUS lx = new NhaCungCapBUS();
		ArrayList<NhaCungCap> arr = lx.getList_NCC();
		String[] s = new String[lx.getNumbNCC() + 1];
		s[0] = "--Select--";
		int i = 1;
		for (NhaCungCap dto : arr) {
			s[i] = dto.getMaNhaCC() + "-" + dto.getTenNhaCC();
			i++;
		}
		this.comboBox_NCC.setModel(new DefaultComboBoxModel<>(s));
	}

	// lấy thông tin từ nhà cung cấp lên combobox
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

	// lấy dữ liệu từ Db lên bảng sản phẩm trong kho
	public void loadDBtoTableSP() {
		model = new DefaultTableModel();
		xemayDAO dal = new xemayDAO();
		try {
			// Set Column Title
			Vector column = new Vector();
			column.add("Mã xe");
			column.add("Tên xe");
			column.add("Tồn kho");
			column.add("Giá xe");
			model.setColumnIdentifiers(column);
			List<XeMayDTO> list = dal.docDB();
			for (int i = 0; i < list.size(); i++) {
				XeMayDTO ncc = (XeMayDTO) list.get(i);
				Vector row = new Vector();
				row.add(ncc.getMaXe());
				row.add(ncc.getTenXe());
				row.add(ncc.getSoLuong());
				row.add(ncc.getGiaXe());
				model.addRow(row);
			}
			table_Sp.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// hàm xóa thông tin hàng chờ nhập ra khỏi table
	public void thuchienXoa() {
		model_choxn = (DefaultTableModel) table_phieuNhap.getModel();
		int i_row = table_phieuNhap.getSelectedRow();
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn xóa dòng đã chọn?");
		if (luaChon == JOptionPane.YES_OPTION) {
			model_choxn.removeRow(i_row);
		}
	}
	// hiển thị thông tin sản phẩm lên phần nhập khi người dùng click vào bảng Sản
	// phẩm tồn
	public void hienThongTinXeMay() {
		model = (DefaultTableModel) table_Sp.getModel();
		int i_row = table_Sp.getSelectedRow();
		if (i_row > -1) {
			String ma = model.getValueAt(i_row, 0) + "";
			this.textField_MaSp.setEditable(false);
			String ten = model.getValueAt(i_row, 1) + "";
			this.textField_tenSp.setEditable(false);
			String giaXe = model.getValueAt(i_row, 3) + "";
			this.textField_donGia.setEditable(false);
			textField_MaSp.setText(ma);
			textField_tenSp.setText(ten);
			textField_donGia.setText(giaXe);
		}
	}

	// khi chọn nhập dữ liệu sẽ đưa xuống hàng chờ xác nhận thêm vào
	public void loadDBtoNhap() {
		int soLuong = 0;
		double donGia = 0;
		soLuong = Integer.parseInt(textField_soLuong.getText());
		System.out.println(donGia);
		int row = table_Sp.getSelectedRow();
		if (row > -1) {
			String maSP = table_Sp.getValueAt(row, 0) + "";
			donGia = Double.valueOf(table_Sp.getValueAt(row, 3) + "");
			for (int i = 0; i < table_phieuNhap.getRowCount(); i++) {
				if (maSP.equals(table_phieuNhap.getValueAt(i, 0))) {
					int soLuongCu = Integer.parseInt(table_phieuNhap.getValueAt(i, 2) + "");
					soLuong += soLuongCu;
					Double thanhTien = soLuong * donGia;
					table_phieuNhap.setValueAt(soLuong, i, 2);
					table_phieuNhap.setValueAt(donGia, i, 3);
					table_phieuNhap.setValueAt(thanhTien, i, 4);
					return;
				}
			}
			String tenSP = table_Sp.getValueAt(row, 1) + "";
			Double thanhTien = soLuong * donGia;
			Vector vec = new Vector();
			vec.add(maSP);
			vec.add(tenSP);
			vec.add(soLuong);
			vec.add(donGia);
			vec.add(thanhTien);
			model_choxn.addRow(vec);
		}
	}

	public void xacnhanNhap() throws Exception {
		int row = table_phieuNhap.getRowCount();
		String ncc = comboBox_NCC.getSelectedItem() + "";
		String[] loaiTmp = ncc.split("-");
		String nhaCungCap = loaiTmp[0].trim();
		//
		String cbbnhanVien = comboBox_NhanVien.getSelectedItem() + "";
		String[] loaiTmp_1 = cbbnhanVien.split("-");
		String nhanVien = loaiTmp_1[0].trim();
		//
		String maPN = textField_maPN.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngaylap = sdf.format(dateChooser.getDate());
		ArrayList<ChiTietHoaDonNhap> dsct = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			String maXe = table_phieuNhap.getValueAt(i, 0) + "";
			int soLuong = Integer.parseInt(table_phieuNhap.getValueAt(i, 2) + "");
			float donGia = Float.parseFloat(table_phieuNhap.getValueAt(i, 3) + "");
			float thanhTien = Float.parseFloat(table_phieuNhap.getValueAt(i, 4) + "");
			ChiTietHoaDonNhap ctpn = new ChiTietHoaDonNhap(donGia, thanhTien, soLuong, maXe, maPN);
			dsct.add(ctpn);
		}
		XuatPhieuNhapView xuatPhieuNhap = new XuatPhieuNhapView(ngaylap, maPN, nhaCungCap, nhanVien, dsct);
		xuatPhieuNhap.setVisible(true);
		mayBLL.getList_SP();
		loadDBtoTableSP();
	}

	public void search() {
		String data = this.textField_search.getText() + "";
		System.out.println(data);
		xemayDAO dal = new xemayDAO();
		model.setRowCount(0);
		ArrayList<XeMayDTO> dsxm = dal.search(data);
		;
		for (XeMayDTO xm : dsxm) {
			Vector vec = new Vector();
			vec.add(xm.getMaXe());
			vec.add(xm.getTenXe());
			vec.add(xm.getSoLuong());
			model.addRow(vec);
		}
	}
}
