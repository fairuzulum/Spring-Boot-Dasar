package com.tutorial.SpringBootDasar.service;

import com.tutorial.SpringBootDasar.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student create(Student student);
    Optional<Student> getById(Long id);
    List<Student> getAll();
    Student update(Student student);
    void delete(Long id);
}
