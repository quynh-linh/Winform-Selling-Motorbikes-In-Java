package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import BUS.NhaCungCapBUS;
import BUS.loaixeBUS;
import BUS.xeMayBUS;
import DAO.xemayController;
import DAO.xemayDAO;
import DTO.LoaiXe;
import DTO.NhaCungCap;
import DTO.XeMayDTO;
import DTO.MyFileChoose;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class xemayGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_timkiem;
	private JTextField textField_Tenxe;
	private JTextField textField_Gia;
	private JComboBox comboBox_Loaixe;
	private JTextField textField_soLuong;
	private JTextField textField_Maxe;
	private JTable table;
	private DefaultTableModel model;
	private XeMayDTO xMayDTO;
	private JLabel lblNewLabel_image;
	private loaixeBUS lxBll = new loaixeBUS();
	String imgPath = null;
	File fileAnhSP;
	private JComboBox comboBox_Search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					xemayGUI frame = new xemayGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public xemayGUI() throws Exception {
		this.init();
		nhapThongTin_LoaiXe();
		nhapThongTin_NCC();
		loadDataIntoJTable();
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setForeground(Color.BLACK);
		setTitle("Danh sách sản phẩm");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Assets/ImgeIconJava/motorcycle-icon.png"));
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1136, 730);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		xemayController controller = new xemayController(this);
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
		textField_timkiem.setBounds(75, 11, 733, 40);
		panel_timkiem.add(textField_timkiem);
		textField_timkiem.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				xoaForm();
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
		btn_Timkiem.setBounds(10, 11, 56, 40);
		panel_timkiem.add(btn_Timkiem);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(253, 61, 867, 630);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 11, 847, 238);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Nh\u1EADp th\u00F4ng tin s\u1EA3n ph\u1EA9m");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 11, 181, 22);
		panel_4.add(lblNewLabel_2);

		JLabel jLabel_Maxe = new JLabel("M\u00E3 xe");
		jLabel_Maxe.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_Maxe.setBounds(10, 51, 60, 22);
		panel_4.add(jLabel_Maxe);

		textField_Maxe = new JTextField();
		textField_Maxe.setBounds(89, 44, 228, 34);
		panel_4.add(textField_Maxe);
		textField_Maxe.setColumns(10);

		JLabel jLabel_Tenxe = new JLabel("T\u00EAn xe");
		jLabel_Tenxe.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_Tenxe.setBounds(10, 101, 60, 22);
		panel_4.add(jLabel_Tenxe);

		textField_Tenxe = new JTextField();
		textField_Tenxe.setBounds(89, 89, 228, 34);
		panel_4.add(textField_Tenxe);

		textField_Gia = new JTextField();
		textField_Gia.setBounds(89, 145, 228, 34);
		panel_4.add(textField_Gia);

		JLabel jLabel_Gia = new JLabel("Gi\u00E1");
		jLabel_Gia.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_Gia.setBounds(10, 159, 60, 22);
		panel_4.add(jLabel_Gia);

		JLabel jLabel_Loaixe = new JLabel("Lo\u1EA1i xe");
		jLabel_Loaixe.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_Loaixe.setBounds(327, 51, 60, 22);
		panel_4.add(jLabel_Loaixe);

		//
		comboBox_Loaixe = new JComboBox();
		comboBox_Loaixe.setEditable(true);
		comboBox_Loaixe.setBounds(397, 44, 175, 34);
		panel_4.add(comboBox_Loaixe);

		textField_soLuong = new JTextField();
		textField_soLuong.setColumns(10);
		textField_soLuong.setBounds(89, 191, 228, 34);
		panel_4.add(textField_soLuong);

		JLabel jLabel_soLuong = new JLabel("Số lượng");
		jLabel_soLuong.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_soLuong.setBounds(10, 205, 60, 22);
		panel_4.add(jLabel_soLuong);

		JButton btn_loadImage = new JButton("Tải ảnh");
		btn_loadImage.setBounds(673, 197, 89, 23);
		btn_loadImage.addActionListener(controller);
		panel_4.add(btn_loadImage);

		lblNewLabel_image = new JLabel("");
		lblNewLabel_image.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_image.setBounds(582, 11, 255, 184);
		panel_4.add(lblNewLabel_image);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 260, 847, 2);
		panel_2.add(separator);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 273, 847, 346);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("Th\u00F4ng tin s\u1EA3n ph\u1EA9m");
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 11, 181, 22);
		panel_5.add(lblNewLabel_2_1);

		table = new JTable();
		table.setRowHeight(50);
		table.addMouseListener((MouseListener) controller);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 48, 827, 242);
		panel_5.add(scrollPane);

		JButton btn_Them = new JButton("Thêm");
		btn_Them.setForeground(Color.BLACK);
		btn_Them.setBackground(Color.WHITE);
		btn_Them.setIcon(new ImageIcon("Assets/ImgeIconJava/add-icon.png"));
		btn_Them.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Them.addActionListener(controller);
		btn_Them.setBounds(45, 301, 121, 34);
		panel_5.add(btn_Them);

		JButton btn_Sua = new JButton("Sửa");
		btn_Sua.setIcon(new ImageIcon("Assets/ImgeIconJava/62968-wrench-icon.png"));
		btn_Sua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Sua.setBounds(195, 301, 121, 34);
		btn_Sua.addActionListener(controller);
		panel_5.add(btn_Sua);

		JButton btn_Xoa = new JButton("Xóa");
		btn_Xoa.setIcon(new ImageIcon("Assets/ImgeIconJava/Windows-Close-Program-icon.png"));
		btn_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Xoa.setBounds(348, 301, 121, 34);
		btn_Xoa.addActionListener(controller);
		panel_5.add(btn_Xoa);

		comboBox_Search = new JComboBox();
		comboBox_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDataIntoJTable();
			}
		});
		comboBox_Search.setBounds(572, 10, 265, 22);
		comboBox_Search.addItem("");
		comboBox_Search.addItem("Giá từ thấp đến cao");
		comboBox_Search.addItem("Giá từ cao đến thấp");
		panel_5.add(comboBox_Search);

		JButton btn_Xoa_1 = new JButton("Import Excel");
		btn_Xoa_1.addActionListener(controller);
		btn_Xoa_1.setIcon(new ImageIcon("Assets/ImgeIconJava/Microsoft-Excel-2013-icon.png"));
		btn_Xoa_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Xoa_1.setBounds(500, 301, 153, 34);
		panel_5.add(btn_Xoa_1);

		JButton btn_Xoa_2 = new JButton("Export Excel");
		btn_Xoa_2.addActionListener(controller);
		btn_Xoa_2.setIcon(new ImageIcon("Assets/ImgeIconJava/Microsoft-Excel-2013-icon.png"));
		btn_Xoa_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_Xoa_2.setBounds(673, 301, 153, 34);
		panel_5.add(btn_Xoa_2);
	}

	// lưu ảnh vào file
	private void luuFileAnh() {
		BufferedImage bImage = null;
		try {
			File initialImage = new File(fileAnhSP.getPath());
			bImage = ImageIO.read(initialImage);

			ImageIO.write(bImage, "png", new File("Assets/Image/" + fileAnhSP.getName()));

		} catch (IOException e) {
			System.out.println("Exception occured :" + e.getMessage());
		}
	}

	// xử lí khi người dùng chọn đuôi loại ảnh
	public void xuLyChonAnh() {
		JFileChooser fileChooser = new MyFileChoose("Assets/Image/");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp hình ảnh", "jpg", "png", "jpeg");
		fileChooser.setFileFilter(filter);
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileAnhSP = fileChooser.getSelectedFile();
			lblNewLabel_image.setIcon(getAnhSP(fileAnhSP.getPath()));
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

	// lấy thông tin từ loại xe lên combobox
	public void nhapThongTin_LoaiXe() throws Exception {
		loaixeBUS lx = new loaixeBUS();
		ArrayList<LoaiXe> arr = lx.getList_XM();
		String[] s = new String[lx.getNumbXeMay() + 1];
		s[0] = "--Select--";
		int i = 1;
		for (LoaiXe dto : arr) {
			s[i] = dto.getMaLoai() + "-" + dto.getTenLoai();
			i++;
		}
		comboBox_Loaixe.setModel(new DefaultComboBoxModel<>(s));
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
	}

	// lấy dữ liệu từ DB vào Jtable
	private void loadDataIntoJTable() {
		model = new DefaultTableModel();
		xemayDAO dal = new xemayDAO();
		List<XeMayDTO> list;
		try {
			// Set Column Title
			Vector column = new Vector();
			column.add("Mã xe");
			column.add("Tên xe");
			column.add("Giá xe");
			column.add("Số lượng");
			column.add("Loại xe");
			column.add("Ảnh");
			model.setColumnIdentifiers(column);
			if (comboBox_Search != null) {
				String loai = comboBox_Search.getSelectedItem() + "";
				System.out.println(loai);
				if (loai.equals("Giá từ thấp đến cao")) {
					list = dal.sortSmall();
					System.out.println("1");
				} else {
					list = dal.sortHight();
				}
			} else {
				list = dal.docDB();
			}
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
			table.setModel(model);
			table.getColumnModel().getColumn(5).setPreferredWidth(150);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// lấy dữ liệu từ text
	public void getDLText() {
		model = (DefaultTableModel) table.getModel();
		String maXe = this.textField_Maxe.getText();
		String tenXe = this.textField_Tenxe.getText();
		Double giaXe = Double.valueOf(this.textField_Gia.getText());
		int soLuong = Integer.valueOf(this.textField_soLuong.getText());
		String cbbloaiXe = this.comboBox_Loaixe.getSelectedItem() + "";
		String[] loaiTmp = cbbloaiXe.split("-");
		String Loaixe = loaiTmp[0].trim();
		String anh = fileAnhSP.getName();
		xMayDTO = new XeMayDTO(maXe, tenXe, giaXe, soLuong, Loaixe, anh);
	}

	// tìm kiếm theo tên
	public void search() {
		String data = this.textField_timkiem.getText() + "";
		System.out.println(data);
		xemayDAO dal = new xemayDAO();
		model.setRowCount(0);
		ArrayList<XeMayDTO> dsxm = dal.search(data);
		;
		for (XeMayDTO xm : dsxm) {
			Vector vec = new Vector();
			vec.add(xm.getMaXe());
			vec.add(xm.getTenXe());
			vec.add(xm.getGiaXe());
			vec.add(xm.getSoLuong());
			String tenLoai = lxBll.getTenLoai(xm.getLoaiXe());
			vec.add(tenLoai);
			vec.add(xm.getMyImage());
			model.addRow(vec);
		}
	}

	// gán ảnh cho jLabel
	public void loadAnh(String anh) {
		lblNewLabel_image.setIcon(getAnhSP(anh));
	}

	public void hienThongTinXeMay() {
		model = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		if (i_row > -1) {
			String ma = model.getValueAt(i_row, 0) + "";
			this.textField_Maxe.setEditable(false);
			String ten = model.getValueAt(i_row, 1) + "";
			String gia = model.getValueAt(i_row, 2) + "";
			String soLuong = model.getValueAt(i_row, 3) + "";
			String loaixe = model.getValueAt(i_row, 4) + "";
			String anh = model.getValueAt(i_row, 5) + "";
			textField_Maxe.setText(ma);
			textField_Tenxe.setText(ten);
			textField_Gia.setText(gia);
			textField_soLuong.setText(soLuong);
			int flag = 0;
			for (int i = 0; i < comboBox_Loaixe.getItemCount(); i++) {
				if (comboBox_Loaixe.getItemAt(i).equals(loaixe)) {
					flag = i;
					break;
				}
			}
			comboBox_Loaixe.setSelectedIndex(flag);
			loadAnh("Assets/Image/" + anh);
		}
	}

	public void xoaForm() {
		this.textField_Maxe.setText("");
		this.textField_Tenxe.setText("");
		this.textField_Gia.setText("");
		this.textField_soLuong.setText("");
		this.comboBox_Loaixe.setSelectedIndex(0);
		loadAnh("");
	}

	// thêm xe máy vào table và dB
	public void addXeMay() {
		try {
			xeMayBUS list_xemay = new xeMayBUS();
			// lỗi nhập thiếu dữ liệu
			if (textField_Maxe.getText().equals("") || textField_Tenxe.getText().equals("")
					|| textField_soLuong.getText().equals("") || textField_Gia.getText().equals("")
					|| comboBox_Loaixe.getSelectedItem() == "--Select") {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập đủ dữ liệu");
			} else {
				XeMayDTO xm = new XeMayDTO();
				String anh = fileAnhSP.getName();
				xm.setMaXe(textField_Maxe.getText());
				xm.setTenXe(textField_Tenxe.getText());
				if (textField_soLuong.getText().equals("")) {
					textField_soLuong.setText("0");
				}
				xm.setGiaXe(Double.valueOf(textField_Gia.getText()));
				xm.setSoLuong(Integer.valueOf(textField_soLuong.getText()));
				String cbbLoaiXe = comboBox_Loaixe.getSelectedItem() + "";
				String[] loaiTmp = cbbLoaiXe.split("-");
				String Loaixe = loaiTmp[0].trim();
				System.out.println(Loaixe);
				xm.setLoaiXe(Loaixe);
				xm.setImage(anh);
				list_xemay.them(xm);
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				list_xemay.docDB();
				loadDataIntoJTable();
				luuFileAnh();
				xoaForm();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// chỉnh sửa nhà cung cấp
	public void editXeMay() {
		getDLText();
		try {
			xeMayBUS list_xm = new xeMayBUS();
			int soLuongDong = model.getRowCount();
			int j = table.getSelectedRow();
			if (j >= 0) {
				model.setValueAt(xMayDTO.getMaXe() + "", j, 0);
				model.setValueAt(xMayDTO.getTenXe() + "", j, 1);
				model.setValueAt(xMayDTO.getGiaXe(), j, 2);
				model.setValueAt(xMayDTO.getSoLuong() + "", j, 3);
				model.setValueAt(xMayDTO.getLoaiXe() + "", j, 4);
				model.setValueAt(xMayDTO.getMyImage() + "", j, 5);
			}
			JOptionPane.showMessageDialog(this, list_xm.sua(xMayDTO));
			xoaForm();
			list_xm.docDB();
			loadDataIntoJTable();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(this, "Thông tin không hợp lệ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeRowXeMay() {
		try {
			getDLText();
			xeMayBUS list_xm = new xeMayBUS();
			int selectedRow = table.getSelectedRow();
			model.removeRow(selectedRow);
			JOptionPane.showMessageDialog(this, list_xm.xoa(xMayDTO));
			;
			list_xm.docDB();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		loadDataIntoJTable();
		xoaForm();
	}

	// Export excel
	public void ExportExcel() {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Danh sach xe may");
			XSSFRow row = null;
			Cell cell = null;
			row = sheet.createRow(3);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã Xe");

			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Tên Xe");

			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Giá Xe");

			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Số Lượng");

			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Mã Loại");

			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Image");

			int i_row = table.getRowCount();
			for (int i = 0; i < i_row; i++) {
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				sheet.autoSizeColumn(4);
				sheet.autoSizeColumn(5);
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

//import file Excel
	public void importexcel() {
		String defaultCurrentDirectoryPath = "/Users/Quanmanh/Downloads/DoAnJava/Excel/importdsxm.xlsx";
		;
		JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
		int excelchooser = excelFileChooser.showOpenDialog(null);

		FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx");
		excelFileChooser.setFileFilter(fnef);
		excelFileChooser.setDialogTitle("Select excel file !");

		if (excelchooser == JFileChooser.APPROVE_OPTION) {

			try {
//				String path = "/Users/Quanmanh/Downloads/DoAnJava/Excel/importdsxm.xlsx";
				File file = new File(defaultCurrentDirectoryPath);
				FileInputStream inputstream = new FileInputStream(file);
				XSSFWorkbook fWorkbook = new XSSFWorkbook(inputstream);
				XSSFSheet fSheet = fWorkbook.getSheetAt(0);

				XSSFRow rowA1 = fSheet.getRow(0);
				XSSFCell cellA1 = rowA1.getCell(0);

				XSSFRow rowA2 = fSheet.getRow(0);
				XSSFCell cellA2 = rowA2.getCell(1);

				XSSFRow rowA3 = fSheet.getRow(0);
				XSSFCell cellA3 = rowA3.getCell(2);

				XSSFRow rowA4 = fSheet.getRow(0);
				XSSFCell cellA4 = rowA4.getCell(3);

				XSSFRow rowA5 = fSheet.getRow(0);
				XSSFCell cellA5 = rowA5.getCell(4);

				XSSFRow rowA6 = fSheet.getRow(0);
				XSSFCell cellA6 = rowA6.getCell(5);

				model = new DefaultTableModel(new String[] { cellA1.toString(), cellA2.toString(), cellA3.toString(),
						cellA4.toString(), cellA5.toString(), cellA6.toString() }, 0);
				for (int j = 0; j <= fSheet.getLastRowNum(); j++) {
					xeMayBUS list_xemay = new xeMayBUS();
					XSSFRow excelRow = fSheet.getRow(j);
					ArrayList<XeMayDTO> arrXm = new ArrayList<XeMayDTO>();
					XeMayDTO xm = new XeMayDTO();
					if (excelRow != null) {
						xm.setMaXe(String.valueOf(excelRow.getCell(0)));
						System.out.println(xm.getMaXe());
						xm.setTenXe(String.valueOf(excelRow.getCell(1)));
						System.out.println(xm.getTenXe());
						String giaXe = String.valueOf(excelRow.getCell(2));
						xm.setGiaXe(Double.valueOf(giaXe));
						System.out.println(xm.getGiaXe());
						String soLuong = String.valueOf(excelRow.getCell(3));
						xm.setSoLuong(Integer.valueOf(soLuong));
						System.out.println(xm.getSoLuong());
						xm.setLoaiXe(String.valueOf(excelRow.getCell(4)));
						System.out.println(xm.getLoaiXe());
						xm.setImage(String.valueOf(excelRow.getCell(5)));
						System.out.println(xm.getMyImage());
						arrXm.add(xm);
					}
					list_xemay.themEcxel(arrXm);
					System.out.println(list_xemay.themEcxel(arrXm));
					loadDataIntoJTable();

				}
				File f = new File(defaultCurrentDirectoryPath);
				try {
					FileInputStream fis = new FileInputStream(f);
					JOptionPane.showMessageDialog(null, "Import successfully...!");
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	public void nhapExcel(JTable tbl) {
		try {
			TableModel dtm = tbl.getModel();

			JFileChooser chooser = new JFileChooser("Excel/importdsxm.xlsx");
			chooser.setDialogTitle("Chọn file");
			FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
			chooser.setFileFilter(fnef);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File fileSelected = chooser.getSelectedFile();
				FileInputStream fis = new FileInputStream(fileSelected);
				BufferedInputStream bis = new BufferedInputStream(fis);

				XSSFWorkbook wb = new XSSFWorkbook(bis);
				Sheet sheet = wb.getSheetAt(0);
				DefaultTableModel dtmtbl = (DefaultTableModel) dtm;
				dtmtbl.setRowCount(0);
				for (int i = 4; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					Vector vec = new Vector();
					for (int j = 0; j < row.getLastCellNum(); j++) {
						if (dtmtbl.getColumnCount() != row.getLastCellNum()) {
							JOptionPane.showMessageDialog(this, "Nhap file that bai");
							return;
						}
						Cell cell = row.getCell(j);
						vec.add(cell);
					}
					dtmtbl.addRow(vec);
				}
				JOptionPane.showMessageDialog(this, "Nhap file thanh cong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void xuLyNhapFileExcel() {
		try {
			xeMayBUS xmBus = new xeMayBUS();
			nhapExcel(table);
			int row = table.getRowCount();
			for (int i = 0; i < row; i++) {
			    String maXe = table.getValueAt(i, 0) + "";
			    String TenXe = table.getValueAt(i, 1) + "";
			    String GiaXe =table.getValueAt(i, 2) + "";
			    String soLuong =table.getValueAt(i, 3) + "";
			    String loaiXe = table.getValueAt(i, 4) + "";
			    String anh = table.getValueAt(i, 5) + "";
			    xmBus.nhapSanPhamTuExcel(maXe, TenXe, GiaXe, soLuong, loaiXe, anh);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
