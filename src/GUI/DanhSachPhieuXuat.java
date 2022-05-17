package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractButton;
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
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.PhieuXuatBUS;
import BUS.xeMayBUS;
import DAO.ChiTietPhieuNhapDAO;
import DAO.ChiTietPhieuXuatDAO;
import DAO.PhieuNhapDAO;
import DAO.PhieuXuatDAO;
import DAO.nhaCungCapDAO;
import DAO.phieuNhapController;
import DAO.phieuXuatController;
import DAO.xemayDAO;
import DTO.ChiTietHoaDonNhap;
import DTO.ChiTietHoaDonXuat;
import DTO.HoaDonNhap;
import DTO.HoaDonXuat;
import DTO.XeMayDTO;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;

public class DanhSachPhieuXuat extends JFrame {

	private JPanel contentPane;
	private JTable table_Phieuxuat;
	private JTable table_chiTietPX;
	private JTextField textField;
	private DefaultTableModel model;
	private DefaultTableModel model_chitiet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					DanhSachPhieuXuat frame = new DanhSachPhieuXuat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DanhSachPhieuXuat() throws Exception {
		this.init();
		this.loadDataIntoJTable();
	}

	public void init() {
		setForeground(Color.BLACK);
		setTitle("Danh sách phiếu xuất");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets/ImgeIconJava/motorcycle-icon.png"));
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1136, 730);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
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
		panel_4.setBounds(10, 75, 847, 262);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2_1_1 = new JLabel("Danh sách phiếu Xuất");
		lblNewLabel_2_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(10, 11, 181, 22);
		panel_4.add(lblNewLabel_2_1_1);

		table_Phieuxuat = new JTable();
		table_Phieuxuat.setRowHeight(50);
		table_Phieuxuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					loadDataIntoJTableChiiet();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		table_Phieuxuat.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "New column", "New column", "New column" }));
//		table_Sp.addMouseListener(controller);
		JScrollPane scrollPane_1 = new JScrollPane(table_Phieuxuat);
		scrollPane_1.setBounds(10, 40, 827, 211);
		panel_4.add(scrollPane_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 340, 847, 2);
		panel_2.add(separator);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 348, 847, 332);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("Chi tiết phiếu xuất");
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 11, 245, 22);
		panel_5.add(lblNewLabel_2_1);

		table_chiTietPX = new JTable();
		table_chiTietPX.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane(table_chiTietPX);
		scrollPane.setBounds(10, 44, 827, 218);
		panel_5.add(scrollPane);

		JButton btnInPhieuXuat = new JButton("In phiếu xuất");
		btnInPhieuXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienThiThongTinToTable();

			}
		});
		btnInPhieuXuat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInPhieuXuat.setIcon(new ImageIcon("Assets/ImgeIconJava/print-icon (1).png"));
		btnInPhieuXuat.setBounds(64, 274, 191, 48);
		panel_5.add(btnInPhieuXuat);

		JButton btnXoaPhieuXuat = new JButton("Xóa phiếu xuất");
		btnXoaPhieuXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaPhieuNhap();
			}
		});
		btnXoaPhieuXuat.setIcon(new ImageIcon("Assets/ImgeIconJava/cross-icon.png"));
		btnXoaPhieuXuat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoaPhieuXuat.setBounds(328, 274, 191, 48);
		panel_5.add(btnXoaPhieuXuat);
		
		JButton btnExportexcel = new JButton("ExportExcel");
		btnExportexcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportExcel();	
			}
		});
		btnExportexcel.setIcon(new ImageIcon("Assets/ImgeIconJava/Microsoft-Excel-2013-icon.png"));
		btnExportexcel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExportexcel.setBounds(597, 274, 191, 48);
		panel_5.add(btnExportexcel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 867, 60);
		panel_2.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tìm kiếm");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 80, 26);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(137, 11, 720, 38);
		panel.add(textField);
		textField.setColumns(10);

		JButton btn_home_2 = new JButton("");
		btn_home_2.setIcon(new ImageIcon("Assets/ImgeIconJava/06-magnify-icon.png"));
		btn_home_2.setHorizontalAlignment(SwingConstants.LEFT);
		btn_home_2.setForeground(Color.WHITE);
		btn_home_2.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		btn_home_2.setFocusable(false);
		btn_home_2.setBorderPainted(false);
		btn_home_2.setBackground(Color.WHITE);
		btn_home_2.setBounds(78, 12, 61, 37);
		panel.add(btn_home_2);
	}

	// lấy dữ liệu từ DB vào Jtable
	private void loadDataIntoJTable() throws Exception {
		model = new DefaultTableModel();
		PhieuXuatDAO px = new PhieuXuatDAO();
		NhanVienBUS nvBll = new NhanVienBUS();
		khachHangBUS khBLl = new khachHangBUS();
		try {
			// Set Column Title
			Vector column = new Vector();
			column.add("Mã phiếu xuất");
			column.add("Ngày xuất");
			column.add("Tên nhân viên");
			column.add("Tên khách hàng");
			column.add("Tổng tiền");
			model.setColumnIdentifiers(column);
			List<HoaDonXuat> list = px.docDB();
			for (int i = 0; i < list.size(); i++) {
				HoaDonXuat hdx = (HoaDonXuat) list.get(i);
				Vector row = new Vector();
				row.add(hdx.getMaPX());
				row.add(hdx.getNgayXuat());
				String tenNV = nvBll.getTenNV(hdx.getMaNV());
				row.add(tenNV);
				String tenKH = khBLl.getTenNV(hdx.getMaKH());
				row.add(tenKH);
				row.add(hdx.getTongTien());
				model.addRow(row);
			}
			table_Phieuxuat.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// lấy dữ liệu từ DB vào Jtable
	private void loadDataIntoJTableChiiet() throws Exception {
		model_chitiet = new DefaultTableModel();
		ChiTietPhieuXuatDAO ctpn = new ChiTietPhieuXuatDAO();
		xeMayBUS xmBll = new xeMayBUS();
		String ma = null;
		model = (DefaultTableModel) table_Phieuxuat.getModel();
		int i_row = table_Phieuxuat.getSelectedRow();
		ma = model.getValueAt(i_row, 0) + "";
		try {
			// Set Column Title
			Vector column = new Vector();
			column.add("Mã phiếu xuất");
			column.add("Mã xe");
			column.add("Mã loại");
			column.add("Số lượng");
			column.add("Đơn giá");
			column.add("Tổng tiền");
			model_chitiet.setColumnIdentifiers(column);
			System.out.println(ma);
			ArrayList<ChiTietHoaDonXuat> list = ctpn.docDB1(ma);
			for (int i = 0; i < list.size(); i++) {
				ChiTietHoaDonXuat hdx = (ChiTietHoaDonXuat) list.get(i);
				Vector row = new Vector();
				row.add(hdx.getMaPX());
				row.add(hdx.getMaXe());
				row.add(hdx.getMaLoai());
				row.add(hdx.getSoLuong());
				row.add(hdx.getDonGia());
				row.add(hdx.gettongTien());
				model_chitiet.addRow(row);
			}
			table_chiTietPX.setModel(model_chitiet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hienThiThongTinToTable() {
		model = new DefaultTableModel();
		int i_row_1 = table_Phieuxuat.getSelectedRow();
		String nhanVien = table_Phieuxuat.getValueAt(i_row_1, 2) + "";
		String ngayXuat = table_Phieuxuat.getValueAt(i_row_1, 1) + "";
		String khachHang = table_Phieuxuat.getValueAt(i_row_1, 3) + "";
		double tongTien = Double.valueOf(table_Phieuxuat.getValueAt(i_row_1, 4) + "");

		// Thông tin chi tiết phiếu nhập;
		model_chitiet = new DefaultTableModel();
		int i_row = table_chiTietPX.getRowCount();
		ArrayList<ChiTietHoaDonXuat> dsct = new ArrayList<>();
		for (int i = 0; i < i_row; i++) {
			String maPX = table_chiTietPX.getValueAt(i, 0) + "";
			String maXe = table_chiTietPX.getValueAt(i, 1) + "";
			String maLoai = table_chiTietPX.getValueAt(i, 2) + "";
			int soLuong = Integer.valueOf(table_chiTietPX.getValueAt(i, 3) + "");
			double donGia = Double.valueOf(table_chiTietPX.getValueAt(i, 4) + "");
			double TongTien = Double.valueOf(table_chiTietPX.getValueAt(i, 5) + "");
			ChiTietHoaDonXuat ctpx = new ChiTietHoaDonXuat(maPX, donGia, soLuong, maLoai, maXe, tongTien);
			dsct.add(ctpx);
		}
		try {
			InPhieuXuat in = new InPhieuXuat(dsct, nhanVien, ngayXuat, khachHang, tongTien);
			in.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void xoaPhieuNhap() {
		try {
			PhieuXuatBUS pxBll = new PhieuXuatBUS();
			NhanVienBUS nvBll = new NhanVienBUS();
			khachHangBUS khBll = new khachHangBUS();
			model = (DefaultTableModel) table_Phieuxuat.getModel();
			int i_row = table_Phieuxuat.getSelectedRow();
			int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn xóa dòng đã chọn?");
			if (luaChon == JOptionPane.YES_OPTION) {
				String maPhieuXuat = table_Phieuxuat.getValueAt(i_row, 0) + "";
				String ngayXuat = table_Phieuxuat.getValueAt(i_row, 1) + "";
				String tenNV = table_Phieuxuat.getValueAt(i_row, 2) + "";
				String maNV = nvBll.getMaNV(tenNV);
				String tenKh = table_Phieuxuat.getValueAt(i_row, 3) + "";
				String maKh = khBll.getMaKH(tenKh);
				double TongTien = Double.valueOf(table_Phieuxuat.getValueAt(i_row, 4) + "");
				HoaDonXuat hdn = new HoaDonXuat(maPhieuXuat, ngayXuat, maKh, maNV, TongTien);
				JOptionPane.showMessageDialog(this, pxBll.xoa(hdn));
				pxBll.docDB();
				loadDataIntoJTable();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// export file excel
		public void exportExcel() {
			try {
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("Danh sach phieu xuat");
				XSSFRow row = null;
				Cell cell = null;
				row = sheet.createRow(3);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Mã phiếu xuất");

				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("Ngày xuất");

				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("Tên Nhân Viên");

				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("Tên Khách hàng");

				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("Tổng tiền");

				int i_row_1 = table_Phieuxuat.getRowCount(); 
				for (int i = 0; i < i_row_1; i++) {	
					sheet.autoSizeColumn(0);
					sheet.autoSizeColumn(1);
					sheet.autoSizeColumn(2);
					sheet.autoSizeColumn(3);
					sheet.autoSizeColumn(4);
					row = sheet.createRow(4 + i);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue(table_Phieuxuat.getValueAt(i, 0)+"");

					cell = row.createCell(1, CellType.STRING);
					cell.setCellValue(table_Phieuxuat.getValueAt(i, 1)+"");

					cell = row.createCell(2, CellType.STRING);
					cell.setCellValue(table_Phieuxuat.getValueAt(i, 2)+"");

					cell = row.createCell(3, CellType.STRING);
					cell.setCellValue(table_Phieuxuat.getValueAt(i, 3)+"");

					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue(table_Phieuxuat.getValueAt(i, 4)+"");
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
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
}
