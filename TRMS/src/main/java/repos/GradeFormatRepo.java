package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Department;
import beans.GradeFormat;
import utils.JDBCConnection;

public class GradeFormatRepo implements DataRepository<GradeFormat>{

	private String sqlcommand;
	private PreparedStatement ps;
	private ResultSet rs;
	public static Connection conn = JDBCConnection.getConnection();
	
	@Override
	public boolean add(GradeFormat t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GradeFormat getById(Integer id) {
		
		GradeFormat gf = null;
		sqlcommand = "select * from gradeformat where id = ?;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				gf = new GradeFormat(rs.getInt("id"),rs.getString("format"));
				
			}
			
			return gf;
		}
		catch(SQLException e) {
			System.out.println("Problem from getGFByID method");
		}
		
		return null;
	}

	@Override
	public ArrayList<GradeFormat> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(GradeFormat t) {
		// TODO Auto-generated method stub
		
	}

}
