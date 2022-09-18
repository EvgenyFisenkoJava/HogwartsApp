package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HogwartsAppApplicationTests {
    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    public void testFaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .contains("WebApp is working");
    }
    @Test
    public void testFindStudentByAgeRange () throws Exception {

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students?min=5&max=10", String.class))
                .contains("James");
    }

    @Test
    public void testFindStudentById () throws Exception {

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/4", String.class))
                .contains("Snoop");
    }
    @Test
    public void testFindStudentByAge () throws Exception {

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/15/student", String.class))
                .contains("Snoop");
    }

    @Test
    public void testFindFacultyOfStudent () throws Exception {

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/1/students", String.class))
                .contains("Gryffindor");
    }

    @Test
    public void testAddStudent () throws Exception {
        Student student = new Student();
        student.setName("Somebody");
        student.setAge(100);

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", student, String.class))
                .isNotNull();
    }
    @Test
    public void testEditStudent () throws Exception {

        Student editStudent = new Student();
        editStudent.setId(1L);
        editStudent.setName("Another Name");
        editStudent.setAge(40);

       this.restTemplate.put("http://localhost:" + port + "/students", editStudent, String.class);
       assertThat(editStudent.getName()).isEqualTo("Another Name");

    }

    @Test
    public void testDeleteStudent () throws Exception {
        this.restTemplate.delete("http://localhost:" + port + "/students/7", String.class);
        assertThat(studentController.getStudent(7L)).isEqualTo(ResponseEntity.notFound().build());
    }








    }
