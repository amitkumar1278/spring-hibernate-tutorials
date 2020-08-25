package com.ag.sjh.repositories.many2many;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ag.sjh.domains.many2many.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

	List<Course> findByTitleContaining(String title);

	List<Course> findByFeeLessThan(double fee);

	List<Course> findByAbbreviation(String abbreviation);
	
}
