package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceTest {
    private StudentService studentService;
    private Student expected;

    private Student actual;


    @BeforeEach
    void setUp() {
        studentService = new StudentService();
        expected = new Student(1L, "Гарри Поттер", 20);
        actual = studentService.createStudent(expected);
    }

    @Test
    void shouldReturnStudentWhenCallCreateStudentMethod() {
        assertEquals(expected, studentService.createStudent(expected));
    }

    @Test
    void shouldReturnStudentWhenCallFindStudentMethod() {
        assertEquals(actual, studentService.findStudent(1L));
    }

    @Test
    void shouldReturnStudentWhenCallEditStudentMethod() {
        assertEquals(actual, studentService.editStudent(expected));
    }

    @Test
    void shouldReturnStudentWhenCallDeleteStudentMethod() {
        assertEquals(actual, studentService.deleteStudent(1L));
    }

    @Test
    void shouldReturnListOfStudentsWhenCallGetStudentsListMethod() {
        List<Student> expected = studentService.getStudentsList();

        List<Student> actual = new ArrayList<>();
        actual.add(new Student(1L, "Гарри Поттер", 20));

        assertEquals(expected, actual);
    }

    @Test
    void ShouldReturnListOfStudentsFilteredByAgeWhenCallGetStudentByAgeMethod() {
        List<Student> expected = studentService.getStudentByAge(20);

        List<Student> actual = new ArrayList<>();
        actual.add(new Student(1L, "Гарри Поттер", 20));

        assertEquals(expected, actual);
    }
}