package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Department;
import beans.Position;
import utils.JDBCConnection;

public class PositionRepo implements DataRepository<Position>{

	private String sqlcommand;
	private PreparedStatement ps;
	private ResultSet rs;
	public static Connection conn = JDBCConnection.getConnection();
	
	@Override
	public boolean add(Position t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Position getById(Integer id) {
		
		Position pos = null;
		sqlcommand = "select * from positions where id = ?;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				pos = new Position(rs.getInt("id"),rs.getString("title"));
				
			}
			
			return pos;
		}
		catch(SQLException e) {
			System.out.println("Problem from getPositionByID method");
		}
		
		return null;
	}

	@Override
	public ArrayList<Position> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Position t) {
		// TODO Auto-generated method stub
		
	}

}
