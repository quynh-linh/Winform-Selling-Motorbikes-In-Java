package BUS;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class BanHangBUS extends AbstractTableModel {

	private Object[][] rows;
	private String[] columns;
	public BanHangBUS() {

	}

	public BanHangBUS(Object[][] data, String[] columnName) {

		this.rows = data;
		this.columns = columnName;
	}

//	public Class getColumnClass(int column) {
//		if (column == 2) {
//			return Icon.class;
//		} else {
//			return getValueAt(0, column).getClass();
//		}
//	}

	public int getRowCount() {

		return this.rows.length;
	}

	public int getColumnCount() {

		return this.columns.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		return this.rows[rowIndex][columnIndex];
	}

	public String getColumnName(int col) {

		return this.columns[col];
	}

}
