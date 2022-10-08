package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(Integer age);

    Collection<Student> findByAgeBetween(Integer min, Integer max);


    @Query(value = "SELECT AVG(age) as age FROM student ", nativeQuery = true)
    Integer getAverageAge();

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getStudentsQty();

    @Query(value = "SELECT * FROM student ORDER BY id DESC limit 5", nativeQuery = true)
    Collection<Student> getLastFiveStudents();

}
