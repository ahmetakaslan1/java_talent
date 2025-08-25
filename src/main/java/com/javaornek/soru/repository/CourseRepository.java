package com.javaornek.soru.repository;

import com.javaornek.soru.domain.Course;
import com.javaornek.soru.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
	List<Course> findByTeacher(Teacher teacher);
}


