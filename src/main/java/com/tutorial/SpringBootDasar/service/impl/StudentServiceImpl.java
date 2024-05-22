package com.tutorial.SpringBootDasar.service.impl;

import com.tutorial.SpringBootDasar.entity.Student;
import com.tutorial.SpringBootDasar.repository.StudentRepository;
import com.tutorial.SpringBootDasar.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> getById(Long id) {
        return studentRepository.findById(String.valueOf(id));
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student update(Student student) {
//        getById(student.getId());
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(String.valueOf(id));
    }
}
