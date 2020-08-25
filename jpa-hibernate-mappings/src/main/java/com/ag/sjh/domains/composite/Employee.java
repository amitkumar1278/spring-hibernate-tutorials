package com.ag.sjh.domains.composite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmployeeId employeeId;
	
	@Column
	private String name;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String phoneNumber;

	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Employee(EmployeeId employeeId, String name, String email, String phoneNumber) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}


	public EmployeeId getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(EmployeeId employeeId) {
		this.employeeId = employeeId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	
	
}
