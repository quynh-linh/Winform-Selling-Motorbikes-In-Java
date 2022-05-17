package GUI;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.ChiTietPhieuNhapBUS;
import BUS.xeMayBUS;
import DTO.ChiTietHoaDonNhap;
import DTO.ChiTietHoaDonXuat;
import DTO.XeMayDTO;

import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;

public class InPhieuXuat extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ArrayList<ChiTietHoaDonXuat> listChiTiet = null;
	private JEditorPane editorPane;
	String nhanVien;
	String ngayBan;
	String khachHang;
	double tongTien = 0;

	/**
	 * Launch the application.
	 */
	public InPhieuXuat(ArrayList<ChiTietHoaDonXuat> listCTPhieuNhap, String nhanVien, String ngayXuat,
			String khachHang, double tongTien) throws Exception {

		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayBan = ngayXuat;
		this.listChiTiet = listCTPhieuNhap;
		this.tongTien = tongTien;
		this.init();
		this.inPHieu();
		this.editorPane.setEditable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public void init() {
		setTitle("In phiếu xuất");
		setBounds(100, 100, 639, 429);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JButton btnInPhieu = new JButton("In Phiếu Nhập");
			btnInPhieu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						editorPane.print();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnInPhieu.setBounds(263, 355, 99, 23);
			contentPanel.add(btnInPhieu);
			btnInPhieu.setActionCommand("OK");
			getRootPane().setDefaultButton(btnInPhieu);
		}

		editorPane = new JEditorPane();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(editorPane);
		scrollPane.setBounds(10, 11, 603, 333);
		contentPanel.add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);
		}
	}

	public void inPHieu() throws Exception {
		xeMayBUS xmBll = new xeMayBUS();
		ArrayList<XeMayDTO> listxm = xmBll.getList_SP();
		editorPane.setContentType("text/html");
		DecimalFormat dcf = new DecimalFormat("###,### VND");
		ChiTietPhieuNhapBUS ctBll = new ChiTietPhieuNhapBUS();
		String hd = "<style> " + "table {" + "border: 1px solid;" + "border-bottom: none" + "}" + "tr {"
				+ "border-bottom: 1px solid;" + "}" + "td {" + "padding: 8px;" + "} " + "th {" + "font-size:16pt" + "}"
				+ "</style>";
		hd += "<h1 style='text-align:center;'>CHI TIẾT PHIẾU XUẤT</h1>";
		hd += "Nhân viên: " + nhanVien + "<br/>";
		hd += "Ngày bán " + ngayBan + "<br/>";
		hd += "Khách hàng: " + khachHang + "<br/>";
		hd += "<div style='text-align:center;'>==========================================</div><br/>";
		hd += "<div style='text-align:center'>";
		hd += "<table style='max-width:100%'>";
		hd += "<tr>" + "<th>Mã SP</th>" + "<th>Tên SP</th>" + "<th>Số lượng</th>" + "<th>Đơn giá</th>"
				+ "<th>Thành tiền</th>" + "</tr>";
		for (ChiTietHoaDonXuat ctpn : listChiTiet) {
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
			hd += "<td style='text-align:center;'>" + dcf.format(ctpn.gettongTien()) + "</td>";
			hd += "</tr>";

			// ===================================================
			// ===================LƯU CTPN VÀO DB=================
			// ===================================================
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
}
