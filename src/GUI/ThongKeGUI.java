package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import BUS.ThongKeBUS;
import DTO.ThongKeDTO;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;


public class ThongKeGUI extends JFrame {

	private JPanel contentPane;
	private ThongKeBUS thongKeBUS = new ThongKeBUS();
	DecimalFormat dcf = new DecimalFormat("###,###");
	private JLabel lblNewLabel_QuantityDT4;
	private JLabel lblNewLabel_QuantityNV4;
	private JLabel lblNewLabel_QuantityXM4;
	private JLabel lblNewLabel_QuantityDT3;
	private JLabel lblNewLabel_QuantityNV3;
	private JLabel lblNewLabel_QuantityXM3;
	private JLabel lblNewLabel_QuantityDT12;
	private JLabel lblNewLabel_QuantityNV2;
	private JLabel lblNewLabel_QuantityXM2;
	private JLabel lblNewLabel_QuantityDT1;
	private JLabel lblNewLabel_QuantityNV_1;
	private JLabel lblNewLabel_QuantityXM_1;
	private JLabel lblNewLabel_QuantityDT;
	private JLabel lblNewLabel_QuantityStaff;
	private JLabel lblNewLabel_QuantityXM;
	private JComboBox<Integer> comboBox_Year;
	private JPanel panel_jFREE;
	private ChartPanel chartPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					ThongKeGUI frame = new ThongKeGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ThongKeGUI() {
		this.init();
		thongKe();
		thongKeTheoQuy();
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setForeground(Color.BLACK);
		setTitle("Thống kê");
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
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBackground(Color.DARK_GRAY);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabbedPane.setBounds(252, 0, 868, 691);
		contentPane.add(tabbedPane);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Tổng quát", null, panel_2, null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.DARK_GRAY));
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 11, 847, 160);
		panel_2.add(panel);

		JLabel lblNewLabel_1 = new JLabel("Tổng quan");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 11, 111, 33);
		panel.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(UIManager.getBorder("Tree.editorBorder"));
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(32, 55, 222, 94);
		panel.add(panel_1);

		JLabel lblNewLabel_2 = new JLabel("Số lượng Xe Máy");
		lblNewLabel_2.setIcon(new ImageIcon("Assets/ImgeIconJava/Motorcycle-icon (1).png"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 11, 202, 72);
		panel_1.add(lblNewLabel_2);

		lblNewLabel_QuantityXM = new JLabel("2210");
		lblNewLabel_QuantityXM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityXM.setForeground(Color.WHITE);
		lblNewLabel_QuantityXM.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityXM.setBounds(61, 58, 151, 25);
		panel_1.add(lblNewLabel_QuantityXM);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(UIManager.getBorder("Tree.editorBorder"));
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(318, 55, 222, 94);
		panel.add(panel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("Số lượng nhân viên");
		lblNewLabel_2_1.setIcon(new ImageIcon("Assets/ImgeIconJava/Groups-Meeting-Dark-icon.png"));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(10, 11, 202, 72);
		panel_1_1.add(lblNewLabel_2_1);

		lblNewLabel_QuantityStaff = new JLabel("3");
		lblNewLabel_QuantityStaff.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityStaff.setForeground(Color.WHITE);
		lblNewLabel_QuantityStaff.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityStaff.setBounds(50, 58, 162, 25);
		panel_1_1.add(lblNewLabel_QuantityStaff);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBorder(UIManager.getBorder("Tree.editorBorder"));
		panel_1_2.setBackground(Color.DARK_GRAY);
		panel_1_2.setBounds(594, 55, 222, 94);
		panel.add(panel_1_2);

		JLabel lblNewLabel_2_2 = new JLabel("Tổng doanh thu");
		lblNewLabel_2_2.setIcon(new ImageIcon("Assets/ImgeIconJava/coins-icon.png"));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_2_2.setBounds(10, 11, 202, 72);
		panel_1_2.add(lblNewLabel_2_2);

		lblNewLabel_QuantityDT = new JLabel("12,070,530");
		lblNewLabel_QuantityDT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityDT.setForeground(Color.WHITE);
		lblNewLabel_QuantityDT.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityDT.setBounds(67, 58, 145, 25);
		panel_1_2.add(lblNewLabel_QuantityDT);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.DARK_GRAY));
		panel_4.setBackground(Color.GRAY);
		panel_4.setBounds(10, 182, 847, 470);
		panel_2.add(panel_4);

		JLabel lblNewLabel_4 = new JLabel("Chi tiết thống kê");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(10, 11, 180, 25);
		panel_4.add(lblNewLabel_4);

		comboBox_Year = new JComboBox();
		comboBox_Year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thongKeTheoQuy();
			}
		});
		comboBox_Year.setForeground(Color.BLACK);
		comboBox_Year.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_Year.setBackground(Color.WHITE);
		comboBox_Year.setBounds(657, 12, 180, 22);
		panel_4.add(comboBox_Year);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBackground(Color.DARK_GRAY);
		panel_5.setBounds(10, 47, 827, 76);
		panel_4.add(panel_5);

		JLabel lblNewLabel = new JLabel("Quý 1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 0, 76, 76);
		panel_5.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(100, 37, 142, 2);
		panel_5.add(separator);

		JLabel lblNewLabel_3 = new JLabel("Số lượng xe máy");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(100, 11, 142, 19);
		panel_5.add(lblNewLabel_3);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(360, 37, 142, 2);
		panel_5.add(separator_1);

		JLabel lblNewLabel_3_1 = new JLabel("Số lượng nhân viên");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(360, 11, 142, 19);
		panel_5.add(lblNewLabel_3_1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.LIGHT_GRAY);
		separator_1_1.setBackground(Color.LIGHT_GRAY);
		separator_1_1.setBounds(586, 37, 142, 2);
		panel_5.add(separator_1_1);

		JLabel lblNewLabel_3_1_1 = new JLabel("Doanh thu quý 1");
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3_1_1.setBounds(586, 11, 142, 19);
		panel_5.add(lblNewLabel_3_1_1);

		lblNewLabel_QuantityXM_1 = new JLabel("0");
		lblNewLabel_QuantityXM_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityXM_1.setForeground(Color.WHITE);
		lblNewLabel_QuantityXM_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityXM_1.setBounds(91, 51, 151, 25);
		panel_5.add(lblNewLabel_QuantityXM_1);

		lblNewLabel_QuantityNV_1 = new JLabel("0");
		lblNewLabel_QuantityNV_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityNV_1.setForeground(Color.WHITE);
		lblNewLabel_QuantityNV_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityNV_1.setBounds(351, 51, 151, 25);
		panel_5.add(lblNewLabel_QuantityNV_1);

		lblNewLabel_QuantityDT1 = new JLabel("0");
		lblNewLabel_QuantityDT1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityDT1.setForeground(Color.WHITE);
		lblNewLabel_QuantityDT1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityDT1.setBounds(577, 51, 151, 25);
		panel_5.add(lblNewLabel_QuantityDT1);

		JPanel panel_5_1 = new JPanel();
		panel_5_1.setLayout(null);
		panel_5_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5_1.setBackground(Color.DARK_GRAY);
		panel_5_1.setBounds(10, 148, 827, 76);
		panel_4.add(panel_5_1);

		JLabel lblQu_2 = new JLabel("Quý 2");
		lblQu_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblQu_2.setForeground(Color.WHITE);
		lblQu_2.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblQu_2.setBounds(0, 0, 76, 76);
		panel_5_1.add(lblQu_2);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(Color.LIGHT_GRAY);
		separator_2.setBounds(100, 37, 142, 2);
		panel_5_1.add(separator_2);

		JLabel lblNewLabel_3_2 = new JLabel("Số lượng xe máy");
		lblNewLabel_3_2.setForeground(Color.WHITE);
		lblNewLabel_3_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3_2.setBounds(100, 11, 142, 19);
		panel_5_1.add(lblNewLabel_3_2);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(Color.LIGHT_GRAY);
		separator_1_2.setBackground(Color.LIGHT_GRAY);
		separator_1_2.setBounds(360, 37, 142, 2);
		panel_5_1.add(separator_1_2);

		JLabel lblNewLabel_3_1_2 = new JLabel("Số lượng nhân viên");
		lblNewLabel_3_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_2.setForeground(Color.WHITE);
		lblNewLabel_3_1_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3_1_2.setBounds(360, 11, 142, 19);
		panel_5_1.add(lblNewLabel_3_1_2);

		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setForeground(Color.LIGHT_GRAY);
		separator_1_1_1.setBackground(Color.LIGHT_GRAY);
		separator_1_1_1.setBounds(586, 37, 142, 2);
		panel_5_1.add(separator_1_1_1);

		JLabel lblNewLabel_3_1_1_1 = new JLabel("Doanh thu quý 2");
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3_1_1_1.setBounds(586, 11, 142, 19);
		panel_5_1.add(lblNewLabel_3_1_1_1);

		lblNewLabel_QuantityXM2 = new JLabel("0");
		lblNewLabel_QuantityXM2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityXM2.setForeground(Color.WHITE);
		lblNewLabel_QuantityXM2.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityXM2.setBounds(91, 51, 151, 25);
		panel_5_1.add(lblNewLabel_QuantityXM2);

		lblNewLabel_QuantityNV2 = new JLabel("0");
		lblNewLabel_QuantityNV2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityNV2.setForeground(Color.WHITE);
		lblNewLabel_QuantityNV2.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityNV2.setBounds(351, 51, 151, 25);
		panel_5_1.add(lblNewLabel_QuantityNV2);

		lblNewLabel_QuantityDT12 = new JLabel("0");
		lblNewLabel_QuantityDT12.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityDT12.setForeground(Color.WHITE);
		lblNewLabel_QuantityDT12.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityDT12.setBounds(577, 51, 151, 25);
		panel_5_1.add(lblNewLabel_QuantityDT12);

		JPanel panel_5_2 = new JPanel();
		panel_5_2.setLayout(null);
		panel_5_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5_2.setBackground(Color.DARK_GRAY);
		panel_5_2.setBounds(10, 249, 827, 76);
		panel_4.add(panel_5_2);

		JLabel lblQu_1 = new JLabel("Quý 3");
		lblQu_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblQu_1.setForeground(Color.WHITE);
		lblQu_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblQu_1.setBounds(0, 0, 76, 76);
		panel_5_2.add(lblQu_1);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.LIGHT_GRAY);
		separator_3.setBackground(Color.LIGHT_GRAY);
		separator_3.setBounds(100, 37, 142, 2);
		panel_5_2.add(separator_3);

		JLabel lblNewLabel_3_3 = new JLabel("Số lượng xe máy");
		lblNewLabel_3_3.setForeground(Color.WHITE);
		lblNewLabel_3_3.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3_3.setBounds(100, 11, 142, 19);
		panel_5_2.add(lblNewLabel_3_3);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(Color.LIGHT_GRAY);
		separator_1_3.setBackground(Color.LIGHT_GRAY);
		separator_1_3.setBounds(360, 37, 142, 2);
		panel_5_2.add(separator_1_3);

		JLabel lblNewLabel_3_1_3 = new JLabel("Số lượng nhân viên");
		lblNewLabel_3_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_3.setForeground(Color.WHITE);
		lblNewLabel_3_1_3.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3_1_3.setBounds(360, 11, 142, 19);
		panel_5_2.add(lblNewLabel_3_1_3);

		JSeparator separator_1_1_2 = new JSeparator();
		separator_1_1_2.setForeground(Color.LIGHT_GRAY);
		separator_1_1_2.setBackground(Color.LIGHT_GRAY);
		separator_1_1_2.setBounds(586, 37, 142, 2);
		panel_5_2.add(separator_1_1_2);

		JLabel lblNewLabel_3_1_1_2 = new JLabel("Doanh thu quý 3");
		lblNewLabel_3_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_3_1_1_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3_1_1_2.setBounds(586, 11, 142, 19);
		panel_5_2.add(lblNewLabel_3_1_1_2);

		lblNewLabel_QuantityXM3 = new JLabel("0");
		lblNewLabel_QuantityXM3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityXM3.setForeground(Color.WHITE);
		lblNewLabel_QuantityXM3.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityXM3.setBounds(91, 51, 151, 25);
		panel_5_2.add(lblNewLabel_QuantityXM3);

		lblNewLabel_QuantityNV3 = new JLabel("0");
		lblNewLabel_QuantityNV3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityNV3.setForeground(Color.WHITE);
		lblNewLabel_QuantityNV3.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityNV3.setBounds(351, 51, 151, 25);
		panel_5_2.add(lblNewLabel_QuantityNV3);

		lblNewLabel_QuantityDT3 = new JLabel("0");
		lblNewLabel_QuantityDT3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityDT3.setForeground(Color.WHITE);
		lblNewLabel_QuantityDT3.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityDT3.setBounds(577, 51, 151, 25);
		panel_5_2.add(lblNewLabel_QuantityDT3);

		JPanel panel_5_3 = new JPanel();
		panel_5_3.setLayout(null);
		panel_5_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5_3.setBackground(Color.DARK_GRAY);
		panel_5_3.setBounds(10, 350, 827, 76);
		panel_4.add(panel_5_3);

		JLabel lblQu = new JLabel("Quý 4");
		lblQu.setHorizontalAlignment(SwingConstants.CENTER);
		lblQu.setForeground(Color.WHITE);
		lblQu.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblQu.setBounds(0, 0, 76, 76);
		panel_5_3.add(lblQu);

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.LIGHT_GRAY);
		separator_4.setBackground(Color.LIGHT_GRAY);
		separator_4.setBounds(100, 37, 142, 2);
		panel_5_3.add(separator_4);

		JLabel lblNewLabel_3_4 = new JLabel("Số lượng xe máy");
		lblNewLabel_3_4.setForeground(Color.WHITE);
		lblNewLabel_3_4.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3_4.setBounds(100, 11, 142, 19);
		panel_5_3.add(lblNewLabel_3_4);

		JSeparator separator_1_4 = new JSeparator();
		separator_1_4.setForeground(Color.LIGHT_GRAY);
		separator_1_4.setBackground(Color.LIGHT_GRAY);
		separator_1_4.setBounds(360, 37, 142, 2);
		panel_5_3.add(separator_1_4);

		JLabel lblNewLabel_3_1_4 = new JLabel("Số lượng nhân viên");
		lblNewLabel_3_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_4.setForeground(Color.WHITE);
		lblNewLabel_3_1_4.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3_1_4.setBounds(360, 11, 142, 19);
		panel_5_3.add(lblNewLabel_3_1_4);

		JSeparator separator_1_1_3 = new JSeparator();
		separator_1_1_3.setForeground(Color.LIGHT_GRAY);
		separator_1_1_3.setBackground(Color.LIGHT_GRAY);
		separator_1_1_3.setBounds(586, 37, 142, 2);
		panel_5_3.add(separator_1_1_3);

		JLabel lblNewLabel_3_1_1_3 = new JLabel("Doanh thu quý 4");
		lblNewLabel_3_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_3.setForeground(Color.WHITE);
		lblNewLabel_3_1_1_3.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_3_1_1_3.setBounds(586, 11, 142, 19);
		panel_5_3.add(lblNewLabel_3_1_1_3);

		lblNewLabel_QuantityXM4 = new JLabel("0");
		lblNewLabel_QuantityXM4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityXM4.setForeground(Color.WHITE);
		lblNewLabel_QuantityXM4.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityXM4.setBounds(91, 51, 151, 25);
		panel_5_3.add(lblNewLabel_QuantityXM4);

		lblNewLabel_QuantityNV4 = new JLabel("0");
		lblNewLabel_QuantityNV4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityNV4.setForeground(Color.WHITE);
		lblNewLabel_QuantityNV4.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityNV4.setBounds(351, 51, 151, 25);
		panel_5_3.add(lblNewLabel_QuantityNV4);

		lblNewLabel_QuantityDT4 = new JLabel("0");
		lblNewLabel_QuantityDT4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_QuantityDT4.setForeground(Color.WHITE);
		lblNewLabel_QuantityDT4.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_QuantityDT4.setBounds(577, 51, 151, 25);
		panel_5_3.add(lblNewLabel_QuantityDT4);

		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Biểu đồ", null, panel_6, null);
		panel_6.setLayout(null);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(UIManager.getColor("Button.darkShadow"));
		panel_7.setBounds(0, 0, 863, 658);
		panel_6.add(panel_7);
		panel_7.setLayout(null);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.DARK_GRAY);
		panel_8.setBounds(10, 11, 843, 73);
		panel_7.add(panel_8);
		panel_8.setLayout(null);

		panel_jFREE = new JPanel();
		panel_jFREE.setBackground(Color.DARK_GRAY);
		// ========BIỂU ĐỒ CỘT=============
		chartPanel = new ChartPanel(createChart());
		chartPanel.setBackground(Color.DARK_GRAY);
		chartPanel.setPreferredSize(new Dimension(843, 552));
		panel_jFREE.setBounds(10, 95, 843, 552);
		panel_jFREE.add(chartPanel);
		panel_7.add(panel_jFREE);

		JLabel lblNewLabel_5 = new JLabel("BIỂU ĐỒ BIỂU ĐẠT DOANH THU");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(0, 0, 843, 73);
		lblNewLabel_5.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblNewLabel_5);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = year; i >= year - 10; i--)
			comboBox_Year.addItem(i);
		this.setVisible(true);
	}
	public void thongKe() {

		lblNewLabel_QuantityXM.setText(thongKeBUS.getTongXeMay()+"");
		lblNewLabel_QuantityStaff.setText(thongKeBUS.getTongNhanVien()+"");
		lblNewLabel_QuantityDT.setText(dcf.format(thongKeBUS.getTongDoanhThu()));	
	}

	public void thongKeTheoQuy() {
		int nam = Integer.valueOf(comboBox_Year.getSelectedItem() + "");
		ThongKeDTO thongke = thongKeBUS.thongKe(nam);
		// quý 1
		this.lblNewLabel_QuantityNV_1.setText(thongke.getSoLuongNhanVien(1) + "");
		this.lblNewLabel_QuantityDT1.setText(thongke.getTongQuy(1) + "");
		this.lblNewLabel_QuantityXM_1.setText(thongke.getSoLuongXeMay(1) + "");
		// quý 2
		this.lblNewLabel_QuantityXM2.setText(thongke.getSoLuongXeMay(2) + "");
		this.lblNewLabel_QuantityNV2.setText(thongke.getSoLuongNhanVien(2) + "");
		this.lblNewLabel_QuantityDT12.setText(thongke.getTongQuy(2) + "");
		// quý 3
		this.lblNewLabel_QuantityXM3.setText(thongke.getSoLuongXeMay(3) + "");
		this.lblNewLabel_QuantityNV3.setText(thongke.getSoLuongNhanVien(3) + "");
		this.lblNewLabel_QuantityDT3.setText(thongke.getTongQuy(3) + "");
		// quý 4
		this.lblNewLabel_QuantityXM4.setText(thongke.getSoLuongXeMay(4) + "");
		this.lblNewLabel_QuantityNV4.setText(thongke.getSoLuongNhanVien(4) + "");
		this.lblNewLabel_QuantityDT4.setText(thongke.getTongQuy(4) + "");
	}

	private JFreeChart createChart() {
		JFreeChart barChart = ChartFactory.createBarChart3D(
				"Doanh thu năm " + Calendar.getInstance().get(Calendar.YEAR), "Tháng", "Doanh thu", createDataset(),
				PlotOrientation.VERTICAL, false, false, false);
		CategoryPlot plot = barChart.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE);
		/* Change Bar colors */
		plot.setRangeGridlinePaint(Color.GRAY);
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, Color.BLACK);
		return barChart;
	}
	
	private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        String [] i = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        for (String string : i) {
        	double value = thongKeBUS.getDoanhThuThang(string,Calendar.getInstance().get(Calendar.YEAR));
            dataset.addValue(value, "Doanh thu", string + "");
		}
        return dataset;
    }
}
