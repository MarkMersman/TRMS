package repos;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import beans.Event;
import beans.Reimbursement;
import beans.Request;
import utils.JDBCConnection;

public class ReimbursementRepo implements DataRepository<Reimbursement>{

	private String sqlcommand;
	private PreparedStatement ps;
	private ResultSet rs;
	public static Connection conn = JDBCConnection.getConnection();
	RequestRepo reqr = new RequestRepo();
	EventRepo evr = new EventRepo();
	
	public boolean add(int requestId) {
		
		Request req = reqr.getById(requestId);
		//need to do logic to get amount university course = 80% of cost seminars 60
		//cert prep class 75%, certification 100, technical training 90, other 30
		int evId = req.getEvent();
		Event ev = evr.getById(evId);
		String evType = ev.getEventType();
		double amt=0;
		
		switch(evType) {
			case "Seminar": amt = (ev.getCost() *.6); break;
			case "University Course": amt = (ev.getCost() * .8); break;
			case "Certification": amt = ev.getCost(); break;
			case "Certification Preperation Class": amt = (ev.getCost()* .75); break;
			case "Technical Training": amt = (ev.getCost()* .9); break;
			default: amt = (ev.getCost()*.3); break;
		}
		
		//need to get all the reimbursements for the year so far and add the amounts
		//to see if the amount needs adjusted.
		double totalForYear = getForYear(req.getEmpId());
		double availableAmount = 1000.00 - totalForYear;
		if(availableAmount > 0) {
			if(amt > availableAmount) {
				//give as much as possible without going over 1000
				amt = availableAmount;
			}
			else {
				//amount is acceptable do nothing
			}
		}
		else {
			//exceeded available amount
			amt = 0;
		}
		
		sqlcommand = "insert into reimbursement values (default, ?,?,"
				+ "?,?,?,?);";
		
				
		try {
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, requestId);
			ps.setDouble(2, amt);
			ps.setBoolean(3, false);			
			ps.setString(4, "N/A");
			ps.setString(5, "Pending Approval");
			ps.setString(6, "N/A");
			
			
			boolean success = ps.execute();
			
			if (!success) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException ex) {
			System.out.println("add reimbursement1 issue");
		}
		
		return false;
	}
	
	@Override
	public boolean add(Reimbursement t) {
 	
	sqlcommand = "insert into reimbursement values (default, ?,?,"
			+ "?,?,?,?);";
	
		
	try {
		ps = conn.prepareStatement(sqlcommand);
		ps.setInt(1, t.getRequestId());
		ps.setDouble(2, t.getAmount());
		ps.setBoolean(3, t.isAmountExceeded());			
		ps.setString(4,t.getExceededReason());
		ps.setString(5, t.getStatus());
		ps.setString(6, t.getEventGrade());
		
		
		boolean success = ps.execute();
		
		if (!success) {
			return true;
		}
		else {
			return false;
		}
	}
	catch(SQLException ex) {
		System.out.println("add reimbursement issue");
	}
	
	return false;
}

	@Override
	public Reimbursement getById(Integer id) {

		Reimbursement re = null;
		
		sqlcommand = "select * from reimbursement where id = ?;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				re = new Reimbursement(rs.getInt("id"), rs.getInt("request_id"),rs.getDouble("amount"),rs.getBoolean("amount_exceeded"),
										rs.getString("exceeded_reason"), rs.getString("status"),rs.getString("event_grade"));
				
			}
			
			return re;
		}
		catch(SQLException e) {
			System.out.println("Problem from get req by id method");
		}
		
		return null;
	}

	public Reimbursement getByReq(int reqId) {
		
		Reimbursement re = null;
		
		sqlcommand = "select * from reimbursement where request_id = ?;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, reqId);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				re = new Reimbursement(rs.getInt("id"), rs.getInt("request_id"),rs.getDouble("amount"),rs.getBoolean("amount_exceeded"),
										rs.getString("exceeded_reason"), rs.getString("status"),rs.getString("event_grade"));
				
			}
			
			return re;
		}
		catch(SQLException e) {
			System.out.println("Problem from get req by id method");
		}
		
		return null;
	}
	
	@Override
	public ArrayList<Reimbursement> getAll() {
		
		ArrayList<Reimbursement> reList = new ArrayList<Reimbursement>();
		Reimbursement re = null;
		sqlcommand = "select * from reimbursement order by id;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			
			rs = ps.executeQuery();
		
			while(rs.next()) {
				
				re = new Reimbursement(rs.getInt("id"), rs.getInt("request_id"),rs.getDouble("amount"),rs.getBoolean("amount_exceeded"),
						rs.getString("exceeded_reason"), rs.getString("status"),rs.getString("event_grade"));
				
				reList.add(re);
			}
			
			return reList;
		}
		catch(SQLException e) {
			System.out.println("Problem from getall reimbursements method");
		}
		
		return null;
	}

	
	public List<Reimbursement> getPending(int id){
		
		List<Reimbursement> reList = new ArrayList<Reimbursement>();
		Reimbursement re = null;
		sqlcommand = "select * from reimbursement r join request re on r.request_id = re.id \r\n"
				+ "where re.emp_id = ? and (r.status like 'pending%' or r.status like 'Pending%' or r.status = 'Awarded');";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				
				re = new Reimbursement(rs.getInt("id"), rs.getInt("request_id"),rs.getDouble("amount"),rs.getBoolean("amount_exceeded"),
						rs.getString("exceeded_reason"), rs.getString("status"),rs.getString("event_grade"));
				
				reList.add(re);
			}
			
			return reList;
		}
		catch(SQLException e) {
			System.out.println("Problem from getpending reimbursements method");
		}
		
		return null;
	}
	
	public double getForYear(int id){
		
		List<Reimbursement> reList = new ArrayList<Reimbursement>();
		Reimbursement re = null;
		Date d = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.YEAR, -1);
		d = new Date(c.getTimeInMillis());
		
		
		sqlcommand = "select * from reimbursement r join request re on r.request_id = re.id \r\n"
				+ "where re.emp_id = ? and re.request_date >= ? and re.status != 'denied';";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, id);
			ps.setDate(2, d);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				
				re = new Reimbursement(rs.getInt("id"), rs.getInt("request_id"),rs.getDouble("amount"),rs.getBoolean("amount_exceeded"),
						rs.getString("exceeded_reason"), rs.getString("status"),rs.getString("event_grade"));
				
				reList.add(re);
			}
			double total = 0;
			for(Reimbursement r : reList) {
				total += r.getAmount();
			}
			
			
			return total;
		}
		catch(SQLException e) {
			System.out.println("Problem from getforyear reimbursements method");
		}
		
		return 0;
	}
	
	@Override
	public void update(Reimbursement t) {

		Reimbursement fromDB = this.getById(t.getId());
		
		if (t.equals(fromDB)) {
			System.out.println("This Reimbursement hasn't been changed");
			return;
		}
		else {
			
			fromDB.setAmount(t.getAmount());
			fromDB.setAmountExceeded(t.isAmountExceeded());
			fromDB.setExceededReason(t.getExceededReason());
			fromDB.setStatus(t.getStatus());
			fromDB.setEventGrade(t.getEventGrade());
				
			
		}
		
		sqlcommand = "update reimbursement set amount = ?, amount_exceeded = ?,"
				+ "exceeded_reason = ?, status = ?, event_grade = ? "
				+ "where id = ?;";
		
		try {
			ps = conn.prepareStatement(sqlcommand);
			ps.setDouble(1, t.getAmount() );
			ps.setBoolean(2, t.isAmountExceeded());
			ps.setString(3, t.getExceededReason());
			ps.setString(4, t.getStatus());
			ps.setString(5, t.getEventGrade());
			ps.setInt(6, t.getId());
			
			
			boolean success = ps.execute();
			
			
		}
		catch(SQLException e) {
			System.out.println("updating reimbursement error");
			e.printStackTrace();
		}
		
	}

}
