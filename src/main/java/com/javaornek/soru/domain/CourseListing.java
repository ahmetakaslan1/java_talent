package com.javaornek.soru.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "course_listings")
public class CourseListing extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", nullable = false)
	private Teacher teacher;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private Boolean available = true;

	public Course getCourse() { return course; }
	public void setCourse(Course course) { this.course = course; }

	public Teacher getTeacher() { return teacher; }
	public void setTeacher(Teacher teacher) { this.teacher = teacher; }

	public BigDecimal getPrice() { return price; }
	public void setPrice(BigDecimal price) { this.price = price; }

	public Boolean getAvailable() { return available; }
	public void setAvailable(Boolean available) { this.available = available; }
}


