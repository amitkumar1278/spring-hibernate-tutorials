package com.spring.sboot.service.many2many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.sboot.domains.many2many.Student;
import com.spring.sboot.repositories.many2many.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * getting all record by using the method findaAll() of CrudRepository
	 */

	@Transactional
	public List<Student> getAllStudents() {
		
		List<Student> students = new ArrayList<>();
		studentRepository.findAll().forEach(student1 -> {
			students.add(student1);
			student1.getCourses().size();
		});
		return students;
	}

	/**
	 * getting a specific record by using the method findById() of CrudRepository
	 */
	@Transactional
	public Student getStudentById(Long studentID) {
		Student student = studentRepository.findById(studentID).get();
		Hibernate.initialize(student.getCourses());
		return student;
	}

	/**
	 * saving a specific record by using the method save() of CrudRepository
	 */
	public Student saveOrUpdate(Student student) {
		Student savedStudent = studentRepository.save(student);
		return savedStudent;
	}

	/**
	 * deleting a specific record by using the method deleteById() of CrudRepository
	 */
	public void delete(Long studentID) {
		studentRepository.deleteById(studentID);
	}


	/**
	 * saving a list of record by using the method saveAll() of CrudRepository
	 */
	public void saveAllStudents(List<Student> students) {
		studentRepository.saveAll(students);
	}

	/**
	 * getting a specific record by using the method getStudentByTitle()
	 */
	@Transactional
	public Student getStudentByNameContaining(String name) {
		Student student = studentRepository.findByNameContaining(name).get(0);
		Hibernate.initialize(student.getCourses());
		return student;
	}
}
