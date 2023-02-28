package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;

public class MySQL {

	public static Connection connect = null;
	ResultSet resultSet = null;
	Statement statement = null;
	static String Host = "localhost";
	static String dbName = "javadoan";
	static String userName = "root";
	static String password = "";
	public MySQL() throws Exception {
		try {
			Class.forName("com.mysql.cj.csdl_javajdbc.Driver");
			if (connect == null) {
				String url = "jdbc:mysql://" + Host + ":3306/" + dbName + "?serverTimezone="
						+ TimeZone.getDefault().getID();
				try {
					connect = DriverManager.getConnection(url, userName, password);
				} catch (SQLException e) {
					throw new Exception("không thể kết nối tới Database" + url + e.getMessage());
				}
			}
		} catch (ClassNotFoundException e) {
			throw new Exception("My SQl not found driveTest");
		}
	}
	// hàm đóng kết nối
	@SuppressWarnings("static-access")
	public void Close() throws Exception {
		if (this.resultSet != null && this.resultSet.isClosed()) {
			this.resultSet.close();
			this.resultSet = null;
		}
		if (this.statement != null && this.statement.isClosed()) {
			this.statement.close();
			this.statement = null;
		}
		if (this.connect != null && this.connect.isClosed()) {
			this.connect.close();
			this.connect = null;
		}
	}
}
