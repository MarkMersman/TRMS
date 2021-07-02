package tests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import services.EmployeeServiceImpl;
import services.ReimbursementServiceImpl;

public class ServicesTests {

	EmployeeServiceImpl es = new EmployeeServiceImpl();
	ReimbursementServiceImpl rr = new ReimbursementServiceImpl();
	
	@Ignore
	@Test
	public void test() {
		System.out.println(rr.getPending(2));
	}

}
