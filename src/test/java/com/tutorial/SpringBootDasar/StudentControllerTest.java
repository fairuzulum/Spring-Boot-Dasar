package com.tutorial.SpringBootDasar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.SpringBootDasar.constant.APIUrl;
import com.tutorial.SpringBootDasar.controller.StudentController;
import com.tutorial.SpringBootDasar.entity.Student;
import com.tutorial.SpringBootDasar.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateNewStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");

        when(studentService.create(any(Student.class))).thenReturn(student);

        mockMvc.perform(post(APIUrl.STUDENT_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()));
    }

    @Test
    void testGetById() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");

        when(studentService.getById(anyLong())).thenReturn(Optional.of(student));

        mockMvc.perform(get(APIUrl.STUDENT_API + "/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()));
    }

    @Test
    void testUpdateStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");

        when(studentService.update(any(Student.class))).thenReturn(student);

        mockMvc.perform(put(APIUrl.STUDENT_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()));
    }

    @Test
    void testGetAll() throws Exception {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Doe");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Doe");

        when(studentService.getAll()).thenReturn(Arrays.asList(student1, student2));

        mockMvc.perform(get(APIUrl.STUDENT_API))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(student1.getId()))
                .andExpect(jsonPath("$[0].name").value(student1.getName()))
                .andExpect(jsonPath("$[1].id").value(student2.getId()))
                .andExpect(jsonPath("$[1].name").value(student2.getName()));
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete(APIUrl.STUDENT_API + "/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete Success"));
    }
}
