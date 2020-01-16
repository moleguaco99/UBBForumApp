package src.web.rest;


import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import src.domain.StringWrapper;
import src.domain.Subject;
import src.domain.Teacher;
import src.security.AuthoritiesConstants;
import src.service.SubjectService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class SubjectResource {
    private final Logger log = LoggerFactory.getLogger(Subject.class);
    @Autowired
    private SubjectService subjectService;

    public SubjectResource() {
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getSubjects() {
        try {
            return new ResponseEntity<>(subjectService.getAllSubjects(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable long id) {
        log.debug("REST request to get subject with ID " + id);
        try {
            return ResponseUtil.wrapOrNotFound(subjectService.getSubject(id));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/subjects/{idS}/teachers")
    public ResponseEntity<List<Teacher>> getTeachersForSubject(@PathVariable long idS) {
        try {
            return new ResponseEntity<>(subjectService.getTeachersForSubject(idS), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/subjectsTeacher/{idT}")
    public ResponseEntity<StringWrapper> getDomainsOfInterest(@PathVariable long idT) {
        try {
            return new ResponseEntity<>(new StringWrapper(subjectService.getSubjectsForTeacher(idT)), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
