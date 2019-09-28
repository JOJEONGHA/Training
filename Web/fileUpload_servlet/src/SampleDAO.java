import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class SampleDAO {
	private static final String driver = "org.mariadb.jdbc.Driver";

	private Connection con;
	private PreparedStatement state;
	private DataSource data;

	public SampleDAO() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sample", "root", "1233");

			if (con != null) {
				System.out.println("db stroge not exist");
			}
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<SampleVO> selectAllSamples() {
		List<SampleVO> list = new ArrayList<>();
		try {
			String query = "select num,title,imageFileName from sample ";
			state = con.prepareStatement(query);
			ResultSet result = state.executeQuery();
			while(result.next()){
				int num = result.getInt("num");
				String sample = result.getString("title");
				String imageFileName = result.getString("imageFileName");	
				SampleVO vo = new SampleVO(num,sample,imageFileName);
				list.add(vo);
			}
			result.close();
			state.close();
		} catch (Exception e) {
			//TODO: handle exception
		}

		return list;
	}

	public void insertNewSample(SampleVO vo) {
		int num = vo.getNum();
		String title = vo.getSample();
		String imageFileName = vo.getImageFileName();
		String query = "insert into sample(num,title,imageFileName) "
						+ "values(?,?,?)";
		try {
			state = con.prepareStatement(query);
			state.setInt(1, num);
			state.setString(2, title);
			state.setString(3, imageFileName);
			state.executeUpdate();
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int extraction_num() {
		String query = "SELECT max(num) FROM sample";
		int number = 1;
		try {
			state = con.prepareStatement(query);
			ResultSet result = state.executeQuery();
			result.next();
			number = result.getInt("num");
			result.close();
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return number;
	}
}
