package com.ag.sjh.service.many2many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ag.sjh.domains.many2many.Course;
import com.ag.sjh.repositories.many2many.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	CourseRepository courseRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * getting all record by using the method findaAll() of CrudRepository
	 */
	@Transactional
	public List<Course> getAllCourses() {
		
		List<Course> courses = new ArrayList<>();
		
		courseRepository.findAll().forEach(course1 -> {
			courses.add(course1);
			course1.getStudents().size();
		});
		return courses;
		
	}

	/**
	 * getting a specific record by using the method findById() of CrudRepository
	 */
	public Course getCourseById(Long courseID) {
		Course course = courseRepository.findById(courseID).get();
		return course;
	}

	/**
	 * saving a specific record by using the method save() of CrudRepository
	 */
	public Course saveOrUpdate(Course course) {
		Course savedCourse = courseRepository.save(course);
		return savedCourse;
	}

	/**
	 * deleting a specific record by using the method deleteById() of CrudRepository
	 */
	public void delete(Long courseID) {
		courseRepository.deleteById(courseID);
	}


	/**
	 * saving a list of record by using the method saveAll() of CrudRepository
	 */
	public void saveAllCourses(List<Course> courses) {
		courseRepository.saveAll(courses);
	}

	/**
	 * getting a specific record by using the method getCourseByTitle()
	 */
	public List<Course>  getCourseByTitle(String title) {
		
		List<Course> courses = new ArrayList<>();
		courseRepository.findByTitleContaining(title).forEach(course1 -> {
			courses.add(course1);
		});
		
		return courses;
	}
	

	/**
	 * getting a specific record by using the method getCourseByTitle()
	 */
	public List<Course>  getCourseByAbbreviation(String abbreviation) {
		
		List<Course> courses = new ArrayList<>();
		courseRepository.findByAbbreviation(abbreviation).forEach(course1 -> {
			courses.add(course1);
		});
		
		return courses;
	}
	
	
	/**
	 * getting a specific record by using the method getCourseByFeeLessThan()
	 */
	public List<Course>  getCourseByFeeLessThan(double fee) {
		
		List<Course> courses = new ArrayList<>();
		courseRepository.findByFeeLessThan(fee).forEach(course1 -> {
			courses.add(course1);
		});
		
		return courses;
	}
}
