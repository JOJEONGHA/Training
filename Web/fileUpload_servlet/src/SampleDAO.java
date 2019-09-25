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
	private static final String url = "jdbc:mariadb://localhost:3306/member";
	private static final String user = "root";
	private static final String pwd = "1233";

	private Connection con;
	private PreparedStatement state;
	private DataSource data;

	public SampleDAO() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/board", "root", "1233");

			if (con != null) {
				System.out.println("db stroge not exist");
			}

		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<SampleVO> listMembers() {
		List<SampleVO> list = new ArrayList<SampleVO>();
		try {
			String query = "select * from t_member ";
			System.out.println("prepareStatememt: " + query);
			state = con.prepareStatement(query);
			ResultSet result = state.executeQuery();
			while (result.next()) {
				int num = result.getInt("num");
				String sample = result.getString("sample");
				SampleVO vo = new SampleVO(num, sample);
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
