package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class ConnectUnit {
	public static MySQLConnection connect;

	public static void main(String[] args) {

		new ConnectUnit();
	}

	// hÃ m khá»Ÿi táº¡o káº¿t ná»‘i máº·c Ä‘á»‹nh
	public ConnectUnit() {
		connect = new MySQLConnection("localhost", "root", "", "csdl_java");
	}

	// hÃ m khá»Ÿi táº¡o cÆ¡ báº£n
	public ConnectUnit(String Host, String Username, String Password, String Database) {
		connect = new MySQLConnection(Host, Username, Password, Database);
	}

	// HÃ m há»— trá»£ Select CSDL
	/**
	 * Select * From Table Where Condition Order by OderBy
	 * 
	 * @throws Exception
	 */
	public ResultSet Select(String TableName, String Condition, String OrderBy) throws Exception {
		// khai bÃ¡o biáº¿n StringBuilder Ä‘á»ƒ táº¡o chuá»—i Select
		StringBuilder query = new StringBuilder("SELECT * FROM " + TableName);
		// Ä�Æ°a cÃ¢u lá»‡nh Ä‘iá»�u kiá»‡n vaÃ² cÃ¢u query
		this.AddCondition(query, Condition);
		// Ä�Æ°a cÃ¢u lá»‡nh Order vÃ o query
		this.AddOrderBy(query, OrderBy);
		// chÃ¨n kÃ½ tá»± ; vÃ o cuá»“i cÃ¡c cÃ¢u lá»‡nh
		query.append(";");
		// thá»±c thi cÃ¢u lá»‡nh query vÃ  tráº£ káº¿t quáº£
		return this.connect.excuteQuery(query.toString());
	}
	public ResultSet SelectSum(String TableName, String Condition, String OrderBy) throws Exception {
		// khai bÃ¡o biáº¿n StringBuilder Ä‘á»ƒ táº¡o chuá»—i Select
		StringBuilder query = new StringBuilder("SELECT SUM("+Condition+")FROM " + TableName);
		
		// Ä�Æ°a cÃ¢u lá»‡nh Ä‘iá»�u kiá»‡n vaÃ² cÃ¢u query
		this.AddCondition(query, Condition);
		// Ä�Æ°a cÃ¢u lá»‡nh Order vÃ o query
		this.AddOrderBy(query, OrderBy);
		// chÃ¨n kÃ½ tá»± ; vÃ o cuá»“i cÃ¡c cÃ¢u lá»‡nh
		query.append(";");
		System.out.println(query);
		// thá»±c thi cÃ¢u lá»‡nh query vÃ  tráº£ káº¿t quáº£
		return this.connect.excuteQuery(query.toString());
	}
	public ResultSet SelectSumDoanhThu(String TableName,String OrderBy ,String Condition) throws Exception {
		// khai bÃ¡o biáº¿n StringBuilder Ä‘á»ƒ táº¡o chuá»—i Select
		StringBuilder query = new StringBuilder("SELECT SUM("+OrderBy+")FROM " + TableName);
		// Ä�Æ°a cÃ¢u lá»‡nh Ä‘iá»�u kiá»‡n vaÃ² cÃ¢u query
		this.AddCondition(query, Condition);
		query.append(";");
		System.out.println(query);
		// thá»±c thi cÃ¢u lá»‡nh query vÃ  tráº£ káº¿t quáº£
		return this.connect.excuteQuery(query.toString());
	}
	public ResultSet SelectCount(String TableName, String Condition, String OrderBy) throws Exception {
		// khai bÃ¡o biáº¿n StringBuilder Ä‘á»ƒ táº¡o chuá»—i Select
		StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM " + TableName);

		// Ä�Æ°a cÃ¢u lá»‡nh Ä‘iá»�u kiá»‡n vaÃ² cÃ¢u query
		this.AddCondition(query, Condition);
		// Ä�Æ°a cÃ¢u lá»‡nh Order vÃ o query
		this.AddOrderBy(query, OrderBy);
		// chÃ¨n kÃ½ tá»± ; vÃ o cuá»“i cÃ¡c cÃ¢u lá»‡nh
		query.append(";");
		System.out.println(query);
		// thá»±c thi cÃ¢u lá»‡nh query vÃ  tráº£ káº¿t quáº£
		return this.connect.excuteQuery(query.toString());
	}

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
	// HÃ m over load Select giáº£m Condition parameter
	/**
	 * Select * From Table
	 * 
	 * @throws Exception
	 */
	public ResultSet Select(String TableName) throws Exception {
		return this.Select(TableName, null, null);
	}

	// HÃ m thÃªm Ä‘iá»�u kiá»‡n vÃ o query
	private void AddCondition(StringBuilder query, String Condition) {
		// kiá»ƒm tra náº¿u condition khÃ¡c null
		if (Condition != null) {
			query.append(" WHERE " + Condition);
		}
	}
	// HÃ m thÃªm OrderBy vÃ o query
	private void AddOrderBy(StringBuilder query, String OrderBy) {
		// Kiá»ƒm tra OrderBy KhÃ¡c null
		if (OrderBy != null) {
			query.append(" ORDER BY " + OrderBy);
		}
	}

	// HÃ m há»— trá»£ Insert xuá»‘ng SQL
	public boolean Insert(String TableName, HashMap<String, Object> ColumnValues) throws Exception {
		// Khai bÃ¡o biáº¿n StringBuilder Ä‘á»ƒ táº¡o chuá»—i Select
		StringBuilder query = new StringBuilder("Insert Into " + TableName);
		// khai bÃ¡o biáº¿n StringBuilder Ä‘á»ƒ táº¡o chuá»—i Column Values
		StringBuilder valueInsert = new StringBuilder();
		query.append("(");
		// Duyá»‡t vÃ  Ä‘Æ°a thÃ´ng tin tÃªn cá»™t vÃ  giÃ¡ tri values vÃ o
		for (String key : ColumnValues.keySet()) {
			query.append(key + ",");
			valueInsert.append("'" + ColumnValues.get(key).toString() + "',");
		}
		// cáº¯t bá»� dáº¥u , dÆ° thá»«a
		query = query.delete(query.length() - 1, query.length());
		valueInsert = valueInsert.delete(valueInsert.length() - 1, valueInsert.length());

		// Ä‘Æ°a giÃ¡ trá»‹ cá»§a cá»™t vÃ o cÃ¢u query
		query.append(") Values(" + valueInsert.toString() + ")");
		// chÃ¨n kÃ½ tá»± ; vÃ o cuá»‘i dÃ²ng lá»‡nh Ä‘á»ƒ cÃ¡ch cÃ¢u
		query.append(";");
		System.out.println(query);
		// Thá»±c thi cÃ¢u query vÃ  tráº£ káº¿t quáº£ ra ngoÃ i
		return this.connect.executeUpdate(query.toString()) > 0;
	}

	// hÃ m há»— trá»£ update CSDL
	public boolean Update(String TableName, HashMap<String, Object> ColumnValues, String Condition) throws Exception {
		// khai bÃ¡o biáº¿n StringBuilder Ä‘á»ƒ táº¡o chuá»—i CSDL
		StringBuilder query = new StringBuilder("Update " + TableName + " Set ");

		// Duyá»‡t vÃ  Ä‘Æ°a thÃ´ng tin tÃªn cá»™t, giÃ¡ trá»‹
		for (String key : ColumnValues.keySet()) {
			query.append(key + " = '" + ColumnValues.get(key).toString() + "',");
		}
		// Cat981 bá»›t kÃ½ tá»± , cuá»‘i cÃ¢u
		query = query.delete(query.length() - 1, query.length());
		// Ä‘Æ°a cÃ¢u lá»‡nh Ä‘iá»�u kiá»‡n vÃ o trong query
		this.AddCondition(query, Condition);
		// chÃ¨n kÃ½ tá»± ; vÃ o cuá»“i dÃ²ng lá»‡nh Ä‘á»ƒ cÃ¡ch cÃ¡c cÃ¢u lá»‡nh
		query.append(";");
		System.out.println(query);
		// thá»±c thi vÃ  tráº£ ra káº¿t quáº£
		return this.connect.executeUpdate(query.toString()) > 0;
	}
	// HÃ m delete há»— trá»£ torng CDSL
	public boolean Delete(String TableName, String Condition) throws Exception {
		// khai bÃ¡o biáº¿n StringBuilder Ä‘á»ƒ táº¡o chuá»—i Select
		StringBuilder query = new StringBuilder("Delete From " + TableName);
		// Ä�Æ°a cÃ¢u lá»‡nh Ä‘iá»�u kiá»‡n vÃ o query
		this.AddCondition(query, Condition);
		// chÃ¨n kÃ½ tá»± ; vÃ o cuá»‘i dÃ²ng lá»‡nh Ä‘á»ƒ ngÄƒn cÃ¡ch cÃ¡c cÃ¢u
		query.append(";");
		System.out.println(query);
		// thá»±c thi vÃ  tráº£ vá»� giÃ¡ trá»‹
		return this.connect.executeUpdate(query.toString()) > 0;
	}

	// hÃ m Ä‘áº¿m sá»‘ cá»™t trong CSDL
	public static int getColumnCount(ResultSet result) throws SQLException {
		return result.getMetaData().getColumnCount();
	}

	// hÃ m Ä‘Ã³ng káº¿t ná»‘i
	public void Close() throws Exception {
		this.connect.Close();
	}

}
