package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.ChiTietPhieuNhapBUS;
import BUS.PhieuNhapBUS;
import BUS.xeMayBUS;
import DTO.ChiTietHoaDonNhap;
import DTO.XeMayDTO;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;

public class XuatPhieuNhapView extends JFrame {

	private JPanel contentPane;
	private boolean checkNhap = false;
	private JButton btn_xacNhan;
	private JButton btnInPhieuNhap;
	private JEditorPane editorPane;
	private ArrayList<ChiTietHoaDonNhap> listCTPhieuNhap = null;
	private int tongTien;
	String nhaCungCap, ngayNhap, maPN, nhanVien;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XuatPhieuNhapView frame = new XuatPhieuNhapView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public XuatPhieuNhapView(String ngaynhap, String mapn, String nhaCungCap, String nhanVien,
			ArrayList<ChiTietHoaDonNhap> listCTPhieuNhap) {

		this.nhaCungCap = nhaCungCap;
		this.nhanVien = nhanVien;
		this.listCTPhieuNhap = listCTPhieuNhap;
		this.ngayNhap = ngaynhap;
		this.maPN = mapn;
		this.init();
		this.editorPane.setEditable(false);
		for (ChiTietHoaDonNhap ctpn : this.listCTPhieuNhap) {
			this.tongTien += ctpn.getTongTien();
		}
		this.setLocationRelativeTo(null);
		btnInPhieuNhap.setEnabled(false);
	}

	public XuatPhieuNhapView() {
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

		JLabel lblNewLabel = new JLabel("Thông tin phiếu nhập");
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

		btnInPhieuNhap = new JButton("In phiếu nhập");
		btnInPhieuNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print();
			}
		});
		btnInPhieuNhap.setBounds(341, 381, 126, 39);
		contentPane.add(btnInPhieuNhap);

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
		btnInPhieuNhap.setEnabled(true);

		xeMayBUS xmBll = new xeMayBUS();
		ArrayList<XeMayDTO> listxm = xmBll.getList_SP();
		editorPane.setContentType("text/html");

		DecimalFormat dcf = new DecimalFormat("###,### VND");

		PhieuNhapBUS pnBll = new PhieuNhapBUS();
		pnBll.themPhieuNhap(maPN, ngayNhap, nhaCungCap, nhanVien, tongTien);

		ChiTietPhieuNhapBUS ctBll = new ChiTietPhieuNhapBUS();
		String hd = "<style> " + "table {" + "border: 1px solid;" + "border-bottom: none" + "}" + "tr {"
				+ "border-bottom: 1px solid;" + "}" + "td {" + "padding: 8px;" + "} " + "th {" + "font-size:16pt" + "}"
				+ "</style>";
		hd += "<h1 style='text-align:center;'>CHI TIẾT PHIẾU NHẬP</h1>";
		hd += "Nhân viên: " + nhanVien + "<br/>";
		hd += "Ngày lập: " + ngayNhap + "<br/>";
		hd += "Nhà cung cấp: " + nhaCungCap + "<br/>";
		hd += "<div style='text-align:center;'>==========================================</div><br/>";
		hd += "<div style='text-align:center'>";
		hd += "<table style='max-width:100%'>";
		hd += "<tr>" + "<th>Mã SP</th>" + "<th>Tên SP</th>" + "<th>Số lượng</th>" + "<th>Đơn giá</th>"
				+ "<th>Thành tiền</th>" + "</tr>";
		for (ChiTietHoaDonNhap ctpn : listCTPhieuNhap) {
			hd += "<tr>";
			hd += "<td style='text-align:center;'>" + ctpn.getMaXe() + "</td>";
			for (XeMayDTO sp : listxm) {
				if (sp.getMaXe().equals(ctpn.getMaXe())) {
					hd += "<td style='text-align:left;'>" + sp.getTenXe() + "</td>";
					break;
				}
			}
			hd += "<td style='text-align:center;'>" + ctpn.getSoLuong() + "</td>";
			hd += "<td style='text-align:center;'>" + dcf.format(ctpn.getDonGia()) + "</td>";
			hd += "<td style='text-align:center;'>" + dcf.format(ctpn.getTongTien()) + "</td>";
			hd += "</tr>";

			// ===================================================
			// ===================LƯU CTPN VÀO DB=================
			// ===================================================
			ctpn.setMaPN(maPN);
			ctBll.luuCTPhieuNhap(ctpn);
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
