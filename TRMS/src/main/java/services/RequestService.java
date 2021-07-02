package services;

import java.util.List;

import beans.Request;

public interface RequestService {

	public void add(Request req);
	
	public Request getById(int id);
	
	public List<Request> getAll();
	
	public List<Request> getbyEmp(int id);
	
	public void update(Request req);
}
