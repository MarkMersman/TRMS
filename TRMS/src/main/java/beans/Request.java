package beans;

import java.io.File;
import java.sql.Date;

public class Request {

	private int id;
	private int empId;
	private int event;
	private Date requestDate;
	private boolean supApproval;
	private boolean deptHeadApproval;
	private boolean benCoApproval;
	private String status;
	private String denialReason;
	private boolean isUrgent;
	private Date lastChanged;
	private File approvalAttachment;
	
	public Request() {}

	public Request(int id, int empId, int event, Date requestDate, boolean supApproval, boolean deptHeadApproval,
			boolean benCoApproval, String status, String denialReason, File approvalAttachment) {
		super();
		this.id = id;
		this.empId = empId;
		this.event = event;
		this.requestDate = requestDate;
		this.supApproval = supApproval;
		this.deptHeadApproval = deptHeadApproval;
		this.benCoApproval = benCoApproval;
		this.status = status;
		this.denialReason = denialReason;
		this.approvalAttachment = approvalAttachment;
	}
	
	public Request(int id, int empId, int event, Date requestDate, boolean supApproval, boolean deptHeadApproval,
			boolean benCoApproval, String status, String denialReason, boolean isUrgent, Date lastChanged) {
		super();
		this.id = id;
		this.empId = empId;
		this.event = event;
		this.requestDate = requestDate;
		this.supApproval = supApproval;
		this.deptHeadApproval = deptHeadApproval;
		this.benCoApproval = benCoApproval;
		this.status = status;
		this.denialReason = denialReason;
		this.isUrgent = isUrgent;
		this.lastChanged = lastChanged;
	}

	public Request(int id, int empId, int event, Date requestDate, boolean supApproval, boolean deptHeadApproval,
			boolean benCoApproval, String status, String denialReason) {
		super();
		this.id = id;
		this.empId = empId;
		this.event = event;
		this.requestDate = requestDate;
		this.supApproval = supApproval;
		this.deptHeadApproval = deptHeadApproval;
		this.benCoApproval = benCoApproval;
		this.status = status;
		this.denialReason = denialReason;
		this.isUrgent = false;		
		this.lastChanged = new Date(System.currentTimeMillis());
	}

	public Request(int id, int empId, int event, Date requestDate, boolean supApproval, boolean deptHeadApproval,
			boolean benCoApproval, String status) {
		super();
		this.id = id;
		this.empId = empId;
		this.event = event;
		this.requestDate = requestDate;
		this.supApproval = supApproval;
		this.deptHeadApproval = deptHeadApproval;
		this.benCoApproval = benCoApproval;
		this.status = status;
		this.isUrgent = false;		
		this.lastChanged = new Date(System.currentTimeMillis());
	}
	
	public Request(int id, int empId, Event event, Date requestDate, boolean supApproval, boolean deptHeadApproval,
			boolean benCoApproval, String status) {
		super();
		this.id = id;
		this.empId = empId;
		this.event = event.getId();
		this.requestDate = requestDate;
		this.supApproval = supApproval;
		this.deptHeadApproval = deptHeadApproval;
		this.benCoApproval = benCoApproval;
		this.status = status;
		this.isUrgent = false;		
		this.lastChanged = new Date(System.currentTimeMillis());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}
	
	public void setEvent(Event event) {
		this.event = event.getId();
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public boolean isSupApproval() {
		return supApproval;
	}

	public void setSupApproval(boolean supApproval) {
		this.supApproval = supApproval;
	}

	public boolean isDeptHeadApproval() {
		return deptHeadApproval;
	}

	public void setDeptHeadApproval(boolean deptHeadApproval) {
		this.deptHeadApproval = deptHeadApproval;
	}

	public boolean isBenCoApproval() {
		return benCoApproval;
	}

	public void setBenCoApproval(boolean benCoApproval) {
		this.benCoApproval = benCoApproval;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDenialReason() {
		return denialReason;
	}

	public void setDenialReason(String denialReason) {
		this.denialReason = denialReason;
	}

	public File getApprovalAttachment() {
		return approvalAttachment;
	}

	public void setApprovalAttachment(File approvalAttachment) {
		this.approvalAttachment = approvalAttachment;
	}	

	public boolean isUrgent() {
		return isUrgent;
	}

	public void setUrgent(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}

	public Date getLastChanged() {
		return lastChanged;
	}

	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvalAttachment == null) ? 0 : approvalAttachment.hashCode());
		result = prime * result + (benCoApproval ? 1231 : 1237);
		result = prime * result + ((denialReason == null) ? 0 : denialReason.hashCode());
		result = prime * result + (deptHeadApproval ? 1231 : 1237);
		result = prime * result + empId;
		result = prime * result + event;
		result = prime * result + id;
		result = prime * result + (isUrgent ? 1231 : 1237);
		result = prime * result + ((lastChanged == null) ? 0 : lastChanged.hashCode());
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (supApproval ? 1231 : 1237);
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
		Request other = (Request) obj;
		if (approvalAttachment == null) {
			if (other.approvalAttachment != null)
				return false;
		} else if (!approvalAttachment.equals(other.approvalAttachment))
			return false;
		if (benCoApproval != other.benCoApproval)
			return false;
		if (denialReason == null) {
			if (other.denialReason != null)
				return false;
		} else if (!denialReason.equals(other.denialReason))
			return false;
		if (deptHeadApproval != other.deptHeadApproval)
			return false;
		if (empId != other.empId)
			return false;
		if (event != other.event)
			return false;
		if (id != other.id)
			return false;
		if (isUrgent != other.isUrgent)
			return false;
		if (lastChanged == null) {
			if (other.lastChanged != null)
				return false;
		} else if (!lastChanged.equals(other.lastChanged))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (supApproval != other.supApproval)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", empId=" + empId + ", event=" + event + ", requestDate=" + requestDate
				+ ", supApproval=" + supApproval + ", deptHeadApproval=" + deptHeadApproval + ", benCoApproval="
				+ benCoApproval + ", status=" + status + ", denialReason=" + denialReason + ", isUrgent=" + isUrgent
				+ ", lastChanged=" + lastChanged + "]";
	}

	

	
	
	
}
