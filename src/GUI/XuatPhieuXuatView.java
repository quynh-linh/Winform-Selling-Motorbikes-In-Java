package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BUS.BanHangBUS;
import BUS.ChiTietPhieuXuatBUS;
import BUS.PhieuNhapBUS;
import BUS.PhieuXuatBUS;
import BUS.xeMayBUS;
import DAO.ChiTietPhieuXuatDAO;
import DTO.ChiTietHoaDonNhap;
import DTO.ChiTietHoaDonXuat;
import DTO.XeMayDTO;

public class XuatPhieuXuatView extends JFrame {

	private JPanel contentPane;
	private boolean checkNhap = false;
	private JButton btn_xacNhan;
	private JButton btnInPhieuXuat;
	private JEditorPane editorPane;
	private ArrayList<ChiTietHoaDonXuat> listCTPhieuXuat = null;
	private double tongTien;
	String khachHang, ngayXuat, maPX, nhanVien;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XuatPhieuXuatView frame = new XuatPhieuXuatView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public XuatPhieuXuatView(String ngayXuat, String maPX, String khachHang, String nhanVien,
			ArrayList<ChiTietHoaDonXuat> listCTPhieuXuat) {

		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.listCTPhieuXuat = listCTPhieuXuat;
		this.ngayXuat = ngayXuat;
		this.maPX = maPX;
		this.init();
		this.editorPane.setEditable(false);
		for (ChiTietHoaDonXuat ctpx : this.listCTPhieuXuat) {
			this.tongTien += ctpx.gettongTien();
		}
		System.out.println(tongTien);
		this.setLocationRelativeTo(null);
		btnInPhieuXuat.setEnabled(false);
	}

	public XuatPhieuXuatView() {
		// TODO Auto-generated constructor stub
		this.init();
	}

	public void init() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 622, 481);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Thông tin phiếu xuất");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 11, 586, 45);
		contentPane.add(lblNewLabel);

		btn_xacNhan = new JButton("Xác nhận");
		btn_xacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					xacNhanNhap();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_xacNhan.setBounds(130, 381, 126, 39);
		contentPane.add(btn_xacNhan);

		btnInPhieuXuat = new JButton("In phiếu xuất");
		btnInPhieuXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print();
			}
		});
		btnInPhieuXuat.setBounds(341, 381, 126, 39);
		contentPane.add(btnInPhieuXuat);

		editorPane = new JEditorPane();
		editorPane.setEditable(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 586, 302);
		scrollPane.setViewportView(editorPane);
		contentPane.add(scrollPane);
	}

	public void xacNhanNhap() throws Exception {
		checkNhap = true;
		btn_xacNhan.setEnabled(false);
		btnInPhieuXuat.setEnabled(true);

		xeMayBUS xmBll = new xeMayBUS();
		ArrayList<XeMayDTO> listxm = xmBll.getList_SP();
		editorPane.setContentType("text/html");

		DecimalFormat dcf = new DecimalFormat("###,### VND");

		PhieuXuatBUS pnBll = new PhieuXuatBUS();
		pnBll.themPhieuXuat(maPX, ngayXuat, khachHang, nhanVien, tongTien);
	

		ChiTietPhieuXuatBUS ctBll = new ChiTietPhieuXuatBUS();
		String hd = "<style> " + "table {" + "border: 1px solid;" + "border-bottom: none" + "}" + "tr {"
				+ "border-bottom: 1px solid;" + "}" + "td {" + "padding: 8px;" + "} " + "th {" + "font-size:16pt" + "}"
				+ "</style>";
		hd += "<h1 style='text-align:center;'>CHI TIẾT PHIẾU NHẬP</h1>";
		hd += "Nhân viên: " + nhanVien + "<br/>";
		hd += "Ngày xuất: " + ngayXuat + "<br/>";
		hd += "Khách : " + khachHang + "<br/>";
		hd += "<div style='text-align:center;'>==========================================</div><br/>";
		hd += "<div style='text-align:center'>";
		hd += "<table style='max-width:100%'>";
		hd += "<tr>" + "<th>Mã SP</th>" + "<th>Tên SP</th>" + "<th>Số lượng</th>" + "<th>Đơn giá</th>"
				+ "<th>Thành tiền</th>" + "</tr>";
		for (ChiTietHoaDonXuat ctpx : listCTPhieuXuat) {
			hd += "<tr>";
			hd += "<td style='text-align:center;'>" + ctpx.getMaXe() + "</td>";
			for (XeMayDTO sp : listxm) {
				if (sp.getMaXe().equals(ctpx.getMaXe())) {
					hd += "<td style='text-align:left;'>" + sp.getTenXe() + "</td>";
					break;
				}
			}
			hd += "<td style='text-align:center;'>" + ctpx.getSoLuong() + "</td>";
			hd += "<td style='text-align:center;'>" + dcf.format(ctpx.getDonGia()) + "</td>";
			hd += "<td style='text-align:center;'>" + dcf.format(ctpx.gettongTien()) + "</td>";
			hd += "</tr>";

			// ===================================================
			// ===================LƯU CTPN VÀO DB=================
			// ===================================================
			ctpx.setMaPX(maPX);
			ctBll.luuCTPhieuXuat(ctpx);
		}

		hd += "<tr>";
		hd += "<td style='text-align:center;'>" + "</td>";
		hd += "<td style='text-align:left;'>" + "</td>";
		hd += "<td style='text-align:center;'>" + "</td>";
		hd += "<td style='text-align:center;font-weight:bold'>Thành tiền</td>";
		hd += "<td style='text-align:center;'>" + dcf.format(tongTien) + "</td>";
		hd += "</tr>";
		hd += "</table>";
		hd += "</div>";
		hd += "<div style='text-align:center;'>==========================================</div><br/>";
		editorPane.setText(hd);
	}
	public void print() {
		try {
			editorPane.print();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
