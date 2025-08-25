package com.javaornek.soru.service;

import com.javaornek.soru.domain.Course;
import com.javaornek.soru.domain.Teacher;
import com.javaornek.soru.repository.CourseRepository;
import com.javaornek.soru.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

	private final TeacherRepository teacherRepository;
	private final CourseRepository courseRepository;

	public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
		this.teacherRepository = teacherRepository;
		this.courseRepository = courseRepository;
	}

	public List<Teacher> getTeachers() {
		return teacherRepository.findAll();
	}

	public Teacher createTeacher(String name, String email) {
		Teacher t = new Teacher();
		t.setName(name);
		t.setEmail(email);
		return teacherRepository.save(t);
	}

	public Teacher updateTeacher(Long id, String name, String email) {
		Teacher t = teacherRepository.findById(id).orElseThrow();
		if (name != null) t.setName(name);
		if (email != null) t.setEmail(email);
		return teacherRepository.save(t);
	}

	@Transactional
	public void deleteTeacher(Long id) {
		teacherRepository.deleteById(id);
	}

	public List<Course> getAllCoursesForTeacher(Long teacherId) {
		Teacher t = teacherRepository.findById(teacherId).orElseThrow();
		return courseRepository.findByTeacher(t);
	}

	public Course createCourseForTeacher(Long teacherId, String title, String description, Integer capacity) {
		Teacher t = teacherRepository.findById(teacherId).orElseThrow();
		Course c = new Course();
		c.setTeacher(t);
		c.setTitle(title);
		c.setDescription(description);
		c.setCapacity(capacity);
		c.setOpenForEnrollment(true);
		return courseRepository.save(c);
	}

	public Course updateCourseForTeacher(Long teacherId, Long courseId, String title, String description, Integer capacity, Boolean open) {
		Teacher t = teacherRepository.findById(teacherId).orElseThrow();
		Course c = courseRepository.findById(courseId).orElseThrow();
		if (!c.getTeacher().getId().equals(t.getId())) throw new IllegalArgumentException("Course not owned by teacher");
		if (title != null) c.setTitle(title);
		if (description != null) c.setDescription(description);
		if (capacity != null) c.setCapacity(capacity);
		if (open != null) c.setOpenForEnrollment(open);
		return courseRepository.save(c);
	}

	public void deleteCourseForTeacher(Long teacherId, Long courseId) {
		Teacher t = teacherRepository.findById(teacherId).orElseThrow();
		Course c = courseRepository.findById(courseId).orElseThrow();
		if (!c.getTeacher().getId().equals(t.getId())) throw new IllegalArgumentException("Course not owned by teacher");
		courseRepository.delete(c);
	}
}


