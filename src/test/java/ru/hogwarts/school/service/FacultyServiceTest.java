package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FacultyServiceTest {
    private FacultyService facultyService;
    private Faculty expected;
    private Faculty actual;


    @BeforeEach
    void setUp() {
        facultyService = new FacultyService();
        expected = new Faculty(1L, "Gryffindor", "white");
        actual = facultyService.createFaculty(expected);
    }

    @Test
    void shouldReturnFacultyWhenCallCreateFacultyMethod() {
        assertEquals(expected, facultyService.createFaculty(expected));
    }

    @Test
    void shouldReturnFacultyWhenCallFindeFacultyMethod() {
        assertEquals(actual, facultyService.findFaculty(1L));
    }

    @Test
    void shouldReturnFacultyWhenCallEditFacultyMethod() {
        assertEquals(actual, facultyService.editFaculty(expected));
    }

    @Test
    void shouldReturnFacultyWhenCallDeleteFacultyMethod() {
        assertEquals(actual, facultyService.deleteFaculty(1L));
    }

    @Test
    void shouldReturnListOfFacultiesWhenCallGetFacultiesListMethod() {
        List<Faculty> expected = facultyService.getFacultiesList();

        List<Faculty> actual = new ArrayList<>();
        actual.add(new Faculty(1L, "Gryffindor", "white"));

        assertEquals(expected, actual);
    }

    @Test
    void ShouldReturnListOfFacultiesFilteredByColorWhenCallGetFacultiesByColorMethod() {
        List<Faculty> expected = facultyService.getFacultyByColor("white");

        List<Faculty> actual = new ArrayList<>();
        actual.add(new Faculty(1L, "Gryffindor", "white"));

        assertEquals(expected, actual);
    }
}