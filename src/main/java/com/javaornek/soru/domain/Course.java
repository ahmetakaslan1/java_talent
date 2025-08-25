package com.javaornek.soru.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

	@Column(nullable = false)
	private String title;

	@Column(length = 2048)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", nullable = false)
	private Teacher teacher;

	@Column(nullable = false)
	private Integer capacity;

	@Column(nullable = false)
	private Boolean openForEnrollment = true;

	@ManyToMany
	@JoinTable(
			name = "course_students",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "student_id")
	)
	private List<Student> students = new ArrayList<>();

	// Optional field: base price reference (actual price may come from CourseListing)
	private BigDecimal basePrice;

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public Teacher getTeacher() { return teacher; }
	public void setTeacher(Teacher teacher) { this.teacher = teacher; }

	public Integer getCapacity() { return capacity; }
	public void setCapacity(Integer capacity) { this.capacity = capacity; }

	public Boolean getOpenForEnrollment() { return openForEnrollment; }
	public void setOpenForEnrollment(Boolean openForEnrollment) { this.openForEnrollment = openForEnrollment; }

	public List<Student> getStudents() { return students; }
	public void setStudents(List<Student> students) { this.students = students; }

	public BigDecimal getBasePrice() { return basePrice; }
	public void setBasePrice(BigDecimal basePrice) { this.basePrice = basePrice; }
}


