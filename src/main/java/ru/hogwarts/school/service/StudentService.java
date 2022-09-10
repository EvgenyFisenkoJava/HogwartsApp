package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {

        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
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


    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Faculty findStudentFaculty(Long id) {
        return studentRepository.findById(id).get().getFaculty();
    }

}
