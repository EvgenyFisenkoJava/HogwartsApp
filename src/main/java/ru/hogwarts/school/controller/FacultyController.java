package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")

public class FacultyController {

    public final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getFacultiesList());
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty changeFaculty = facultyService.editFaculty(faculty);
        if (changeFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(changeFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        Faculty removeFaculty = facultyService.deleteFaculty(id);
        if (removeFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(removeFaculty);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Faculty>> filteredByColor(@PathVariable String color) {
        List<Faculty> colorFaculty = facultyService.getFacultyByColor(color);
        if (colorFaculty.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(colorFaculty);
    }
}
