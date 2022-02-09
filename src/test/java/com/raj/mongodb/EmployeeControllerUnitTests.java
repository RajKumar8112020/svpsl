package com.raj.mongodb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.raj.mongodb.model.Employee;
import com.raj.mongodb.repo.EmployeeRepository;

@SpringBootTest(classes = MySpringbootApplication.class)
@TestMethodOrder(OrderAnnotation.class)
class EmployeeControllerUnitTests {

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	@Order(1)
	public void testReadAll() {
		List<Employee> employee = employeeRepository.findAll();
		assertThat(employee).size().isGreaterThan(0);
	}
	
	@Test
	@Order(2)
	public void testUpdate() {
		Employee e = employeeRepository.findById(1L).get();
		e.setFirstName("Rahul");
		e.setLastName("Singh");
		e.setEmailId("rahul@gmail.com");
		employeeRepository.save(e);
		assertNotEquals("Sanj", employeeRepository.findById(1L).get().getFirstName());
	}
	
	@Test
	@Order(3)
	public void testDelete() {
		employeeRepository.deleteById(2L);
		assertThat(employeeRepository.existsById(2L)).isFalse();
	}
	
	
}
