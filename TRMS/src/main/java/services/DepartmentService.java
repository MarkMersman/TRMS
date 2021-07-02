package services;

import java.util.List;

import beans.Department;

public interface DepartmentService {

	public List<Department> getAll();
	
	public Department getById(int id);
	

	
	
}
