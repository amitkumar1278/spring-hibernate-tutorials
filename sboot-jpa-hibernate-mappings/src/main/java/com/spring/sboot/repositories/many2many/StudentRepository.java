package com.spring.sboot.repositories.many2many;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.domains.many2many.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

	//	List<Student> findByNameContaining(String name);

}
