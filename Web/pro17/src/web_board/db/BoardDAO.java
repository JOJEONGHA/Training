package web_board.db;

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

public class BoardDAO {
	private static final String driver = "org.mariadb.jdbc.Driver";
	private static final String url = "jdbc:mariadb://localhost:3306/member";
	private static final String user = "root";
	private static final String pwd = "1233";
	
	private Connection con;
	private PreparedStatement state;
	private DataSource data;
	
	public BoardDAO() {
		try {
            Class.forName(driver);
            con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/board",
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

	public List<ArticleVO> selectAllArticles() {
		List articlesList = new ArrayList();
		try {
			String query = "SELECT level,articleNO,parentNO,title,content,id,writeDate" 
			             + " from t_board"
					     + " START WITH  parentNO=0" + " CONNECT BY PRIOR articleNO=parentNO"
					     + " ORDER SIBLINGS BY articleNO DESC";
			System.out.println(query);
			state = con.prepareStatement(query);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			rs.close();
			state.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
}
