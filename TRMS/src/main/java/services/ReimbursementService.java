package services;

import java.util.List;

import beans.Reimbursement;

public interface ReimbursementService {

	public void add(Reimbursement r);
	
	public Reimbursement getById(int id);
	
	public List<Reimbursement> getAll();
	
	public void update(Reimbursement r);
}
