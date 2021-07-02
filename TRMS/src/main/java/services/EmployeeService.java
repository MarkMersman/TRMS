package services;

import java.util.List;

import beans.Employee;

public interface EmployeeService {
	
	public Employee getById(int id);
		
	public Employee getByUserName(String un);
	
	public void Login(String un, String pw);
	
	public void add(Employee emp);
	
}
