package com.tutorial.SpringBootDasar;

import com.tutorial.SpringBootDasar.entity.Student;
import com.tutorial.SpringBootDasar.repository.StudentRepository;
import com.tutorial.SpringBootDasar.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Student student = new Student();
        student.setId(4L);
        student.setName("Fairuz");

        when(studentRepository.save(student)).thenReturn(student);

        Student createdStudent = studentService.create(student);

        assertEquals(student.getId(), createdStudent.getId());
        assertEquals(student.getName(), createdStudent.getName());

        System.out.println(student.getId());
        System.out.println(createdStudent.getId());
    }

    @Test
    void testGetById() {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");

        when(studentRepository.findById("1")).thenReturn(Optional.of(student));

        Optional<Student> foundStudent = studentService.getById(1L);

        assertTrue(foundStudent.isPresent());
        assertEquals(student.getId(), foundStudent.get().getId());
        assertEquals(student.getName(), foundStudent.get().getName());
    }

    @Test
    void testGetAll() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Doe");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Doe");

        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> foundStudents = studentService.getAll();

        assertEquals(2, foundStudents.size());
        assertEquals(student1.getId(), foundStudents.get(0).getId());
        assertEquals(student2.getId(), foundStudents.get(1).getId());
    }

    @Test
    void testUpdate() {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");

        when(studentRepository.findById("1")).thenReturn(Optional.of(student));
        when(studentRepository.saveAndFlush(student)).thenReturn(student);

        Student updatedStudent = studentService.update(student);

        assertEquals(student.getId(), updatedStudent.getId());
        assertEquals(student.getName(), updatedStudent.getName());
    }

    @Test
    void testDelete() {

        doNothing().when(studentRepository).deleteById("1");

        studentService.delete(1L);

        verify(studentRepository, times(1)).deleteById("1");
    }
}
