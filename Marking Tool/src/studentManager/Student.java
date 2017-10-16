package studentManager;

import java.io.Serializable;


public class Student implements Serializable, Comparable<Student> {
	private String sID; 
	private String name; 
	private double Score;
	private String sex;
	
	

	public Student() {
	}

	public Student(String sID, String name) {
		this.sID = sID;
		this.name = name;
		this.sex = sex;
	
	}

	public String getsID() {
		return sID;
	}

	public void setsID(String sID) {
		this.sID = sID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	

	@Override
	public String toString() {
		return name;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (sID == null) {
			if (other.sID != null)
				return false;
		} else if (!sID.equals(other.sID))
			return false;
		
		return true;
	}

	@Override
	public int compareTo(Student o) {
		if (this.sID.compareTo(o.sID) > 0) {
			return 1;
		} else if (this.sID.compareTo(o.sID) < 0) {
			return -1;
		} else {
			return 0;
		}
	}

}