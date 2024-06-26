package com.tutorial.SpringBootDasar.repository;

import com.tutorial.SpringBootDasar.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
