package com.example.repositorylayertest_springdatajpa;

import jakarta.persistence.Table;
import org.hibernate.mapping.List;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest        //  internally used DB to run testcase (in memory DB) ( no need to create DB manually, spring boot creates itself as in memory)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  // each crud function must execute in ordre
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;



        // junit test for save employee details
    @Test
    @Order(1)
    @Rollback(value = false)
    public  void saveEmployee(){
        Employee employee = Employee.builder()
                .firstName("Amuku")
                .lastName("Dumuku")
                .email("amukudumuku97@hotmail.com")
                .build();

        employeeRepository.save(employee);

        assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployee(){
        Employee employee = employeeRepository.findById(1L).get();
        assertThat(employee.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfEmployees(){
//        List<Employee> employeeList = employeeRepository.findAll();
//        assertThat(employeeList.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployee(){
        Employee employee = employeeRepository.findById(1L).get();

        employee.setFirstName("Amuku_1");
        employee.setLastName("Dumuku_1");
        employee.setEmail("amukudumuku_11@yahoo.com");

        Employee employeeUPDATED = employeeRepository.save(employee);

        assertThat(employeeUPDATED.getEmail()).isEqualTo("amukudumuku_11@yahoo.com");
        assertThat(employeeUPDATED.getFirstName()).isEqualTo("Amuku_1");
        assertThat(employeeUPDATED.getLastName()).isEqualTo("Dumuku_1");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployee(){
        Employee employee= employeeRepository.findById(1L).get();
        employeeRepository.delete(employee);

        Employee employee1 = null;
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail("amukudumuku_11@yahoo.com");

        if(optionalEmployee.isPresent()){
            employee1 =optionalEmployee.get();
        }

        assertThat(employee1).isNull();

    }
}
