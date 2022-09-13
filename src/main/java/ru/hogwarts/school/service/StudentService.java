package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {

        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        if (studentRepository.findById(student.getId()).isPresent()) {
            Student foundStudent = studentRepository.findById(student.getId()).get();
            foundStudent.setName(student.getName());
            foundStudent.setAge(student.getAge());
            return studentRepository.save(foundStudent);
        }
        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentByAge(Integer age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getStudentByAgeRange(Integer min, Integer max) {
        return studentRepository.findByAgeBetween(min, max);
    }


    public Faculty findStudentFaculty(Long id) {
        if (findStudent(id) != null) {
            return findStudent(id).getFaculty();
        }
        return null;
    }
}
