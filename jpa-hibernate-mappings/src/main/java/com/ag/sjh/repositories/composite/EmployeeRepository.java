package com.ag.sjh.repositories.composite;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ag.sjh.domains.composite.Employee;
import com.ag.sjh.domains.composite.EmployeeId;

public interface EmployeeRepository extends CrudRepository<Employee, EmployeeId>  {
	 List<Employee> findByEmployeeIdDepartmentId(Long departmentId);
}
