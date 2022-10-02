package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student createStudent(Student student) {
        logger.debug("Was invoked method for create student: {}", student);
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.debug("Was invoked method for find student: {}", id);
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        logger.debug("Was invoked method for edit student: {}", student);
        Student foundStudent = findStudent(student.getId());
        foundStudent.setName(student.getName());
        foundStudent.setAge(student.getAge());
        return studentRepository.save(foundStudent);
    }

    public void deleteStudent(Long id) {
        logger.debug("Was invoked method for delete student: {}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentByAge(Integer age) {
        logger.debug("Was invoked method for get student by age: {}", age);
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getStudentByAgeRange(Integer min, Integer max) {
        logger.debug("Was invoked method for get student by age range: from age {}. to age {}.", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }


    public Faculty findStudentFaculty(Long id) {
        logger.debug("Was invoked method for get faculty by student id: {}", id);
        Student student = findStudent(id);
        if (student != null) {
            logger.warn("There's no student with id = " + id);
            return student.getFaculty();
        }
        return null;
    }

    public Integer getAverageAge() {
        logger.debug("Was invoked method for get average age");
        return studentRepository.getAverageAge();
    }

    public Integer getStudentsQty() {
        logger.debug("Was invoked method for get quantity of students");
        return studentRepository.getStudentsQty();
    }

    public Collection<Student> getFiveLastStudents() {
        logger.debug("Was invoked method for get five last students");
        return studentRepository.getLastFiveStudents();
    }
}
