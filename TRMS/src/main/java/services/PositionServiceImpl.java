package services;

import beans.Position;
import repos.PositionRepo;

public class PositionServiceImpl implements PositionService{

	PositionRepo pr = new PositionRepo();
	
	@Override
	public Position getbyId(int id) {
		
		return pr.getById(id);
	}

}
