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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import DAO.NhaCungCapController;
import DAO.nhaCungCapDAO;
import DAO.xemayDAO;
import DTO.NhaCungCap;
import DTO.XeMayDTO;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NhaCungCapView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_TenNCC;
	private JTextField textField_MaNCC;
	private JTextField textField_timkiem;
	private JTable table_1;
	private JTextField textField_diaChi;
	private JTextField textField_Sdt;
	private DefaultTableModel model;
	private JButton btn_Them;
	private DefaultTableModel model_table;
	private NhaCungCap ncc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					NhaCungCapView frame = new NhaCungCapView();
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
	public NhaCungCapView() {
		setForeground(Color.BLACK);
		setTitle("Danh sách nhà cung cấp");
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
		NhaCungCapController controller = new NhaCungCapController(this);

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
		panel_4.setBounds(10, 11, 847, 189);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lbl_NCC = new JLabel("Nh\u1EADp th\u00F4ng tin nh\u00E0 cung c\u1EA5p");
		lbl_NCC.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lbl_NCC.setBounds(10, 11, 205, 22);
		panel_4.add(lbl_NCC);

		JLabel jLabel_MaNCC = new JLabel("M\u00E3 nh\u00E0 cung c\u1EA5p");
		jLabel_MaNCC.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_MaNCC.setBounds(20, 66, 120, 22);
		panel_4.add(jLabel_MaNCC);

		textField_MaNCC = new JTextField();
		textField_MaNCC.setBounds(167, 59, 225, 34);
		panel_4.add(textField_MaNCC);

		JLabel jLabel_TenNCC = new JLabel("T\u00EAn nh\u00E0 cung c\u1EA5p");
		jLabel_TenNCC.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_TenNCC.setBounds(20, 133, 113, 22);
		panel_4.add(jLabel_TenNCC);

		textField_TenNCC = new JTextField();
		textField_TenNCC.setColumns(10);
		textField_TenNCC.setBounds(167, 126, 225, 34);
		panel_4.add(textField_TenNCC);

		JLabel jLabel_DiaChi = new JLabel("\u0110\u1ECBa ch\u1EC9");
		jLabel_DiaChi.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_DiaChi.setBounds(444, 67, 60, 22);
		panel_4.add(jLabel_DiaChi);

		JLabel jLabel_NhaCC = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
		jLabel_NhaCC.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_NhaCC.setBounds(444, 133, 96, 22);
		panel_4.add(jLabel_NhaCC);

		textField_diaChi = new JTextField();
		textField_diaChi.setBounds(561, 60, 225, 34);
		panel_4.add(textField_diaChi);

		textField_Sdt = new JTextField();
		textField_Sdt.setColumns(10);
		textField_Sdt.setBounds(561, 126, 225, 34);
		panel_4.add(textField_Sdt);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 211, 847, 2);
		panel_2.add(separator);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 224, 847, 395);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("Th\u00F4ng tin s\u1EA3n ph\u1EA9m");
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 11, 181, 22);
		panel_5.add(lblNewLabel_2_1);

		table_1 = new JTable();
		table_1.addMouseListener((MouseListener) controller);
		table_1.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(10, 48, 827, 288);
		panel_5.add(scrollPane);

		btn_Them = new JButton("Th\u00EAm");
		btn_Them.setFont(new Font("Arial", Font.PLAIN, 15));
		btn_Them.setIcon(new ImageIcon("Assets/ImgeIconJava/add-icon.png"));
		btn_Them.setBounds(62, 347, 135, 37);
		btn_Them.addActionListener(controller);
		panel_5.add(btn_Them);

		JButton btn_Sua = new JButton("S\u1EEDa");
		btn_Sua.setIcon(new ImageIcon("Assets/ImgeIconJava/62968-wrench-icon.png"));
		btn_Sua.setFont(new Font("Arial", Font.PLAIN, 15));
		btn_Sua.setBounds(250, 348, 135, 37);
		btn_Sua.addActionListener(controller);
		panel_5.add(btn_Sua);

		JButton btn_Xoa = new JButton("X\u00F3a");
		btn_Xoa.setIcon(new ImageIcon("Assets/ImgeIconJava/Windows-Close-Program-icon.png"));
		btn_Xoa.setFont(new Font("Arial", Font.PLAIN, 15));
		btn_Xoa.setBounds(432, 348, 135, 37);
		btn_Xoa.addActionListener(controller);
		panel_5.add(btn_Xoa);
		
		JButton btnExportExcel = new JButton("ExportExcel");
		btnExportExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportExcel();
				
			}
		});
		btnExportExcel.setIcon(new ImageIcon("Assets/ImgeIconJava/Microsoft-Excel-2013-icon.png"));
		btnExportExcel.setFont(new Font("Arial", Font.PLAIN, 15));
		btnExportExcel.setBounds(616, 347, 152, 37);
		panel_5.add(btnExportExcel);
		loadDataIntoJTable();
	}

	public void xoaform() {
		this.textField_MaNCC.setText("");
		this.textField_MaNCC.setEditable(true);
		this.textField_TenNCC.setText("");
		this.textField_diaChi.setText("");
		this.textField_Sdt.setText("");
	}
	public void search() {
		String data = this.textField_timkiem.getText()+"";
		System.out.println(data);
		nhaCungCapDAO dal = new nhaCungCapDAO();
		model.setRowCount(0);
		ArrayList<NhaCungCap> dsxm = dal.search(data);;
		for (NhaCungCap xm : dsxm) {
			Vector row = new Vector();
			row.add(xm.getMaNhaCC());
			row.add(xm.getTenNhaCC());
			row.add(xm.getDiaChi());
			row.add(xm.getSdtNhaCC());
            model.addRow(row);
        }
	}
	// lấy dữ liệu từ DB vào Jtable
	@SuppressWarnings("unchecked")
	private void loadDataIntoJTable() {
		model = new DefaultTableModel();
		nhaCungCapDAO dal = new nhaCungCapDAO();
		List<NhaCungCap> list;
		try {
			// Set Column Title
			@SuppressWarnings("rawtypes")
			Vector column = new Vector();
			column.add("Mã nhà cung cấp");
			column.add("Tên nhà cung cấp");
			column.add("Địa chỉ");
			column.add("Số điện thoại");
			model.setColumnIdentifiers(column);
			list = dal.docDB();
			for (int i = 0; i < list.size(); i++) {
				NhaCungCap ncc = (NhaCungCap) list.get(i);
				Vector row = new Vector();
				row.add(ncc.getMaNhaCC());
				row.add(ncc.getTenNhaCC());
				row.add(ncc.getDiaChi());
				row.add(ncc.getSdtNhaCC());
				model.addRow(row);
			}
			table_1.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// lấy dữ liệu từ text
	public void getDLText() {
		model_table = (DefaultTableModel) table_1.getModel();
		String maNCC = this.textField_MaNCC.getText();
		String tenNCC = this.textField_TenNCC.getText();
		String diaChi = this.textField_diaChi.getText();
		String sdt = this.textField_Sdt.getText();
		ncc = new NhaCungCap(maNCC, tenNCC, diaChi, sdt);
	}

	// hàm lấy dữ liệu nhà cung cấp từ bảng
	public NhaCungCap getNCCTable() {
		DefaultTableModel model_table = (DefaultTableModel) table_1.getModel();
		int i_row = table_1.getSelectedRow();
		// lấy dữ liệu
		String maNCC = model_table.getValueAt(i_row, 0) + "";
		String tenNCC = model_table.getValueAt(i_row, 1) + "";
		String diaChi = model_table.getValueAt(i_row, 2) + "";
		String sdt = model_table.getValueAt(i_row, 3) + "";
		NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, sdt);
		return ncc;
	}

	public void hienThongTinNCC() {
		NhaCungCap ts = getNCCTable();
		this.textField_MaNCC.setText(ts.getMaNhaCC() + "");
		this.textField_MaNCC.setEditable(false);
		this.textField_TenNCC.setText(ts.getTenNhaCC());
		this.textField_diaChi.setText(ts.getDiaChi());
		this.textField_Sdt.setText(ts.getSdtNhaCC() + "");
	}

	public void addNhaCungCap() {
		try {
			NhaCungCapBUS list_ncc = new NhaCungCapBUS();
			// lỗi nhập thiếu dữ liệu
			if (textField_MaNCC.getText().equals("") || textField_TenNCC.getText().equals("")
				|| textField_diaChi.getText().equals("")
					|| textField_Sdt.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập đủ dữ liệu");
			} else {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setMaNhaCC(textField_MaNCC.getText());
				ncc.setTenNhaCC(textField_TenNCC.getText());
				ncc.setDiaChi(textField_diaChi.getText());
				ncc.setSdtNhaCC(textField_Sdt.getText());
				list_ncc.them(ncc);
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				list_ncc.docDB();
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
	public void editNhaCungCap() {
		getDLText();
		try {
			NhaCungCapBUS list_ncc = new NhaCungCapBUS();
			int j = table_1.getSelectedRow();
			if (j >= 0) {
				model_table.setValueAt(ncc.getMaNhaCC() + "", j, 0);
				model_table.setValueAt(ncc.getTenNhaCC() + "", j, 1);
				model_table.setValueAt(ncc.getDiaChi() + "", j, 2);
				model_table.setValueAt(ncc.getSdtNhaCC() + "", j, 3);
			}
			JOptionPane.showMessageDialog(this, list_ncc.sua(ncc));
			xoaform();
			list_ncc.docDB();
			loadDataIntoJTable();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(this, "Thông tin không hợp lệ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removeRowNCC() {
		try {
			getDLText();
			NhaCungCapBUS list_ncc = new NhaCungCapBUS();
			int selectedRow = table_1.getSelectedRow();
			model_table.removeRow(selectedRow);
			JOptionPane.showMessageDialog(this, list_ncc.xoa(ncc));
			;
			list_ncc.docDB();
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
			XSSFSheet sheet = workbook.createSheet("Danh sach nha cung cap");
			XSSFRow row = null;
			Cell cell = null;
			
			row =sheet.createRow(3);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã nhà cung cấp");
			
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Tên nhà cung cấp");
			
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Địa chỉ");
			
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Số điện thoại");
			
			int i_row = table_1.getRowCount();
			for(int i = 0; i<i_row;i++) {
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				row = sheet.createRow(4+i);
				
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(table_1.getValueAt(i, 0)+ "");
				
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(table_1.getValueAt(i, 1)+ "");
				
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(table_1.getValueAt(i, 2)+ "");				
				
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue(table_1.getValueAt(i, 3)+ "");
					
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
