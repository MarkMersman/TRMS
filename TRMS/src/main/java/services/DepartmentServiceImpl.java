package services;

import java.util.List;

import beans.Department;
import repos.DepartmentRepo;

public class DepartmentServiceImpl implements DepartmentService {

	DepartmentRepo dr = new DepartmentRepo();
	
	@Override
	public List<Department> getAll() {
		return dr.getAll();
	}

	@Override
	public Department getById(int id) {
		return dr.getById(id);
		
	}

}
