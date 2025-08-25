package com.javaornek.soru.web;

import com.javaornek.soru.domain.Course;
import com.javaornek.soru.domain.Teacher;
import com.javaornek.soru.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

	private final TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@GetMapping
	public List<Teacher> getTeachers() { return teacherService.getTeachers(); }

	@PostMapping
	public Teacher createTeacher(@RequestBody Map<String, String> req) {
		return teacherService.createTeacher(req.get("name"), req.get("email"));
	}

	@PutMapping("/{id}")
	public Teacher updateTeacher(@PathVariable Long id, @RequestBody Map<String, String> req) {
		return teacherService.updateTeacher(id, req.get("name"), req.get("email"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
		teacherService.deleteTeacher(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/courses")
	public List<Course> getAllCoursesForTeacher(@PathVariable Long id) {
		return teacherService.getAllCoursesForTeacher(id);
	}

	@PostMapping("/{id}/courses")
	public Course createCourseForTeacher(@PathVariable Long id, @RequestBody Map<String, Object> req) {
		String title = (String) req.get("title");
		String description = (String) req.get("description");
		Integer capacity = ((Number) req.get("capacity")).intValue();
		return teacherService.createCourseForTeacher(id, title, description, capacity);
	}

	@PutMapping("/{teacherId}/courses/{courseId}")
	public Course updateCourseForTeacher(@PathVariable Long teacherId,
										@PathVariable Long courseId,
										@RequestBody Map<String, Object> req) {
		String title = (String) req.get("title");
		String description = (String) req.get("description");
		Integer capacity = req.get("capacity") == null ? null : ((Number) req.get("capacity")).intValue();
		Boolean open = (Boolean) req.get("openForEnrollment");
		return teacherService.updateCourseForTeacher(teacherId, courseId, title, description, capacity, open);
	}

	@DeleteMapping("/{teacherId}/courses/{courseId}")
	public ResponseEntity<Void> deleteCourseForTeacher(@PathVariable Long teacherId, @PathVariable Long courseId) {
		teacherService.deleteCourseForTeacher(teacherId, courseId);
		return ResponseEntity.noContent().build();
	}
}


