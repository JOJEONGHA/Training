package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private static final String driver = "org.mariadb.jdbc.Driver";
	
	private Connection con;
	private PreparedStatement state;
	private DataSource data;
	
	public MemberDAO() {
		try {
            Class.forName(driver);
            con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/member",
                    "root",
                    "1233");
      
            if( con != null ) {
            	System.out.println("DB 접속 성공");
            }
            
        } catch (ClassNotFoundException e) { 
        	System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
        	System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
	}

	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {

			String query = "select * from sample ";
			System.out.println("prepareStatememt: " + query);
			state = con.prepareStatement(query);
			ResultSet result = state.executeQuery();
			while (result.next()) {
              int num = result.getInt("num");
              String title = result.getString("title");
              MemberVO vo = new MemberVO(num,title);
              list.add(vo);
			}
            result.close();
            state.close();
            con.close();
        } catch (Exception e) {
        }
        return list;
    }
}
