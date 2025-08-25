package com.javaornek.soru.config;

import com.javaornek.soru.domain.*;
import com.javaornek.soru.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataSeeder {

	@Bean
	CommandLineRunner seedData(TeacherRepository teacherRepository,
								CourseRepository courseRepository,
								CourseListingRepository listingRepository,
								CustomerRepository customerRepository,
								CartRepository cartRepository,
								StudentRepository studentRepository) {
		return args -> {
			if (teacherRepository.count() > 0) return;
			Teacher t1 = new Teacher();
			t1.setName("Teacher One");
			t1.setEmail("t1@example.com");
			teacherRepository.save(t1);

			Course c1 = new Course();
			c1.setTeacher(t1);
			c1.setTitle("Spring Boot 101");
			c1.setDescription("Intro course");
			c1.setCapacity(100);
			c1.setOpenForEnrollment(true);
			courseRepository.save(c1);

			CourseListing l1 = new CourseListing();
			l1.setCourse(c1);
			l1.setTeacher(t1);
			l1.setPrice(new BigDecimal("49.90"));
			l1.setAvailable(true);
			listingRepository.save(l1);

			Customer cust = new Customer();
			cust.setName("Alice");
			cust.setEmail("alice@example.com");
			customerRepository.save(cust);
			Cart cart = new Cart();
			cart.setCustomer(cust);
			cartRepository.save(cart);

			Student s1 = new Student();
			s1.setName("Bob");
			s1.setEmail("bob@example.com");
			studentRepository.save(s1);
		};
	}
}



