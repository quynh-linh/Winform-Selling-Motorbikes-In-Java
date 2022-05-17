package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import DAO.nhaCungCapDAO;
import DAO.nhanvienController;
import DAO.nhanvienDAO;
import DAO.xemayDAO;
import DTO.NhaCungCap;
import DTO.NhanVienDTO;
import DTO.XeMayDTO;

import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class nhanvienGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_timkiem;
	private JTextField textField_MaNV;
	private JTextField textField_TenNV;
	private JTextField textField_sdt;
	private JTextField textField_diaChi;
	private JTextField textField_luongNV;
	private DefaultTableModel model;
	private ButtonGroup buttonGroup_sex;
	private JRadioButton rdbtn_men;
	private JRadioButton rdbtn_women;
	private NhanVienDTO nv;
	private JComboBox<String> comboBox_Search;
	private JDateChooser dateChooser_ngaySinh;
	private JDateChooser dateChooser_ngayLam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					nhanvienGUI frame = new nhanvienGUI();
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
	public nhanvienGUI() {
		setForeground(Color.BLACK);
		setTitle("Danh sách nhân viên");
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

		nhanvienController controller = new nhanvienController(this);
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
		JPanel panel_timkiem = new JPanel();
		panel_timkiem.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		panel_timkiem.setBackground(Color.WHITE);
		panel_timkiem.setBounds(253, 0, 867, 62);
		contentPane.add(panel_timkiem);
		panel_timkiem.setLayout(null);

		textField_timkiem = new JTextField();
		textField_timkiem.setBounds(65, 11, 743, 40);
		panel_timkiem.add(textField_timkiem);
		textField_timkiem.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				xoaform();
			}
		});
		lblNewLabel_1_1.setIcon(new ImageIcon("Assets/ImgeIconJava/refresh-icon.png"));
		lblNewLabel_1_1.setBounds(818, 11, 39, 40);
		panel_timkiem.add(lblNewLabel_1_1);

		JButton btn_Timkiem = new JButton("");
		btn_Timkiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btn_Timkiem.setIcon(new ImageIcon("Assets/ImgeIconJava/06-magnify-icon.png"));
		btn_Timkiem.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Timkiem.setForeground(Color.WHITE);
		btn_Timkiem.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_Timkiem.setFocusable(false);
		btn_Timkiem.setBorderPainted(false);
		btn_Timkiem.setBackground(Color.WHITE);
		btn_Timkiem.setBounds(10, 11, 55, 40);
		panel_timkiem.add(btn_Timkiem);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(253, 61, 867, 630);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 11, 847, 208);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Nhập thông tin nhân viên");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 11, 181, 22);
		panel_4.add(lblNewLabel_2);

		JLabel jLabel_MaNV = new JLabel("Mã nhân viên");
		jLabel_MaNV.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_MaNV.setBounds(20, 49, 101, 22);
		panel_4.add(jLabel_MaNV);

		textField_MaNV = new JTextField();
		textField_MaNV.setBounds(147, 44, 218, 30);
		panel_4.add(textField_MaNV);
		textField_MaNV.setColumns(10);

		JLabel jLabel_TenNV = new JLabel("Tên nhân viên");
		jLabel_TenNV.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_TenNV.setBounds(20, 93, 91, 22);
		panel_4.add(jLabel_TenNV);

		textField_TenNV = new JTextField();
		textField_TenNV.setBounds(147, 88, 218, 30);
		panel_4.add(textField_TenNV);

		JLabel jLabel_ngaySinh = new JLabel("Ngày sinh");
		jLabel_ngaySinh.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_ngaySinh.setBounds(20, 134, 80, 22);
		panel_4.add(jLabel_ngaySinh);

		JLabel jLabel_diaChi = new JLabel("Địa chỉ");
		jLabel_diaChi.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_diaChi.setBounds(405, 93, 60, 22);
		panel_4.add(jLabel_diaChi);

		JLabel jLabel_ngayLam = new JLabel("Ngày làm");
		jLabel_ngayLam.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_ngayLam.setBounds(405, 137, 96, 22);
		panel_4.add(jLabel_ngayLam);

		textField_sdt = new JTextField();
		textField_sdt.setColumns(10);
		textField_sdt.setBounds(511, 42, 218, 30);
		panel_4.add(textField_sdt);

		JLabel jLabel_sdt = new JLabel("Số điện thoại");
		jLabel_sdt.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_sdt.setBounds(402, 44, 99, 22);
		panel_4.add(jLabel_sdt);

		textField_diaChi = new JTextField();
		textField_diaChi.setColumns(10);
		textField_diaChi.setBounds(511, 88, 218, 30);
		panel_4.add(textField_diaChi);

		JLabel jLabel_Luong = new JLabel("Lương");
		jLabel_Luong.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_Luong.setBounds(20, 175, 80, 22);
		panel_4.add(jLabel_Luong);

		textField_luongNV = new JTextField();
		textField_luongNV.setBounds(147, 170, 218, 30);
		panel_4.add(textField_luongNV);

		JLabel jLabel_sex = new JLabel("Giới tính");
		jLabel_sex.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_sex.setBounds(405, 175, 96, 22);
		panel_4.add(jLabel_sex);

		buttonGroup_sex = new ButtonGroup();
		rdbtn_men = new JRadioButton("Nam");
		rdbtn_men.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtn_men.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtn_men.setBackground(Color.WHITE);
		rdbtn_men.setBounds(511, 173, 91, 22);
		panel_4.add(rdbtn_men);

		rdbtn_women = new JRadioButton("Nữ");
		rdbtn_women.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtn_women.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtn_women.setBackground(Color.WHITE);
		rdbtn_women.setBounds(624, 173, 91, 23);
		panel_4.add(rdbtn_women);

		buttonGroup_sex.add(rdbtn_men);
		buttonGroup_sex.add(rdbtn_women);
		
		dateChooser_ngaySinh = new JDateChooser();
		dateChooser_ngaySinh.setBounds(147, 129, 218, 30);
		panel_4.add(dateChooser_ngaySinh);
		
		dateChooser_ngayLam =new JDateChooser();
		dateChooser_ngayLam.setBounds(511, 134, 218, 30);
		panel_4.add(dateChooser_ngayLam);
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 230, 847, 2);
		panel_2.add(separator);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 243, 847, 376);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("Thông tin nhân viên");
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 11, 143, 22);
		panel_5.add(lblNewLabel_2_1);

		table = new JTable();
		table.setRowHeight(50);
		table.addMouseListener((MouseListener) controller);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 43, 827, 277);
		panel_5.add(scrollPane);

		JButton btn_Them = new JButton("Thêm");
		btn_Them.setIcon(
				new ImageIcon("Assets/ImgeIconJava/add-icon.png"));
		btn_Them.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Them.addActionListener(controller);
		btn_Them.setBounds(65, 332, 143, 34);
		panel_5.add(btn_Them);

		JButton btn_Sua = new JButton("Sửa");
		btn_Sua.setIcon(new ImageIcon("Assets/ImgeIconJava/62968-wrench-icon.png"));
		btn_Sua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Sua.setBounds(256, 332, 143, 34);
		btn_Sua.addActionListener(controller);
		panel_5.add(btn_Sua);

		JButton btnExportExcel = new JButton("ExportExcel");
		btnExportExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportExcel();
			}
		});
		btnExportExcel.setIcon(new ImageIcon("Assets/ImgeIconJava/Microsoft-Excel-2013-icon.png"));
		btnExportExcel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExportExcel.setBounds(645, 332, 143, 34);
		btnExportExcel.addActionListener(controller);
		panel_5.add(btnExportExcel);
		
		comboBox_Search = new JComboBox<String>();
		comboBox_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDataIntoJTable();
			}
		});
		comboBox_Search.addItem("");
		comboBox_Search.addItem("Lương từ thấp đên cao");
		comboBox_Search.addItem("Lương từ cao đên thấp");
		comboBox_Search.setBounds(596, 10, 241, 22);
		panel_5.add(comboBox_Search);
		
		JButton btn_Xoa_1 = new JButton("Xóa");
		btn_Xoa_1.setIcon(new ImageIcon("Assets/ImgeIconJava/cross-icon.png"));
		btn_Xoa_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Xoa_1.setBounds(455, 332, 143, 34);
		panel_5.add(btn_Xoa_1);
		loadDataIntoJTable();
	}

	public void xoaform() {
		this.textField_MaNV.setText("");
		this.textField_MaNV.setEditable(true);
		this.textField_TenNV.setText("");
		this.textField_diaChi.setText("");
		this.textField_sdt.setText("");
		this.textField_luongNV.setText("");
		this.buttonGroup_sex.clearSelection();
	}
	// tìm kiếm theo tên
		public void search() {
			String data = this.textField_timkiem.getText()+"";
			System.out.println(data);
			nhanvienDAO dal = new nhanvienDAO();
			model.setRowCount(0);
			ArrayList<NhanVienDTO> dsxm = dal.search(data);;
			for (NhanVienDTO xm : dsxm) {
				Vector row = new Vector();
				row.add(xm.getMaNV());
				row.add(xm.getTenNV());
				row.add(xm.getNgaySinh());
				row.add(xm.getDiaChi());
				row.add(xm.getSdt());
				row.add(xm.getNgayLam());
				row.add(xm.getLuongNV());
				row.add(xm.getGioiTinh());
				model.addRow(row);
	        }
		}
	// lấy dữ liệu từ DB vào Jtable
	@SuppressWarnings("unchecked")
	private void loadDataIntoJTable() {
		model = new DefaultTableModel();
		nhanvienDAO dal = new nhanvienDAO();
		List<NhanVienDTO>  list;
		try {
			// Set Column Title
			@SuppressWarnings("rawtypes")
			Vector column = new Vector();
			column.add("Mã nhân viên");
			column.add("Tên nhân viên");
			column.add("Ngày sinh");
			column.add("Địa chỉ");
			column.add("Số điện thoại");
			column.add("Ngày làm");
			column.add("Lương nhân viên");
			column.add("Giới tính");
			model.setColumnIdentifiers(column);
			if (comboBox_Search != null) {
	            String loai = comboBox_Search.getSelectedItem() + "";
	            System.out.println(loai);
	            if (loai.equals("Lương từ thấp đên cao")) {
	            	list = dal.sortSmall();
	            	System.out.println("1");
	            } else {
	            	list = dal.sortHight();
	            }
	        } else {
	        	list = dal.docDB();
	        }
			for (int i = 0; i < list.size(); i++) {
				NhanVienDTO nv = (NhanVienDTO) list.get(i);
				Vector row = new Vector();
				row.add(nv.getMaNV());
				row.add(nv.getTenNV());
				row.add(nv.getNgaySinh());
				row.add(nv.getDiaChi());
				row.add(nv.getSdt());
				row.add(nv.getNgayLam());
				row.add(nv.getLuongNV());
				row.add(nv.getGioiTinh());
				model.addRow(row);
			}
			table.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// lấy dữ liệu từ text
	public void getDLText() {
		model = (DefaultTableModel) table.getModel();
		String maNV = this.textField_MaNV.getText();
		System.out.println(maNV);
		String tenNV = this.textField_TenNV.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngaySinh = sdf.format(dateChooser_ngaySinh.getDate());
		String diaChi = this.textField_diaChi.getText();
		int sdt = Integer.valueOf(this.textField_sdt.getText());
		String ngayLam = sdf.format(( dateChooser_ngayLam).getDate());
		Double luongNV = Double.valueOf(this.textField_luongNV.getText());
		String gioiTinh = "Nam";
		if (this.rdbtn_men.isSelected()) {
			gioiTinh = "Nam";
		} else if (this.rdbtn_women.isSelected()) {
			gioiTinh = "Nữ";
		}
		nv = new NhanVienDTO(maNV, tenNV, ngaySinh, diaChi, sdt, ngayLam, luongNV, gioiTinh);
	}

	// hàm lấy dữ liệu nhà cung cấp từ bảng
	@SuppressWarnings("deprecation")
	public NhanVienDTO getNVTable() throws ParseException {
		model = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		// lấy dữ liệu
		String maNV = model.getValueAt(i_row, 0) + "";
		String tenNV = model.getValueAt(i_row, 1) + "";
		// Start Ngày sinh
		String ngaySinh = model.getValueAt(i_row, 2) + "";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date_ns = formatter.parse(ngaySinh);
		String ns = formatter.format(date_ns);
		// END
		String diaChi = model.getValueAt(i_row, 3) + "";
		int sdt = Integer.valueOf(model.getValueAt(i_row, 4) + "");
		// Start Ngày làm
		String ngayLam = model.getValueAt(i_row, 5) + "";
		Date date_nl = formatter.parse(ngayLam);
		String nl = formatter.format(date_nl);
		// END
		Double luongNV = Double.valueOf(model.getValueAt(i_row, 6) + "");
		String gioiTinh = model.getValueAt(i_row, 7) + "";
		NhanVienDTO nv = new NhanVienDTO(maNV, tenNV, ns, diaChi, sdt, nl, luongNV, gioiTinh);
		return nv;
	}

	public void hienThongTinNCC() throws ParseException {
		NhanVienDTO ts = getNVTable();
		this.textField_MaNV.setText(ts.getMaNV() + "");
		this.textField_MaNV.setEditable(false);
		this.textField_TenNV.setText(ts.getTenNV());
		String ngaySinh = ts.getNgaySinh() + "";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date_ns = formatter.parse(ngaySinh);
		this.dateChooser_ngaySinh.setDate(date_ns);
		this.textField_diaChi.setText(ts.getDiaChi() + "");
		this.textField_sdt.setText(ts.getSdt() + "");
		String ngaylam = ts.getNgayLam() + "";
		Date date_nl = formatter.parse(ngaylam);
		this.dateChooser_ngayLam.setDate(date_nl);
		this.textField_luongNV.setText(ts.getLuongNV() + "");
		if (ts.getGioiTinh().equals("Nam")) {
			rdbtn_men.setSelected(true);
		} else {
			rdbtn_women.setSelected(true);
		}
	}

	public void addNhanVien() {
		try {
			NhanVienBUS list_nv = new NhanVienBUS();
			// lỗi nhập thiếu dữ liệu
			if (textField_MaNV.getText().equals("") || textField_TenNV.getText().equals("")
					|| dateChooser_ngaySinh.getDate().equals("") || textField_diaChi.getText().equals("")
					|| dateChooser_ngayLam.getDate().equals("") || textField_sdt.getText().equals("")
					) {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập đủ dữ liệu");
			} else {
				NhanVienDTO nv = new NhanVienDTO();
				nv.setMaNV(this.textField_MaNV.getText());
				nv.setTenNV(this.textField_TenNV.getText());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String ngaySinh = sdf.format(dateChooser_ngaySinh.getDate());
				nv.setNgaySinh(ngaySinh);
				nv.setDiaChi(this.textField_diaChi.getText());
				nv.setSdt(Integer.valueOf(this.textField_sdt.getText()));
				String ngayLam = sdf.format(dateChooser_ngayLam.getDate());
				nv.setNgayLam(ngayLam);
				nv.setLuongNV(Double.valueOf(this.textField_luongNV.getText()));
				if (this.rdbtn_men.isSelected()) {
					nv.setGioiTinh("Nam");
				} else {
					nv.setGioiTinh("Nữ");
				}
				list_nv.them(nv);
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				list_nv.docDB();
				loadDataIntoJTable();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// chỉnh sửa nhà cung cấp
	public void editNhanVien() {
		getDLText();
		try {
			NhanVienBUS list_nv = new NhanVienBUS();
			int j = table.getSelectedRow();
			if (j >= 0) {
				model.setValueAt(nv.getMaNV() + "", j, 0);
				model.setValueAt(nv.getTenNV() + "", j, 1);
				model.setValueAt(nv.getNgaySinh(), j, 2);
				model.setValueAt(nv.getDiaChi() + "", j, 3);
				model.setValueAt(nv.getSdt() + "", j, 4);
				model.setValueAt(nv.getNgayLam() + "", j, 5);
				model.setValueAt(nv.getLuongNV() + "", j, 6);
				model.setValueAt(nv.getGioiTinh() + "", j, 7);
			}
			JOptionPane.showMessageDialog(this, list_nv.sua(nv));
			xoaform();
			list_nv.docDB();
			loadDataIntoJTable();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(this, "Thông tin không hợp lệ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeRowNV() {
		try {
			getDLText();
			NhanVienBUS list_nv = new NhanVienBUS();
			int selectedRow = table.getSelectedRow();
			model.removeRow(selectedRow);
			JOptionPane.showMessageDialog(this, list_nv.xoa(nv));;
			list_nv.docDB();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		loadDataIntoJTable();
		xoaform();
	}
	
	// Export file excel
	public void exportExcel() {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Danh sach nhan vien");
			XSSFRow row = null;
			Cell cell = null;

			row = sheet.createRow(3);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã nhân viên");

			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Tên nhân viên");

			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Ngày sinh");

			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Địa chỉ");

			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Số điện thoại");

			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Ngày làm");

			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("Lương nhân viên");

			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue("Giới tính");

			int i_row = table.getRowCount();
			for (int i = 0; i < i_row; i++) {
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				sheet.autoSizeColumn(4);
				sheet.autoSizeColumn(5);
				sheet.autoSizeColumn(6);
				sheet.autoSizeColumn(7);
				row = sheet.createRow(4 + i);

				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(table.getValueAt(i, 0) + "");

				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(table.getValueAt(i, 1) + "");

				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(table.getValueAt(i, 2) + "");

				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue(table.getValueAt(i, 3) + "");

				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue(table.getValueAt(i, 4) + "");

				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue(table.getValueAt(i, 5) + "");

				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue(table.getValueAt(i, 6) + "");

				cell = row.createCell(7, CellType.STRING);
				cell.setCellValue(table.getValueAt(i, 7) + "");

			}
			String fileDictName = "Excel/name.xlsx";
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Open the file"); // name for chooser
			FileFilter filter = new FileNameExtensionFilter("Files", ".xlsx"); // filter to show only that
			fileChooser.setAcceptAllFileFilterUsed(false); // to show or not all other files
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setSelectedFile(new File(fileDictName)); // when you want to show the name of file into the
																	// chooser
			fileChooser.setVisible(true);
			int result = fileChooser.showSaveDialog(fileChooser);
			if (result == JFileChooser.APPROVE_OPTION) {
				fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
			} else {
				return;
			}
			File f = new File(fileDictName);
			try {
				FileOutputStream fis = new FileOutputStream(f);
				workbook.write(fis);
				fis.close();
				JOptionPane.showMessageDialog(null, "Export successfully...!");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
