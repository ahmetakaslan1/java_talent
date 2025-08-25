package com.javaornek.soru.service;

import com.javaornek.soru.domain.Course;
import com.javaornek.soru.domain.Student;
import com.javaornek.soru.repository.CourseRepository;
import com.javaornek.soru.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;

	public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
	}

	public List<Course> getCoursesForStudent(Long studentId) {
		Student s = studentRepository.findById(studentId).orElseThrow();
		return s.getCourses();
	}

	public boolean hasCapacity(Course course) {
		return course.getStudents().size() < course.getCapacity();
	}
}


