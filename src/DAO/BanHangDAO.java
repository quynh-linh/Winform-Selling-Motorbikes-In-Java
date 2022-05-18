package DAO;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import DTO.BanHangDTO;


/**
 *
 * @author 1bestcsharp.blogspot.com
 */
public class BanHangDAO {
    
	ConnectUnit connect;
	FileOutputStream fs=null;
	 /**
     * Lấy thông tin từ Database
     */
    public ArrayList<BanHangDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new ConnectUnit();
        ResultSet result = this.connect.Select("tbl_banhang", condition, orderBy);
        ArrayList<BanHangDTO> sanphams = new ArrayList<>(); 
        while ( result.next() ) {
        	BanHangDTO bh = new BanHangDTO();
        	
        	result.getString("maXe");
        	result.getString("tenXe");
        	result.getBytes("image");
        	result.getInt("soLuong");
            sanphams.add(bh);
        }
        connect.Close();
        return sanphams;
    }
    public ArrayList<BanHangDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    public ArrayList<BanHangDTO> docDB() throws Exception {
        return docDB(null);
    }
}
