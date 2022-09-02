package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private Long facultyId = 0L;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++facultyId);
        faculties.put(facultyId, faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(Long id) {
        return faculties.remove(id);
    }

    public List<Faculty> getFacultiesList() {
        return new ArrayList<>(faculties.values());
    }

    public List<Faculty> getFacultyByColor(String color) {
        List<Faculty> facultiesByColor = getFacultiesList();

        return facultiesByColor.stream()
                .filter(s -> s.getColor().equals(color))
                .collect(Collectors.toList());
    }
}




