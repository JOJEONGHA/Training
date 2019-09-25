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
	private static final String url = "jdbc:mariadb://localhost:3306/member";
	private static final String user = "root";
	private static final String pwd = "1233";
	
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
//		try {
//			Context ctx = new InitialContext();
////			Context envContext = (Context) ctx.lookup("java:/comp/env/");
////			data = (DataSource) envContext.lookup("jdbc/mariadb");
//			data = (DataSource) ctx.lookup("java:/comp/env/jdbc/maridb");
//			System.out.println("check!!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
//			connDB();
//			con=data.getConnection();	
			String query = "select * from t_member ";
			System.out.println("prepareStatememt: " + query);
			state = con.prepareStatement(query);
			ResultSet result = state.executeQuery();
			while (result.next()) {
              String id = result.getString("id");
              String pwd = result.getString("pwd");
              String name = result.getString("name");
              String email = result.getString("email");
              Date joinDate = result.getDate("joinDate");
              MemberVO vo = new MemberVO(id,pwd,name,email,joinDate);
              list.add(vo);
			}
            result.close();
            state.close();
            con.close();
        } catch (Exception e) {
        }
        return list;
    }
	
	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("MariaDB 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
