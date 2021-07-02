package services;

import beans.Event;
import repos.EventRepo;

public class EventServiceImpl implements EventService {

	EventRepo er = new EventRepo();
	
	@Override
	public void add(Event ev) {
		er.add(ev);
		
	}

	@Override
	public Event getbyId(int id) {
		return er.getById(id);
	}
	
	public int getLast() {
		return er.getLast();
	}

	
}
