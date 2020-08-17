package com.spring.sboot.repositories.many2many;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.domains.many2many.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

//    List<Course> findByTitleContaining(String title);
//
//    List<Course> findByFeeLessThan(double fee);
}
