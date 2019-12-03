package src.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.domain.Subject;
import src.domain.Teacher;
import src.repository.SubjectRepository;
import src.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/ourApi")
public class SubjectResource {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> findAll() {
        return new ResponseEntity<>(subjectRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/subject/{idS}/teachers")
    public ResponseEntity<List<Teacher>> findAllTeachers(@Param("idS") Long idSubject) {
        try {
            List<Teacher> teachers = subjectService.findAllTeachersForSubject(idSubject);
            return new ResponseEntity<>(teachers, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
