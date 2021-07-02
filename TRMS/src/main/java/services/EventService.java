package services;

import beans.Event;

public interface EventService {

	public void add(Event  ev);
	
	public Event getbyId(int id);
	
	
}
