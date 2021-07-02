package beans;

public class Department {

	private int id;
	private String name;
	private int deptHead;
	
	public Department() {
		
	}

	
	public Department(int id, String name, int deptHead) {
		super();
		this.id = id;
		this.name = name;
		this.deptHead = deptHead;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDeptHead() {
		return deptHead;
	}

	public void setDeptHead(int deptHead) {
		this.deptHead = deptHead;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deptHead;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Department other = (Department) obj;
		if (deptHead != other.deptHead)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Department [name=" + name + ", deptHead=" + deptHead + "]";
	}
	
	
}
