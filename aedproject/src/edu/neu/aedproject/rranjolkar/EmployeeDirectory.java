package edu.neu.aedproject.rranjolkar;

import java.util.List;

public class EmployeeDirectory extends IdObject {
	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
