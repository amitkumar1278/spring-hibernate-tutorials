package com.ag.sjh;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ag.sjh.domains.many2many.Course;
import com.ag.sjh.domains.many2many.Student;
import com.ag.sjh.service.many2many.CourseService;
import com.ag.sjh.service.many2many.StudentService;

@org.springframework.core.annotation.Order(value = 5)
@Component
public class Many2ManyMappingStartupRunner implements CommandLineRunner {

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@Override
	public void run(String... args) throws Exception {
		
		// TODO Auto-generated method stub
		System.out.println("\n\n=============================================================================================");
		System.out.println("######################    Many2Many Mapping demonstration Started    #########################\n\n");

		
		
		List<Course> courses = Arrays.asList(
				new Course("Master of Computer Application", "MCA", 15, 70000),
				new Course("Master of Computer Management", "MCM", 9, 50000),
				new Course("Master of Business Administration", "MBA", 12, 90000),
				new Course("Master of Science", "MS", 9, 30000));
		courseService.saveAllCourses(courses);



		List<Course> courseList = courseService.getAllCourses();
		System.out.println("\n######################    All Courses are below  -> List user  ##########################");
		courseList.forEach(course -> {
			System.out.println(course.toString());
		});
		System.out.println("---------------------------------------------\n");


		
		Course mca = courseService.getCourseByTitle("Master of Computer Application").get(0);
		Course mcm = courseService.getCourseByAbbreviation("MCM").get(0);
		Course mba = courseService.getCourseByAbbreviation("MBA").get(0);
		Course ms = courseService.getCourseByTitle("Master of Science").get(0);
		
		System.out.println("\n######################    Courses by title or abbreviation  ##########################");
		System.out.println(mca.toString());
		System.out.println(mcm.toString());
		System.out.println(mba.toString());
		System.out.println(ms.toString());
		System.out.println("---------------------------------------------\n");

		
		
		Set<Course> mcaSet = new HashSet<Course> (courseService.getCourseByTitle("Master of Computer Application"));
		Set<Course> mcmSet = new HashSet<Course> (courseService.getCourseByAbbreviation("MCM"));
		Set<Course> mbaSet = new HashSet<Course> (courseService.getCourseByAbbreviation("MBA"));
		Set<Course> msSet = new HashSet<Course> (courseService.getCourseByTitle("Master of Science"));

		
		
		List<Student> students = Arrays.asList(
				new Student("Chris Evans", 25, "B", mcaSet), new Student("Chris Hemsworth", 24, "c", mcmSet), 
				new Student("Mark Ruffalo", 26, "A++", mbaSet),  new Student("Tom Holland", 21, "B", msSet),
				new Student("Benedict Cumberbatch", 22, "A++", mcaSet), new Student("Robert Downey, Jr.", 28, "A+++", mcmSet), 
				new Student("Elizabeth Olsen", 22, "B", mbaSet), new Student("Scarlett Johansson", 27, "A+", msSet), 
				new Student("Zoe Saldana", 24, "C", mcaSet), new Student("Cobie Smulders", 25, "A++", mcmSet), 
				new Student("Natalie Portman", 26, "A+", mbaSet), new Student("Tom Hiddleston", 26, "B+", msSet));
		studentService.saveAllStudents(students);



		List<Student> allStudents = (List<Student>) studentService.getAllStudents();
		System.out.println("\n######################    All Students, with there course are below  -> List user  ##########################");
		allStudents.forEach(student -> {
			System.out.println("name: "+student.getName());
			System.out.println(student.toString());
			System.out.println("Printing Course detail of ::"+student.getName());
			student.getCourses().forEach(course -> {
				System.out.println(course.toString());
			});
			System.out.println("---------------------------------------------\n");
		});
		System.out.println("---------------------------------------------\n");



		List<Course> courseList2 = courseService.getAllCourses();
		System.out.println("\n######################    All Courses, with Students are below  -> List user  ##########################");
		courseList2.forEach(course -> {
			System.out.println(course.toString());
			System.out.println("Printing student associate with courses ::");
			course.getStudents().forEach(student -> {
				System.out.println(student.toString());
			});
			System.out.println("---------------------------------------------\n");
		});
		System.out.println("---------------------------------------------\n");


		System.out.println("\n\n######################    Many2Many Mapping demonstration Ended    #########################");
		System.out.println("===========================================================================================\n\n");

	}

}
