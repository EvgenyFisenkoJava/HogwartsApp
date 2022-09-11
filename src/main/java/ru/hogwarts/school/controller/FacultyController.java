package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")

public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getFacultiesList() {
        return ResponseEntity.ok(facultyService.getFacultyList());
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
        changeFaculty.setName(faculty.getName());
        changeFaculty.setColor(faculty.getColor());
        return ResponseEntity.ok(changeFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{nameOrColor}/faculty")
    public ResponseEntity<Faculty> findFaculties(@PathVariable String nameOrColor) {
        Faculty findFaculty = facultyService.getFacultyByNameOrColor(nameOrColor);
        if (nameOrColor == null) {
            return ResponseEntity.notFound().build();
        }
        else if (nameOrColor.equals(findFaculty.getName()) || nameOrColor.equals(findFaculty.getColor())) {
            return ResponseEntity.ok(facultyService.getFacultyByNameOrColor(nameOrColor));
        }
        return ResponseEntity.ok(findFaculty);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Collection<Student>> findStudentsOfFaculty(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.findByFacultyId(id));
    }

}
