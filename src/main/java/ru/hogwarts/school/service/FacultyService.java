package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {

        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {

        Faculty foundFaculty = findFaculty(faculty.getId());
        foundFaculty.setName(faculty.getName());
        foundFaculty.setColor(faculty.getColor());
        return facultyRepository.save(foundFaculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Faculty getFacultyByNameOrColor(String nameOrColor) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    public Collection<Student> findByFacultyId(Long id) {
        Faculty faculty = findFaculty(id);
        if (faculty != null) {
            return faculty.getStudents();
        }
        return null;
    }
}




