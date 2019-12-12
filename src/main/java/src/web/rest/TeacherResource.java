package src.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.domain.Subject;
import src.domain.Teacher;
import src.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/ourApi")
public class TeacherResource {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> findAll() {
        return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/teacher/{idT}/subjects")
    public ResponseEntity<List<Subject>> findAllSubjects(@PathVariable("idT") Long idTeacher) {
        try {
            List<Subject> subjects = teacherService.findAllSubjectsForTeacher(idTeacher);
            return new ResponseEntity<>(subjects, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
