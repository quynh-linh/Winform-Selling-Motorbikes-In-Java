package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class ConnectUnit {
	public MySQLConnection connect;

	public static void main(String[] args) {

		new ConnectUnit();
	}

	// hàm khởi tạo kết nối mặc định
	public ConnectUnit() {
		connect = new MySQLConnection("localhost", "root", "", "csdl_java");
	}

	// hàm khởi tạo cơ bản
	public ConnectUnit(String Host, String Username, String Password, String Database) {
		connect = new MySQLConnection(Host, Username, Password, Database);
	}

	// Hàm hỗ trợ Select CSDL
	/**
	 * Select * From Table Where Condition Order by OderBy
	 * 
	 * @throws Exception
	 */
	public ResultSet Select(String TableName, String Condition, String OrderBy) throws Exception {
		// khai báo biến StringBuilder để tạo chuỗi Select
		StringBuilder query = new StringBuilder("SELECT * FROM " + TableName);
		// Đưa câu lệnh điều kiện vaò câu query
		this.AddCondition(query, Condition);
		// Đưa câu lệnh Order vào query
		this.AddOrderBy(query, OrderBy);
		// chèn ký tự ; vào cuồi các câu lệnh
		query.append(";");
		// thực thi câu lệnh query và trả kết quả
		return this.connect.excuteQuery(query.toString());
	}
	public ResultSet SelectSum(String TableName, String Condition, String OrderBy) throws Exception {
		// khai báo biến StringBuilder để tạo chuỗi Select
		StringBuilder query = new StringBuilder("SELECT SUM("+Condition+")FROM " + TableName);
		
		// Đưa câu lệnh điều kiện vaò câu query
		this.AddCondition(query, Condition);
		// Đưa câu lệnh Order vào query
		this.AddOrderBy(query, OrderBy);
		// chèn ký tự ; vào cuồi các câu lệnh
		query.append(";");
		System.out.println(query);
		// thực thi câu lệnh query và trả kết quả
		return this.connect.excuteQuery(query.toString());
	}
	public ResultSet SelectSumDoanhThu(String TableName,String OrderBy ,String Condition) throws Exception {
		// khai báo biến StringBuilder để tạo chuỗi Select
		StringBuilder query = new StringBuilder("SELECT SUM("+OrderBy+")FROM " + TableName);
		// Đưa câu lệnh điều kiện vaò câu query
		this.AddCondition(query, Condition);
		query.append(";");
		System.out.println(query);
		// thực thi câu lệnh query và trả kết quả
		return this.connect.excuteQuery(query.toString());
	}
	public ResultSet SelectCount(String TableName, String Condition, String OrderBy) throws Exception {
		// khai báo biến StringBuilder để tạo chuỗi Select
		StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM " + TableName);

		// Đưa câu lệnh điều kiện vaò câu query
		this.AddCondition(query, Condition);
		// Đưa câu lệnh Order vào query
		this.AddOrderBy(query, OrderBy);
		// chèn ký tự ; vào cuồi các câu lệnh
		query.append(";");
		System.out.println(query);
		// thực thi câu lệnh query và trả kết quả
		return this.connect.excuteQuery(query.toString());
	}

	// Hàm over load Select giảm OrderBy parameter
	/**
	 * Select * From Table Where Condition
	 * 
	 * @throws Exception
	 */
	public ResultSet SelectSum(String TableName ,String Condition) throws Exception {
		return this.SelectSum(TableName, Condition, null);
	}
	public ResultSet SelectSumDT(String TableName ,String od ,String Condition) throws Exception {
		return this.SelectSumDoanhThu(TableName, od, Condition);
	}
	//
	public ResultSet SelectCount(String TableName) throws Exception {
		return this.SelectCount(TableName,null,null);
	}
	//
	public ResultSet SelectCount(String TableName,String Condition) throws Exception {
		return this.SelectCount(TableName,Condition,null);
	}
	public ResultSet Select(String TableName, String Condition) throws Exception {
		return this.Select(TableName, Condition, null);
	}
	public ResultSet SelectOrderBY(String TableName, String OrderBy) throws Exception {
		return this.Select(TableName, null, OrderBy);
	}
	// Hàm over load Select giảm Condition parameter
	/**
	 * Select * From Table
	 * 
	 * @throws Exception
	 */
	public ResultSet Select(String TableName) throws Exception {
		return this.Select(TableName, null, null);
	}

	// Hàm thêm điều kiện vào query
	private void AddCondition(StringBuilder query, String Condition) {
		// kiểm tra nếu condition khác null
		if (Condition != null) {
			query.append(" WHERE " + Condition);
		}
	}
	// Hàm thêm OrderBy vào query
	private void AddOrderBy(StringBuilder query, String OrderBy) {
		// Kiểm tra OrderBy Khác null
		if (OrderBy != null) {
			query.append(" ORDER BY " + OrderBy);
		}
	}

	// Hàm hỗ trợ Insert xuống SQL
	public boolean Insert(String TableName, HashMap<String, Object> ColumnValues) throws Exception {
		// Khai báo biến StringBuilder để tạo chuỗi Select
		StringBuilder query = new StringBuilder("Insert Into " + TableName);
		// khai báo biến StringBuilder để tạo chuỗi Column Values
		StringBuilder valueInsert = new StringBuilder();

		query.append("(");
		// Duyệt và đưa thông tin tên cột và giá tri values vào
		for (String key : ColumnValues.keySet()) {
			query.append(key + ",");
			valueInsert.append("'" + ColumnValues.get(key).toString() + "',");
		}
		// cắt bỏ dấu , dư thừa
		query = query.delete(query.length() - 1, query.length());
		valueInsert = valueInsert.delete(valueInsert.length() - 1, valueInsert.length());

		// đưa giá trị của cột vào câu query
		query.append(") Values(" + valueInsert.toString() + ")");
		// chèn ký tự ; vào cuối dòng lệnh để cách câu
		query.append(";");
		System.out.println(query);
		// Thực thi câu query và trả kết quả ra ngoài
		return this.connect.executeUpdate(query.toString()) > 0;
	}

	// hàm hỗ trợ update CSDL
	public boolean Update(String TableName, HashMap<String, Object> ColumnValues, String Condition) throws Exception {
		// khai báo biến StringBuilder để tạo chuỗi CSDL
		StringBuilder query = new StringBuilder("Update " + TableName + " Set ");

		// Duyệt và đưa thông tin tên cột, giá trị
		for (String key : ColumnValues.keySet()) {
			query.append(key + " = '" + ColumnValues.get(key).toString() + "',");
		}
		// Cat981 bớt ký tự , cuối câu
		query = query.delete(query.length() - 1, query.length());
		// đưa câu lệnh điều kiện vào trong query
		this.AddCondition(query, Condition);
		// chèn ký tự ; vào cuồi dòng lệnh để cách các câu lệnh
		query.append(";");
		System.out.println(query);
		// thực thi và trả ra kết quả
		return this.connect.executeUpdate(query.toString()) > 0;
	}
	// Hàm delete hỗ trợ torng CDSL
	public boolean Delete(String TableName, String Condition) throws Exception {
		// khai báo biến StringBuilder để tạo chuỗi Select
		StringBuilder query = new StringBuilder("Delete From " + TableName);
		// Đưa câu lệnh điều kiện vào query
		this.AddCondition(query, Condition);
		// chèn ký tự ; vào cuối dòng lệnh để ngăn cách các câu
		query.append(";");
		System.out.println(query);
		// thực thi và trả về giá trị
		return this.connect.executeUpdate(query.toString()) > 0;
	}

	// hàm đếm số cột trong CSDL
	public static int getColumnCount(ResultSet result) throws SQLException {
		return result.getMetaData().getColumnCount();
	}

	// hàm lấy tên cột trong result select từ CSDL
	public static String[] getColumnName(ResultSet result) throws SQLException {
		// lấy resultsetMetaDate từ Result
		ResultSetMetaData rsMetaData = (ResultSetMetaData) result.getMetaData();
		// lấy số lượng cột trong Result
		int ColumnCount = rsMetaData.getColumnCount();
		// khai báo danh sách các cột
		String[] list = new String[ColumnCount];
		for (int i = 0; i < ColumnCount; i++) {
			list[i] = rsMetaData.getColumnName(i);
		}
		return list;
	}
	// hàm đóng kết nối
	public void Close() throws Exception {
		this.connect.Close();
	}
}
