package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/students")

public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {

        if (studentService.editStudent(student) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.editStudent(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{age}/student")
    public ResponseEntity<Collection<Student>> findStudents(@PathVariable Integer age) {
        return ResponseEntity.ok(studentService.getStudentByAge(age));
    }

    @GetMapping()
    public ResponseEntity<Collection<Student>> findByAgeRange(@RequestParam(required = false) Integer min,
                                                              @RequestParam(required = false) Integer max) {
        return ResponseEntity.ok(studentService.getStudentByAgeRange(min, max));
    }

    @GetMapping("{id}/students")
    public ResponseEntity<Faculty> findFacultyOfStudent(@PathVariable Long id) {
        if (studentService.findStudentFaculty(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.findStudentFaculty(id));
    }

}
