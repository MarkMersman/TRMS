package repos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Department;
import beans.Event;
import utils.JDBCConnection;

public class EventRepo implements DataRepository<Event>{

	private String sqlcommand;
	private PreparedStatement ps;
	private ResultSet rs;
	public static Connection conn = JDBCConnection.getConnection();
	
	@Override
	public boolean add(Event t) {
		if (t.getAttachment() != null) {
			sqlcommand = "insert into events values (default, ?,?,?,?,?,?,?,?);";
		}
		else {
			sqlcommand = "insert into events values (default, ?,?,?,?,?,?,?,?,?);";
		}
		
		try {
			ps = conn.prepareStatement(sqlcommand);
			ps.setString(1, t.getEventType());
			ps.setString(2, t.getDescription());
			ps.setString(3, t.getLocation());
			ps.setTime(4, t.getEventTime());
			ps.setDate(5, t.getStartDate());
			ps.setInt(6, t.getGradeFormat());
			ps.setDouble(7, t.getCost());
			ps.setDouble(8, t.getTimeMissed());
			ps.setString(9, t.getpGrade());
			if(t.getAttachment() != null) {
							
				try {
					ps.setBytes(8, Files.readAllBytes(t.getAttachment().toPath()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("event repo add method converting file to bytes issue");
				}
			}
			
			
			
			boolean success = ps.execute();
			System.out.println(success);
			if (success) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException ex) {
			System.out.println("add event issue");
		}
		
		return false;
	}

	@Override
	public Event getById(Integer id) {
		//tried to get the bytea data and turn it into a file
		//Path p = Paths.get("D:\\Users\\markm\\Revature_Training\\TRMS\\TRMS\\TRMS\\src\\main\\resources\\file.txt");
		//File fil = new File(p);
		Event ev = null;
		sqlcommand = "select * from events where id = ?;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				ev = new Event(rs.getInt("id"),rs.getString("event_type"),rs.getString("description"),rs.getString("event_location"),rs.getTime("event_time"),
								rs.getDate("start_date"),rs.getInt("grade_format"),rs.getDouble("event_cost"),rs.getDouble("missedwork"),rs.getString("mingrade"));  //,Files.write(null, rs.getBytes("event_attachment"))
				
			}
			
			return ev;
		}
		catch(SQLException e) {
			System.out.println("Problem from getEventByID method");
		}
		
		return null;
	}

	public int getLast() {
		int evID = 0;
		sqlcommand = "select id from events order by id desc limit 1;";
		try {
			ps = conn.prepareStatement(sqlcommand);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				evID = rs.getInt("id");
			}
			return evID;
		} catch (SQLException e) {
			System.out.println("Problem from getLastEvent method");
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public ArrayList<Event> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Event t) {
		// TODO Auto-generated method stub
		
	}

}
