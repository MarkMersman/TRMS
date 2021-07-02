package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Time;
import java.sql.Date;

import org.junit.Ignore;
import org.junit.Test;

import beans.Event;
import beans.GradeFormat;
import beans.Position;
import beans.Reimbursement;
import beans.Request;
import junit.framework.Assert;
import repos.DepartmentRepo;
import repos.EmployeeRepo;
import repos.EventRepo;
import repos.GradeFormatRepo;
import repos.PositionRepo;
import repos.ReimbursementRepo;
import repos.RequestRepo;

public class repoTests {

	DepartmentRepo dr = new DepartmentRepo();
	PositionRepo pr = new PositionRepo();
	GradeFormatRepo gf = new GradeFormatRepo();
	EventRepo er = new EventRepo();
	EmployeeRepo empr = new EmployeeRepo();
	ReimbursementRepo rr = new ReimbursementRepo();
	RequestRepo reqr = new RequestRepo();
	
	
	
	@Ignore
	@Test
	public void deptTest() {
		System.out.println(dr.getById(2));
	}
	@SuppressWarnings("deprecation")
	@Test
	public void posTest() {
		Position newPos = new Position(3,"department head");
		Position p = pr.getById(3);
		Assert.assertEquals(newPos, p);
	}
	@Ignore
	@Test
	public void gfTest() {
		System.out.println(gf.getById(4));
	}
	@Ignore
	@Test
	public void eventAddTest() {
		Time t = Time.valueOf("10:00:00");
		Date d =  Date.valueOf("2021-9-14");
		//File fil = new File("C:\\Users\\markm\\OneDrive\\Documents\\Project1 TRMS.docx");
		Event ev = new Event(1,"Japanese Class","Entry level Japanese class","Wichita,ks",t,d,1,200.00);
		
		er.add(ev);
		
		//System.out.println(gf.getById(4));
	}
	@Ignore
	@Test
	public void evGBIDTest() {
		System.out.println(er.getById(4));
	}
	
	@Ignore
	@Test
	public void empTests() {
		System.out.println(empr.getById(6));
	}
	
	@Ignore
	@Test
	public void ReimTests() {
		Reimbursement reim = new Reimbursement(6,22,300.50,false,"N/A","pending","B");
		rr.add(reim);
		
		//Reimbursement fromDb = rr.getById(6);
		
		//rr.update(reim);
		
		//System.out.println(rr.getById(2));
	}
	@Ignore
	@Test
	public void ReqTests() {
		Date d = Date.valueOf("2021-06-17");
		Request req = new Request(1,3,3,d,false,false,false,"pending", "N/A");
		//req.setBenCoApproval(false);
		//reqr.update(req);
		
		//req.setId(23);
		reqr.add(req);
		//System.out.println(reqr.getByEmp(3));
	}
	
	@Ignore
	@Test
	public void ReimAddTest1() {
		rr.add(26);
	}
	@Ignore
	@Test
	public void reqGetAppr() {
		System.out.println(reqr.needApproval(6));
	}
	@Ignore
	@Test
	public void addReim() {
		rr.add(44);
	
	}

}
