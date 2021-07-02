package beans;

import java.io.File;
import java.sql.Date;
import java.sql.Time;

public class Event {

	private int id;
	private String eventType;
	private String description;
	private String location;
	private Time eventTime;
	private Date startDate;
	private int gradeFormat;
	private double cost;
	private double timeMissed;
	private String pGrade;
	private File attachment;
	
	public Event() {}

	public Event(int id, String eventType, String description, String location, Time eventTime, Date startDate,
			int gradeFormat, double cost, File attachment) {
		super();
		this.id = id;
		this.eventType = eventType;
		this.description = description;
		this.location = location;
		this.eventTime = eventTime;
		this.startDate = startDate;
		this.gradeFormat = gradeFormat;
		this.cost = cost;
		this.attachment = attachment;
	}

	
	public Event(int id, String eventType, String description, String location, Time eventTime, Date startDate,
			int gradeFormat, double cost) {
		super();
		this.id = id;
		this.eventType = eventType;
		this.description = description;
		this.location = location;
		this.eventTime = eventTime;
		this.startDate = startDate;
		this.gradeFormat = gradeFormat;
		this.cost = cost;
	}
	
	public Event(int id, String eventType, String description, String location, Time eventTime, Date startDate,
			int gradeFormat, double cost, double tMissed, String pGrade) {
		super();
		this.id = id;
		this.eventType = eventType;
		this.description = description;
		this.location = location;
		this.eventTime = eventTime;
		this.startDate = startDate;
		this.gradeFormat = gradeFormat;
		this.cost = cost;
		this.timeMissed = tMissed;
		this.pGrade = pGrade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Time getEventTime() {
		return eventTime;
	}

	public void setEventTime(Time eventTime) {
		this.eventTime = eventTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getGradeFormat() {
		return gradeFormat;
	}

	public void setGradeFormat(int gradeFormat) {
		this.gradeFormat = gradeFormat;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	

	public double getTimeMissed() {
		return timeMissed;
	}

	public void setTimeMissed(double timeMissed) {
		this.timeMissed = timeMissed;
	}

	public String getpGrade() {
		return pGrade;
	}

	public void setpGrade(String pGrade) {
		this.pGrade = pGrade;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachment == null) ? 0 : attachment.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((eventTime == null) ? 0 : eventTime.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + gradeFormat;
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((pGrade == null) ? 0 : pGrade.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		temp = Double.doubleToLongBits(timeMissed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (attachment == null) {
			if (other.attachment != null)
				return false;
		} else if (!attachment.equals(other.attachment))
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eventTime == null) {
			if (other.eventTime != null)
				return false;
		} else if (!eventTime.equals(other.eventTime))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (gradeFormat != other.gradeFormat)
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (pGrade == null) {
			if (other.pGrade != null)
				return false;
		} else if (!pGrade.equals(other.pGrade))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (Double.doubleToLongBits(timeMissed) != Double.doubleToLongBits(other.timeMissed))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventType=" + eventType + ", description=" + description + ", location="
				+ location + ", eventTime=" + eventTime + ", startDate=" + startDate + ", gradeFormat=" + gradeFormat
				+ ", cost=" + cost + ", timeMissed=" + timeMissed + ", pGrade=" + pGrade + "]";
	}

	
	
	
	
}
