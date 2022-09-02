package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private Long studentId = 0L;

    public Student createStudent(Student student) {
        student.setId(++studentId);
        students.put(studentId, student);
        return student;
    }

    public Student findStudent(Long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(Long id) {
        return students.remove(id);
    }

    public List<Student> getStudentsList() {
        return new ArrayList<>(students.values());
    }

    public List<Student> getStudentByAge(int age) {
        List<Student> studentsByAge = getStudentsList();

        return studentsByAge.stream()
                .filter(s -> Objects.equals(s.getAge(), age))
                .collect(Collectors.toList());
    }
}
