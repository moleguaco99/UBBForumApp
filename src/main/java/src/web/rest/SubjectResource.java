package src.web.rest;


import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    public SubjectResource(){}
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Subject> getSubject(@PathVariable long id){
        log.debug("REST request to get subject with ID " + id);
        return ResponseUtil.wrapOrNotFound(subjectService.getSubject(id));
    }
    @GetMapping("/subjects/teachers/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<Teacher>> getAnswersToQuestion(@PathVariable long id){
        return new ResponseEntity<>(subjectService.getTeachersForSubject(id), HttpStatus.OK);
    }
}
