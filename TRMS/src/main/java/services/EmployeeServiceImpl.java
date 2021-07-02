package services;

import java.sql.SQLException;

import beans.Department;
import beans.Employee;
import beans.Position;
import repos.DepartmentRepo;
import repos.EmployeeRepo;
import repos.PositionRepo;

public class EmployeeServiceImpl implements EmployeeService {

	PositionRepo pr = new PositionRepo();
	DepartmentRepo dr = new DepartmentRepo();
	EmployeeRepo er = new EmployeeRepo();
	
	public String displayEmp(Integer id) {
		
		Employee emp = er.getById(id);
		Employee sup = er.getById(emp.getSupervisor());
		Position pos = pr.getById(emp.getPosition());
		Department dept = dr.getById(emp.getDepartment());

		return emp.toString() + " " + sup.getFirstName() + " " + sup.getLastName() + " " + pos.toString() + " "
				+ dept.toString();

	}

	@Override
	public Employee getById(int id) {
		return er.getById(id);
	}

	@Override
	public Employee getByUserName(String un) {
		return er.getByUserName(un);
	}

	@Override
	public void Login(String un, String pw) {
		Employee emp = er.getByUserName(un);
		if(emp.getPass().equals(pw)) {
			System.out.println("Login Successful");
		}
		else {
			System.out.println("Login Failed. Try again.");
		}
		
	}

	@Override
	public void add(Employee emp) {
		er.add(emp);
		
	}

	
}
