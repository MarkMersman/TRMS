package services;

import java.util.List;

import beans.Request;
import repos.RequestRepo;

public class RequestServiceImpl implements RequestService{

	RequestRepo rr = new RequestRepo();
	
	@Override
	public void add(Request req) {
		rr.add(req);
		
	}
	public List<Request> needApproval(int empId){
		return rr.needApproval(empId);
	}
	public int getLast() {
		return rr.getLast();
	}
	public void add (int empId, int evId) {
		rr.add(empId, evId);
	}

	@Override
	public Request getById(int id) {
		
		return rr.getById(id);
	}

	@Override
	public List<Request> getAll() {
		
		return rr.getAll();
	}

	@Override
	public List<Request> getbyEmp(int id) {
		
		return rr.getByEmp(id);
	}

	@Override
	public void update(Request req) {
		
		rr.update(req);
		
	}
	
	/*
	 * public List<Request> getPending(int id) {
	 * 
	 * return rr.getPending(id);
	 * 
	 * }
	 */

}
