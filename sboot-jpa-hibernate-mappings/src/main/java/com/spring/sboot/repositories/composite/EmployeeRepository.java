package com.spring.sboot.repositories.composite;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.domains.composite.Employee;
import com.spring.sboot.domains.composite.EmployeeId;

public interface EmployeeRepository extends CrudRepository<Employee, EmployeeId>  {
	 List<Employee> findByEmployeeIdDepartmentId(Long departmentId);
}
