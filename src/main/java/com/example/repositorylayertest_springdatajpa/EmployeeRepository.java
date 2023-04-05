package com.example.repositorylayertest_springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmail(String email);
}