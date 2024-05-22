package com.tutorial.SpringBootDasar.controller;


import com.tutorial.SpringBootDasar.constant.APIUrl;
import com.tutorial.SpringBootDasar.entity.Student;
import com.tutorial.SpringBootDasar.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.STUDENT_API)
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createNewStudent(@RequestBody Student request){
        Student newStudent = studentService.create(request);
        return ResponseEntity.ok().body(newStudent);
    }

    @GetMapping(APIUrl.PATH_VAR_ID)
    public ResponseEntity<Optional<Student>> getById(@PathVariable Long id){
        Optional<Student> getById = studentService.getById(id);
        return ResponseEntity.ok(getById);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        Student update = studentService.update(student);
        return ResponseEntity.ok(update);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        List<Student> all = studentService.getAll();
        return ResponseEntity.ok().body(all);
    }

    @DeleteMapping(APIUrl.PATH_VAR_ID)
    public ResponseEntity<String> deletById(@PathVariable Long id){
         studentService.delete(id);
         return ResponseEntity.ok().body("Delete Success");
    }
}
