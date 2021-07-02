package beans;

import java.io.File;

public class Reimbursement {

	private int id;
	private int requestId;
	private double amount;
	private boolean amountExceeded;
	private String exceededReason;
	private String status;
	private String eventGrade;
	
	public Reimbursement() {
		
	}

	public Reimbursement(int id, int requestId, double amount, boolean amountExceeded, String exceededReason,
			String status, String grade) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.amount = amount;
		this.amountExceeded = amountExceeded;
		this.exceededReason = exceededReason;
		this.status = status;
		this.eventGrade = grade;
	}
	

	public Reimbursement(int id, int requestId, double amount, boolean amountExceeded, String status,
			String grade) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.amount = amount;
		this.amountExceeded = amountExceeded;
		this.status = status;
		this.eventGrade = grade;
	}

	public Reimbursement(int id, int requestId, double amount, boolean amountExceeded, String status) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.amount = amount;
		this.amountExceeded = amountExceeded;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isAmountExceeded() {
		return amountExceeded;
	}

	public void setAmountExceeded(boolean amountExceeded) {
		this.amountExceeded = amountExceeded;
	}

	public String getExceededReason() {
		return exceededReason;
	}

	public void setExceededReason(String exceededReason) {
		this.exceededReason = exceededReason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public String getEventGrade() {
		return eventGrade;
	}

	public void setEventGrade(String eventGrade) {
		this.eventGrade = eventGrade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (amountExceeded ? 1231 : 1237);
		result = prime * result + ((eventGrade == null) ? 0 : eventGrade.hashCode());
		result = prime * result + ((exceededReason == null) ? 0 : exceededReason.hashCode());
		result = prime * result + id;
		result = prime * result + requestId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (amountExceeded != other.amountExceeded)
			return false;
		if (eventGrade == null) {
			if (other.eventGrade != null)
				return false;
		} else if (!eventGrade.equals(other.eventGrade))
			return false;
		if (exceededReason == null) {
			if (other.exceededReason != null)
				return false;
		} else if (!exceededReason.equals(other.exceededReason))
			return false;
		if (id != other.id)
			return false;
		if (requestId != other.requestId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", requestId=" + requestId + ", amount=" + amount + ", amountExceeded="
				+ amountExceeded + ", exceededReason=" + exceededReason + ", status=" + status + ", eventGrade="
				+ eventGrade + "]";
	}

	
	
	
}
