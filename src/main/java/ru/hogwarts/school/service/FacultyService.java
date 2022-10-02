package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public Faculty createFaculty(Faculty faculty) {
        logger.debug("Was invoked method for create faculty: {}", faculty);
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        logger.debug("Was invoked method for find faculty: {}", id);
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.debug("Was invoked method for edit faculty: {}", faculty);

        Faculty foundFaculty = findFaculty(faculty.getId());
        foundFaculty.setName(faculty.getName());
        foundFaculty.setColor(faculty.getColor());
        return facultyRepository.save(foundFaculty);
    }

    public void deleteFaculty(Long id) {
        logger.debug("Was invoked method for delete faculty: {}", id);
        facultyRepository.deleteById(id);
    }

    public Faculty getFacultyByNameOrColor(String nameOrColor) {
        logger.debug("Was invoked method for get faculty by name or color: {}", nameOrColor);
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    public Collection<Student> findByFacultyId(Long id) {
        logger.debug("Was invoked method for find students of faculty: {}", id);
        Faculty faculty = findFaculty(id);
        if (faculty != null) {
            logger.warn("There's no faculty with id = " + id);
            return faculty.getStudents();
        }
        return null;
    }
}




