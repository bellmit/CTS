import java.util.Map;

public class ABCDevEngine {
	public Map<Integer, Map> processData(String sourceFolder,
			String inputfileName) throws EmployeeBusinessException {

		return null;

	}

}

class EmployeeBusinessException extends Exception {
	public EmployeeBusinessException(String message) {
		super(message);
	}

	public EmployeeBusinessException(Throwable throwable) {
		super(throwable);
	}

}

class EmpVO {

	private String empId;
	private String empName;
	private String empDesignation;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpVO other = (EmpVO) obj;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		return true;
	}
}
