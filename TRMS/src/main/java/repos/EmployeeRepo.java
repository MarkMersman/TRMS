package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Department;
import beans.Employee;
import beans.Position;
import utils.JDBCConnection;

public class EmployeeRepo implements DataRepository<Employee> {

	private String sqlcommand;
	private PreparedStatement ps;
	private ResultSet rs;
	public static Connection conn = JDBCConnection.getConnection();
	
	@Override
	public boolean add(Employee t) {
		
		sqlcommand = "insert into Employee values (default, ?,?,"
				+ "?,?,?,?,?,?);";
		
		try {
			ps = conn.prepareStatement(sqlcommand);
			ps.setString(1, t.getUserName());
			ps.setString(2, t.getPass());
			ps.setString(3,t.getLastName());
			ps.setString(4,t.getFirstName());
			ps.setDate(5,t.getStartDate());
			ps.setInt(6, t.getPosition());
			ps.setInt(7, t.getSupervisor());
			ps.setInt(8, t.getDepartment());
			
			
			
			boolean success = ps.execute();
			
			if (success) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException ex) {
			System.out.println("add emp issue");
		}
		
		return false;
	}

	@Override
	public Employee getById(Integer id) {
		
		Employee emp = null;
		sqlcommand = "select * from Employee where id = ?;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				emp = new Employee(rs.getInt("id"),rs.getString("username"),rs.getString("pass"),
						rs.getString("last_name"),rs.getString("first_name"),rs.getDate("start_date"),
						rs.getInt("position_id"),rs.getInt("supervisor_id"),rs.getInt("dept_id"));
				
			}
			
			return emp;
		}
		catch(SQLException e) {
			System.out.println("Problem from getUserByID method");
		}
		
		return null;
	}
	
	public Employee getByUserName(String un) {
		
		Employee emp = null;
		sqlcommand = "select * from Employee where username = ?;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setString(1, un);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				emp = new Employee(rs.getInt("id"),rs.getString("username"),rs.getString("pass"),
						rs.getString("last_name"),rs.getString("first_name"),rs.getDate("start_date"),
						rs.getInt("position_id"),rs.getInt("supervisor_id"),rs.getInt("dept_id"));
				
			}
			
			return emp;
		}
		catch(SQLException e) {
			System.out.println("Problem from getUserByID method");
		}
		
		return null;
	}
	
	
	@Override
	public ArrayList<Employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Employee t) {
		// TODO Auto-generated method stub
		
	}

}
