package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
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

import BUS.loaixeBUS;
import BUS.xeMayBUS;
import DAO.loaixeController;
import DAO.loaixeDAO;
import DAO.xemayDAO;
import DTO.LoaiXe;
import DTO.NhaCungCap;
import DTO.XeMayDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DanhMucSPView extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_Maxe;
	private JTextField textField_timkiem;
	private JTextField textField_Tenxe;
	private DefaultTableModel model;
	private LoaiXe lx;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					DanhMucSPView frame = new DanhMucSPView();
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
	public DanhMucSPView() {
		setForeground(Color.BLACK);
		setTitle("Danh mục sản phẩm");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("D:\\Java_ky_2\\DoAnJava\\Assets\\ImgeIconJava\\motorcycle-icon.png"));
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1136, 730);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		loaixeController controller = new loaixeController(this);
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
		panel_4.setBounds(10, 11, 847, 103);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Nhập thông tin loại xe");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 11, 163, 22);
		panel_4.add(lblNewLabel_2);

		JLabel jLabel_Maxe = new JLabel("Mã loại");
		jLabel_Maxe.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_Maxe.setBounds(20, 52, 60, 22);
		panel_4.add(jLabel_Maxe);

		textField_Maxe = new JTextField();
		textField_Maxe.setBounds(99, 44, 299, 37);
		panel_4.add(textField_Maxe);
		textField_Maxe.setColumns(10);

		JLabel jLabel_Tenxe = new JLabel("Tên loại xe");
		jLabel_Tenxe.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		jLabel_Tenxe.setBounds(425, 52, 91, 22);
		panel_4.add(jLabel_Tenxe);

		textField_Tenxe = new JTextField();
		textField_Tenxe.setColumns(10);
		textField_Tenxe.setBounds(526, 44, 299, 37);
		panel_4.add(textField_Tenxe);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 125, 847, 2);
		panel_2.add(separator);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 138, 847, 481);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("Thông tin loại xe");
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 11, 181, 22);
		panel_5.add(lblNewLabel_2_1);

		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		table.setRowHeight(50);
		table.addMouseListener((MouseListener) controller);
		scrollPane.setBounds(10, 32, 827, 386);
		panel_5.add(scrollPane);

		JButton btn_them = new JButton("Thêm");
		btn_them.setIcon(new ImageIcon("Assets/ImgeIconJava/add-icon.png"));
		btn_them.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_them.setBounds(64, 429, 136, 41);
		btn_them.addActionListener(controller);
		panel_5.add(btn_them);

		JButton btn_sua = new JButton("Sửa");
		btn_sua.setIcon(new ImageIcon("Assets/ImgeIconJava/62968-wrench-icon.png"));
		btn_sua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_sua.setBounds(238, 429, 136, 41);
		btn_sua.addActionListener(controller);
		panel_5.add(btn_sua);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("Assets/ImgeIconJava/Windows-Close-Program-icon.png"));
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoa.setBounds(434, 429, 136, 41);
		panel_5.add(btnXoa);
		
		JButton btnExportexcel = new JButton("ExportExcel");
		btnExportexcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportExcel();
				
			}
		});
		btnExportexcel.setIcon(new ImageIcon("Assets/ImgeIconJava/Microsoft-Excel-2013-icon.png"));
		btnExportexcel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExportexcel.setBounds(635, 430, 153, 41);
		panel_5.add(btnExportexcel);
		btnXoa.addActionListener(controller);
		loadDataIntoJTable();
	}

	public void xoaform() {
		this.textField_Maxe.setText("");
		this.textField_Maxe.setEditable(true);
		this.textField_Tenxe.setText("");
	}

	// tìm kiếm theo tên loại
	public void search() {
		loaixeDAO dal = new loaixeDAO();
		String data = this.textField_timkiem.getText() + "";
		System.out.println(data);
		model.setRowCount(0);
		ArrayList<LoaiXe> dsxm = dal.search(data);
		;
		for (LoaiXe lx : dsxm) {
			Vector vec = new Vector();
			vec.add(lx.getMaLoai());
			vec.add(lx.getTenLoai());
			model.addRow(vec);
		}
	}

	// lấy dữ liệu từ DB vào Jtable
	private void loadDataIntoJTable() {
		model = new DefaultTableModel();
		try {
			loaixeDAO dal = new loaixeDAO();
			// Set Column Title
			Vector column = new Vector();
			column.add("Mã loại");
			column.add("Tên loại");
			model.setColumnIdentifiers(column);
			List<LoaiXe> list = dal.docDB();
			for (int i = 0; i < list.size(); i++) {
				LoaiXe ncc = (LoaiXe) list.get(i);
				Vector row = new Vector();
				row.add(ncc.getMaLoai());
				row.add(ncc.getTenLoai());
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
		String maNCC = this.textField_Maxe.getText();
		String tenNCC = this.textField_Tenxe.getText();
		lx = new LoaiXe(maNCC, tenNCC);
	}

	public void addLoaiXe() {
		try {
			loaixeBUS dal = new loaixeBUS();
			// lỗi nhập thiếu dữ liệu
			if (textField_Maxe.getText().equals("") || textField_Tenxe.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập đủ dữ liệu");
			} else {
				LoaiXe lx = new LoaiXe();
				lx.setMaLoai(textField_Maxe.getText());
				lx.setTenLoai(textField_Tenxe.getText());
				JOptionPane.showMessageDialog(this, dal.them(lx));
				dal.docDB();
				loadDataIntoJTable();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// hàm lấy dữ liệu loại xe từ bảng
	public LoaiXe getLoaiXeTable() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		// lấy dữ liệu
		String maLoaiXe = model_table.getValueAt(i_row, 0) + "";
		String tenLoaiXe = model_table.getValueAt(i_row, 1) + "";
		LoaiXe lx = new LoaiXe(maLoaiXe, tenLoaiXe);
		return lx;
	}

	public void hienThongTinLoaiXe() {
		LoaiXe lx = getLoaiXeTable();
		this.textField_Maxe.setText(lx.getMaLoai() + "");
		this.textField_Maxe.setEditable(false);
		this.textField_Tenxe.setText(lx.getTenLoai() + "");
	}

	// chỉnh sửa nhà cung cấp
	public void editLoaiXe() {
		getDLText();
		try {
			loaixeBUS dal = new loaixeBUS();
			int soLuongDong = model.getRowCount();
			int j = table.getSelectedRow();
			if (j >= 0) {
				model.setValueAt(lx.getMaLoai() + "", j, 0);
				model.setValueAt(lx.getTenLoai() + "", j, 1);
			}
			JOptionPane.showMessageDialog(this, dal.sua(lx));

			xoaform();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(this, "Thông tin không hợp lệ");
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeRowLoaiXe() {
		getDLText();
		try {
			loaixeBUS dal = new loaixeBUS();
			int selectedRow = table.getSelectedRow();
			model.removeRow(selectedRow);
			JOptionPane.showMessageDialog(this, dal.xoa(lx));
			dal.docDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xoaform();
	}

	// export file excel
	public void exportExcel() {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Danh sach loai SP");
			XSSFRow row = null;
			Cell cell = null;
			
			row =sheet.createRow(3);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã loại");
			
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Tên loại");
			
			int i_row = table.getRowCount();
			for(int i = 0; i<i_row;i++) {
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				row = sheet.createRow(4+i);
				
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(table.getValueAt(i, 0)+ "");
				
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(table.getValueAt(i, 1)+ "");
					
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
