package repos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import beans.Department;
import beans.Employee;
import beans.Event;
import beans.Reimbursement;
import beans.Request;
import utils.JDBCConnection;

public class RequestRepo implements DataRepository<Request>{

	private String sqlcommand;
	private PreparedStatement ps;
	private ResultSet rs;
	public static Connection conn = JDBCConnection.getConnection();
	EmployeeRepo er = new EmployeeRepo();
	EventRepo evr = new EventRepo();
	
	@Override
	public boolean add(Request t) {
		
		sqlcommand = "insert into request values (default, ?,?,"
				+ "?,?,?,?,?,?);";
		
		try {
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, t.getEvent());
			ps.setDate(2, t.getRequestDate());
			ps.setBoolean(3, t.isSupApproval());
			ps.setBoolean(4, t.isDeptHeadApproval());
			ps.setBoolean(5, t.isBenCoApproval());
			ps.setString(6,t.getStatus());
			ps.setString(7, t.getDenialReason());
			ps.setInt(8, t.getEmpId());
			
			
			
			boolean success = ps.execute();
			
			if (success) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException ex) {
			System.out.println("add request issue");
		}
		
		return false;
	}

	public boolean add(int empId, int eventId) {
		
		Employee emp = er.getById(empId);
		Event ev = evr.getById(eventId);
		
		sqlcommand = "insert into request values (default, ?,?,"
				+ "?,?,?,?,?,?,?,?);";
		
		try {
			ps = conn.prepareStatement(sqlcommand);
			Date d = new Date(System.currentTimeMillis());
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.DATE, 14);
			Date intwoWeeks = new Date(c.getTimeInMillis());
			
			ps.setInt(1, eventId);
			ps.setDate(2, d);
			if((emp.getPosition() == 2)||(emp.getPosition() == 3)) {
			ps.setBoolean(3, true);
			}
			else {
			ps.setBoolean(3, false);
			}
			if (emp.getPosition() == 3) {
			ps.setBoolean(4, true);
			}
			else {
			ps.setBoolean(4, false);
			}
			ps.setBoolean(5, false);
			ps.setString(6,"Pending Approval");
			ps.setString(7, "N/A");
			ps.setInt(8, empId);
			if(ev != null) {
				if((ev.getStartDate().before(intwoWeeks))&&((ev.getStartDate().after(d))||(ev.getStartDate().equals(d)))) {
					ps.setBoolean(9, true);
				}
				else {
					ps.setBoolean(9, false);
				
				}
			}
			ps.setDate(10, d);
			
			
			
			
			boolean success = ps.execute();
			
			if (success) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException ex) {
			System.out.println("add request issue");
		}
		
		return false;
	}
	@Override
	public Request getById(Integer id) {
		
		Request req = null;
		
		sqlcommand = "select * from request where id = ?;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				req = new Request(rs.getInt("id"), rs.getInt("emp_id"),rs.getInt("event_id"),rs.getDate("request_date"),
									rs.getBoolean("supervisor_approval"),rs.getBoolean("dept_head_approval"),
									rs.getBoolean("ben_co_approval"), rs.getString("status"), rs.getString("denial_reason"));
				
			}
			
			return req;
		}
		catch(SQLException e) {
			System.out.println("Problem from get req by id method");
		}
		
		return null;
	}

	public int getLast() {
		int evID = 0;
		sqlcommand = "select id from request order by id desc limit 1;";
		try {
			ps = conn.prepareStatement(sqlcommand);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				evID = rs.getInt("id");
			}
			return evID;
		} catch (SQLException e) {
			System.out.println("Problem from getLastRequest method");
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public ArrayList<Request> getByEmp(Integer id){

		ArrayList<Request> reqList = new ArrayList<Request>();
		Request req = null;
		sqlcommand = "select * from request where emp_id = ? order by id;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				
				req = new Request(rs.getInt("id"), rs.getInt("emp_id"),rs.getInt("event_id"),rs.getDate("request_date"),
						rs.getBoolean("supervisor_approval"),rs.getBoolean("dept_head_approval"),
						rs.getBoolean("ben_co_approval"), rs.getString("status"), rs.getString("denial_reason"));
				
				reqList.add(req);
			}
			
			return reqList;
		}
		catch(SQLException e) {
			System.out.println("Problem from getbyEmp reqs method");
		}
		
		return null;
		
	}
	@Override
	public ArrayList<Request> getAll() {
		
		ArrayList<Request> reqList = new ArrayList<Request>();
		Request req = null;
		sqlcommand = "select * from request order by id;";
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			
			rs = ps.executeQuery();
		
			while(rs.next()) {
				
				req = new Request(rs.getInt("id"), rs.getInt("emp_id"),rs.getInt("event_id"),rs.getDate("request_date"),
						rs.getBoolean("supervisor_approval"),rs.getBoolean("dept_head_approval"),
						rs.getBoolean("ben_co_approval"), rs.getString("status"), rs.getString("denial_reason"));
				
				reqList.add(req);
			}
			
			return reqList;
		}
		catch(SQLException e) {
			System.out.println("Problem from getall reqs method");
		}
		
		return null;
	}
	
	public List<Request> needApproval(int empId){
		
		ArrayList<Request> reqList = new ArrayList<Request>();
		Request req = null;
		Employee emp = er.getById(empId);
		
		if(emp.getPosition() == 2) {
			sqlcommand = "select r.id as id, r.event_id as event_id, r.request_date as request_date, r.supervisor_approval as supervisor_approval, r.dept_head_approval as dept_head_approval, r.ben_co_approval as ben_co_approval,\r\n"
				+ "r.status as status, r.denial_reason as denial_reason, r.emp_id as emp_id, r.isurgent as is_urgent,r.lastchanged as last_changed from request r join employee e on r.emp_id = e.id \r\n"
				+ "where e.supervisor_id = ? and r.supervisor_approval = 'false' and (r.status like 'pending%' or r.status like 'Pending%') order by isUrgent desc;";
		}
		else if(emp.getPosition() == 3) {
			sqlcommand = "select r.id as id, r.event_id as event_id, r.request_date as request_date, r.supervisor_approval as supervisor_approval, r.dept_head_approval as dept_head_approval, r.ben_co_approval as ben_co_approval,\r\n"
					+ "r.status as status, r.denial_reason as denial_reason, r.emp_id as emp_id,r.isurgent as is_urgent, r.lastchanged as last_changed from request r join employee e on r.emp_id = e.id join dept d on e.dept_id = d.id \r\n"
					+ "where r.supervisor_approval = 'true' and r.dept_head_approval = 'false' and d.dept_head = ? and (r.status like 'pending%' or r.status like 'Pending%') order by isUrgent desc;";
		}
		else if(emp.getPosition() == 4) {
			sqlcommand = "select r.id as id, r.event_id as event_id, r.request_date as request_date, r.supervisor_approval as supervisor_approval, r.dept_head_approval as dept_head_approval, r.ben_co_approval as ben_co_approval,\r\n"
					+ "r.status as status, r.denial_reason as denial_reason, r.emp_id as emp_id,r.isurgent as is_urgent,r.lastchanged as last_changed from request r where supervisor_approval ='true' and dept_head_approval = 'true'\r\n"
					+ "and ((ben_co_approval = 'false' and (status like 'pending%' or status like 'Pending%')) or(status = 'Pending Grade' or status = 'Pending Grade Approval')) order by isUrgent desc;";
		}
		try {
			
			ps = conn.prepareStatement(sqlcommand);
			if(emp.getPosition() != 4) {
				ps.setInt(1, empId);
			}
			else {}
			rs = ps.executeQuery();
		
			while(rs.next()) {
				
				req = new Request(rs.getInt("id"), rs.getInt("emp_id"),rs.getInt("event_id"),rs.getDate("request_date"),
						rs.getBoolean("supervisor_approval"),rs.getBoolean("dept_head_approval"),
						rs.getBoolean("ben_co_approval"), rs.getString("status"), rs.getString("denial_reason"),
						rs.getBoolean("is_urgent"),rs.getDate("last_changed"));
				
				reqList.add(req);
			}
			
			return reqList;
		}
		catch(SQLException e) {
			System.out.println("Problem from needApproval reqs method");
		}
		
		return null;
	}

	@Override
	public void update(Request t) {
		
		Request fromDB = this.getById(t.getId());
		Date d = new Date(System.currentTimeMillis());
		
		if (t.equals(fromDB)) {
			System.out.println("This Request hasn't been changed");
			return;
		}
		else {
			
			fromDB.setSupApproval(t.isSupApproval());
			fromDB.setDeptHeadApproval(t.isDeptHeadApproval());
			fromDB.setBenCoApproval(t.isBenCoApproval());
			fromDB.setStatus(t.getStatus());
			fromDB.setDenialReason(t.getDenialReason());
				
			
		}
		
		sqlcommand = "update request set supervisor_approval = ?, "
				+ "dept_head_approval = ?, ben_co_approval = ?,"
				+ "status = ?, denial_reason = ?, lastchanged = ? "
				+ "where id = ?;";
		
		try {
			ps = conn.prepareStatement(sqlcommand);
			ps.setBoolean(1, t.isSupApproval() );
			ps.setBoolean(2, t.isDeptHeadApproval());
			ps.setBoolean(3, t.isBenCoApproval());
			ps.setString(4, t.getStatus());
			ps.setString(5, t.getDenialReason());
			ps.setDate(6, d);
			ps.setInt(7, t.getId());
			
			
			boolean success = ps.execute();
			
			
		}
		catch(SQLException e) {
			System.out.println("updating reimbursement error");
			e.printStackTrace();
		}
		
	}

}
