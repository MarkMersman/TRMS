package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Department;
import utils.JDBCConnection;

public class DepartmentRepo implements DataRepository<Department>{

	private String sqlcommand;
	private PreparedStatement ps;
	private ResultSet rs;
	public static Connection conn = JDBCConnection.getConnection();
	
	
	@Override
	public boolean add(Department t) {
		sqlcommand = "insert into dept values (default, ?,?);";
		
		try {
			ps = conn.prepareStatement(sqlcommand);
			ps.setString(1, t.getName());
			ps.setInt(2, t.getDeptHead());
			
			
			
			boolean success = ps.execute();
			
			if (success) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException ex) {
			System.out.println("add department issue");
		}
		
		return false;
	}

	@Override
	public Department getById(Integer id) {
		
		Department dept = null;
		sqlcommand = "select * from dept where id = ?;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				dept = new Department(rs.getInt("id"),rs.getString("name"),rs.getInt("dept_head"));
				
			}
			
			return dept;
		}
		catch(SQLException e) {
			System.out.println("Problem from getUserByID method");
		}
		
		return null;
	}

	@Override
	public ArrayList<Department> getAll() {
		
		ArrayList<Department> deptList = new ArrayList<Department>();
		Department dept = null;
		sqlcommand = "select * from dept order by id;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			
			rs = ps.executeQuery();
		
			while(rs.next()) {
				dept = new Department(rs.getInt("id"),rs.getString("name"),rs.getInt("dept_head"));
				
				deptList.add(dept);
			}
			
			return deptList;
		}
		catch(SQLException e) {
			System.out.println("Problem from getall depts method");
		}
		
		return null;
	}

	@Override
	public void update(Department t) {
		// TODO Auto-generated method stub
		
	}

}
