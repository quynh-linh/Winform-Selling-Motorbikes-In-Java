package GUI;

import java.awt.Color;
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
import BUS.khachHangBUS;
import DAO.KhachHangController;
import DAO.KhachHangDAO;
import DAO.nhaCungCapDAO;
import DAO.nhanvienController;
import DAO.nhanvienDAO;
import BUS.NhanVienBUS;
import DTO.KhachHangDTO;
import DTO.NhaCungCap;
import DTO.NhanVienDTO;

import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KhachHangGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_timkiem;
	private JTextField textField_MaKH;
	private JTextField textField_TenKH;
	private JTextField textField_NgaySinh;
	private JTextField textField_sdt;
	private JTextField textField_diaChi;
	private JTextField textField_ngayLam;
	private JTextField textField_luongNV;
	private DefaultTableModel model;
	private ButtonGroup buttonGroup_sex;
	private JRadioButton rdbtn_men;
	private JRadioButton rdbtn_women;
	private KhachHangDTO kh;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					KhachHangGUI frame = new KhachHangGUI();
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
	public KhachHangGUI() {
		// TODO Auto-generated constructor stub
	} {
		setForeground(Color.BLACK);
		setTitle("Danh sách khách hàng");
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

	    KhachHangController controller = new KhachHangController(this);
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
		panel_4.setBounds(10, 6, 847, 199);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Nhập thông tin khách hàng");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 11, 181, 22);
		panel_4.add(lblNewLabel_2);

		JLabel jLabel_MaNV = new JLabel("Mã khách hàng");
		jLabel_MaNV.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_MaNV.setBounds(20, 45, 127, 22);
		panel_4.add(jLabel_MaNV);

		textField_MaKH = new JTextField();
		textField_MaKH.setBounds(159, 42, 218, 30);
		panel_4.add(textField_MaKH);
		textField_MaKH.setColumns(10);

		JLabel jLabel_TenNV = new JLabel("Tên khách hàng");
		jLabel_TenNV.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_TenNV.setBounds(20, 93, 127, 22);
		panel_4.add(jLabel_TenNV);

		textField_TenKH = new JTextField();
		textField_TenKH.setBounds(159, 90, 218, 30);
		panel_4.add(textField_TenKH);


		JLabel jLabel_diaChi = new JLabel("Địa chỉ");
		jLabel_diaChi.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_diaChi.setBounds(474, 45, 60, 22);
		panel_4.add(jLabel_diaChi);


		textField_sdt = new JTextField();
		textField_sdt.setColumns(10);
		textField_sdt.setBounds(159, 132, 218, 30);
		panel_4.add(textField_sdt);

		JLabel jLabel_sdt = new JLabel("Số điện thoại");
		jLabel_sdt.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_sdt.setBounds(22, 137, 99, 22);
		panel_4.add(jLabel_sdt);

		textField_diaChi = new JTextField();
		textField_diaChi.setColumns(10);
		textField_diaChi.setBounds(566, 42, 204, 30);
		panel_4.add(textField_diaChi);



		JLabel jLabel_sex = new JLabel("Giới tính");
		jLabel_sex.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_sex.setBounds(474, 93, 96, 22);
		panel_4.add(jLabel_sex);

		buttonGroup_sex = new ButtonGroup();
		rdbtn_men = new JRadioButton("Nam");
		rdbtn_men.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtn_men.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtn_men.setBackground(Color.WHITE);
		rdbtn_men.setBounds(554, 94, 91, 22);
		panel_4.add(rdbtn_men);

		rdbtn_women = new JRadioButton("Nữ");
		rdbtn_women.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtn_women.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtn_women.setBackground(Color.WHITE);
		rdbtn_women.setBounds(664, 93, 91, 23);
		panel_4.add(rdbtn_women);

		buttonGroup_sex.add(rdbtn_men);
		buttonGroup_sex.add(rdbtn_women);
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 216, 847, 2);
		panel_2.add(separator);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 229, 847, 390);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("Thông tin khách hàng");
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 11, 143, 22);
		panel_5.add(lblNewLabel_2_1);

		table = new JTable();
		table.setRowHeight(50);
		table.addMouseListener((MouseListener) controller);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 40, 827, 264);
		panel_5.add(scrollPane);

		JButton btn_Them = new JButton("Thêm");
		btn_Them.setIcon(
				new ImageIcon("Assets/ImgeIconJava/add-icon.png"));
		btn_Them.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Them.addActionListener(controller);
		btn_Them.setBounds(111, 331, 143, 34);
		panel_5.add(btn_Them);

		JButton btn_Sua = new JButton("Sửa");
		btn_Sua.setIcon(new ImageIcon("Assets/ImgeIconJava/62968-wrench-icon.png"));
		btn_Sua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Sua.setBounds(288, 331, 143, 34);
		btn_Sua.addActionListener(controller);
		panel_5.add(btn_Sua);

		JButton btn_Xoa = new JButton("Xóa");
		btn_Xoa.setIcon(new ImageIcon("Assets/ImgeIconJava/cross-icon.png"));
		btn_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Xoa.setBounds(457, 331, 143, 34);
		btn_Xoa.addActionListener(controller);
		panel_5.add(btn_Xoa);

		JButton btnExportExcel = new JButton("ExportExcel");
		btnExportExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportExcel();	
			}
		});
		btnExportExcel.setIcon(new ImageIcon("Assets/ImgeIconJava/Microsoft-Excel-2013-icon.png"));
		btnExportExcel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExportExcel.setBounds(633, 331, 143, 34);
		panel_5.add(btnExportExcel);
		loadDataIntoJTable();
		
	}

	public void xoaform() {
		this.textField_MaKH.setText("");
		this.textField_MaKH.setEditable(true);
		this.textField_TenKH.setText("");
		this.textField_diaChi.setText("");
		this.textField_sdt.setText("");
		this.buttonGroup_sex.clearSelection();
	}

	// lấy dữ liệu từ DB vào Jtable
	@SuppressWarnings("unchecked")
	private void loadDataIntoJTable() {
		model = new DefaultTableModel();
		KhachHangDAO dal = new KhachHangDAO();
		try {
			// Set Column Title
			@SuppressWarnings("rawtypes")
			Vector column = new Vector();
			column.add("Mã Khách Hàng");
			column.add("Tên Khách Hàng");
			
			column.add("Địa chỉ");
			column.add("Số điện thoại");
			
			column.add("Giới tính");
			model.setColumnIdentifiers(column);
			List<KhachHangDTO> list = dal.docDB();
			for (int i = 0; i < list.size(); i++) {
				KhachHangDTO KH = (KhachHangDTO) list.get(i);
				Vector row = new Vector();
				row.add(KH.getMaKH());
				row.add(KH.getTenKH());
				
				row.add(KH.getDiaChi());
				row.add(KH.getSDT());
				row.add(KH.getGioiTinh());
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
		String maKH = this.textField_MaKH.getText();
		System.out.println(maKH);
		String tenKH = this.textField_TenKH.getText();
		
		String diaChi = this.textField_diaChi.getText();
		int sdt = Integer.valueOf(this.textField_sdt.getText());

		String gioiTinh = "Nam";
		if (this.rdbtn_men.isSelected()) {
			gioiTinh = "Nam";
		} else if (this.rdbtn_women.isSelected()) {
			gioiTinh = "Nữ";
		}
		kh = new KhachHangDTO(maKH, tenKH, diaChi, sdt, gioiTinh);
	}

	// hàm lấy dữ liệu nhà cung cấp từ bảng
	@SuppressWarnings("deprecation")
	public KhachHangDTO getNVTable() throws ParseException {
		model = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		// lấy dữ liệu
		String maKH = model.getValueAt(i_row, 0) + "";
		String tenKH = model.getValueAt(i_row, 1) + "";
	
		// END
		String diaChi = model.getValueAt(i_row, 2) + "";
		int sdt = Integer.valueOf(model.getValueAt(i_row, 3) + "");
		
		// END
		
		String gioiTinh = model.getValueAt(i_row, 4) + "";
		kh = new KhachHangDTO(maKH, tenKH, diaChi, sdt, gioiTinh);
		return kh;
	}

	public void hienThongTinKH() throws ParseException {
		KhachHangDTO ts = getNVTable();
		this.textField_MaKH.setText(ts.getMaKH() + "");
		this.textField_MaKH.setEditable(false);
		this.textField_TenKH.setText(ts.getTenKH());
		
		this.textField_diaChi.setText(ts.getDiaChi() + "");
		this.textField_sdt.setText(ts.getSDT() + "");
		
		if (ts.getGioiTinh().equals("Nam")) {
			rdbtn_men.setSelected(true);
		} else {
			rdbtn_women.setSelected(true);
		}
	}

	public void themKhachHang() {
		try {
			khachHangBUS list_kh = new khachHangBUS();
			// lỗi nhập thiếu dữ liệu
			if (textField_MaKH.getText().equals("") || textField_TenKH.getText().equals("")
					|| textField_diaChi.getText().equals("")
					|| textField_sdt.getText().equals(""))
					 {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập đủ dữ liệu");
			} else {
				KhachHangDTO kh = new KhachHangDTO();
				kh.setMaKH(this.textField_MaKH.getText());
				kh.setTenKH(this.textField_TenKH.getText());
				
				kh.setDiaChi(this.textField_diaChi.getText());
				kh.setSDT(Integer.valueOf(this.textField_sdt.getText()));
				
				if (this.rdbtn_men.isSelected()) {
					kh.setGioiTinh("Nam");
				} else {
					kh.setGioiTinh("Nữ");
				}
				list_kh.them(kh);
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				list_kh.docDB();
				loadDataIntoJTable();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// chỉnh sửa nhà cung cấp
	public void editKhachHang() {
		getDLText();
		try {
			khachHangBUS list_kh = new khachHangBUS();
			int j = table.getSelectedRow();
			if (j >= 0) {
				model.setValueAt(kh.getTenKH() + "", j, 1);
				model.setValueAt(kh.getDiaChi() + "", j, 2);
				model.setValueAt(kh.getSDT() + "", j, 3);
				model.setValueAt(kh.getGioiTinh() + "", j, 4);
			}
			JOptionPane.showMessageDialog(this, list_kh.sua(kh));
			xoaform();
			list_kh.docDB();
			loadDataIntoJTable();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(this, "Thông tin không hợp lệ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeRowKH() {
		try {
			khachHangBUS list_kh = new khachHangBUS();
			int selectedRow = table.getSelectedRow();
			model.removeRow(selectedRow);
			JOptionPane.showMessageDialog(this, list_kh.xoa(kh));
			list_kh.docDB();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		loadDataIntoJTable();
		xoaform();
	}
	
	// export file excel
	public void exportExcel() {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Danh sach khach hang");
			XSSFRow row = null;
			Cell cell = null;
			
			row =sheet.createRow(3);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã khách hàng");
			
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Tên khách hàng");
			
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Địa chỉ");
			
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Số điện thoại");
			
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Giới tính");
			
			int i_row = table.getRowCount();
			for(int i = 0; i<i_row;i++) {
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				sheet.autoSizeColumn(4);
				row = sheet.createRow(4+i);
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
					
			}
			String fileDictName = "Excel/name.xlsx";
			JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Open the file"); //name for chooser
	        FileFilter filter = new FileNameExtensionFilter("Files", ".xlsx"); //filter to show only that
	        fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
	        fileChooser.addChoosableFileFilter(filter);
	        fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
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
			} 
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
