package services;

import java.util.List;

import beans.Reimbursement;
import repos.ReimbursementRepo;

public class ReimbursementServiceImpl implements ReimbursementService{

	ReimbursementRepo rr = new ReimbursementRepo();
	
	@Override
	public void add(Reimbursement r) {
		rr.add(r);
		
	}
	public void add(int reqId) {
		rr.add(reqId);
	}

	@Override
	public Reimbursement getById(int id) {
	
		return rr.getById(id);
	}
	
	public Reimbursement getByReq(int reqId) {
		return rr.getByReq(reqId);
	}

	@Override
	public List<Reimbursement> getAll() {
		
		return rr.getAll();
	}

	@Override
	public void update(Reimbursement r) {
		
		rr.update(r);
		
	}
	
	
	public List<Reimbursement> getPending(int id) {
	  
	  return rr.getPending(id);
	  
	}
	 

}
