package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")

public class StudentController {

    public final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public  ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getStudentsList());
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent (@RequestBody Student student) {
        Student changeStudent =  studentService.editStudent(student);
        if(changeStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(changeStudent);
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Student> deleteStudent (@PathVariable Long id) {
        Student student = studentService.deleteStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Student>> filteredByAge (@PathVariable int age) {
        List <Student> ageStudent = studentService.getStudentByAge(age);
        if(ageStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ageStudent);
    }
}
