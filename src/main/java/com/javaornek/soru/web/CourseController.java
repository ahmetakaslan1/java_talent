package com.javaornek.soru.web;

import com.javaornek.soru.domain.Course;
import com.javaornek.soru.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("/students/{studentId}")
	public List<Course> getCoursesForStudent(@PathVariable Long studentId) {
		return courseService.getCoursesForStudent(studentId);
	}
}


