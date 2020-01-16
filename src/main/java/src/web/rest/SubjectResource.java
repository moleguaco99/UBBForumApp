package src.web.rest;


import io.github.jhipster.web.util.ResponseUtil;
import jdk.internal.loader.AbstractClassLoaderValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import src.domain.Subject;
import src.domain.Teacher;
import src.service.SubjectService;

import java.util.ArrayList;
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

    @PostMapping("/create-some-subjects")
    @Transactional
    public ResponseEntity<?> createSomeSubjects() {
        List<Subject> subjectList = new ArrayList<>();

        Subject subject1 = new Subject();
        subject1.setTitle("Arhitectura sistemelor de calcul");
        subject1.setDescription("Arhitectura sistemelor de calcul");
        subject1.setSemester(1);
        subject1.setSection("Informatica");
        subject1.setLanguage("Romana");
        subject1.setType("Obligatorie");

        Subject subject2 = new Subject();
        subject2.setTitle("Logica computationala");
        subject2.setDescription("Logica computationala");
        subject2.setSemester(1);
        subject2.setSection("Informatica");
        subject2.setLanguage("Romana");
        subject2.setType("Obligatorie");

        Subject subject3 = new Subject();
        subject3.setTitle("Programare orientata obiect");
        subject3.setDescription("Programare orientata obiect");
        subject3.setSemester(2);
        subject3.setSection("Informatica");
        subject3.setLanguage("Romana");
        subject3.setType("Obligatorie");

        Subject subject4 = new Subject();
        subject4.setTitle("Programare pentru dispozitive mobile");
        subject4.setDescription("Programare pentru dispozitive mobile");
        subject4.setSemester(5);
        subject4.setSection("Informatica");
        subject4.setLanguage("Romana");
        subject4.setType("Obligatorie");

        Subject subject5 = new Subject();
        subject5.setTitle("Limbaje formale si tehnici de compilare");
        subject5.setDescription("Limbaje formale si tehnici de compilare");
        subject5.setSemester(5);
        subject5.setSection("Informatica");
        subject5.setLanguage("Romana");
        subject5.setType("Obligatorie");

        Subject subject6 = new Subject();
        subject6.setTitle("Programare paralela si distribuita");
        subject6.setDescription("Programare paralela si distribuita");
        subject6.setSemester(5);
        subject6.setSection("Informatica");
        subject6.setLanguage("Romana");
        subject6.setType("Obligatorie");

        subjectList.add(subject1);
        subjectList.add(subject2);
        subjectList.add(subject3);
        subjectList.add(subject4);
        subjectList.add(subject5);
        subjectList.add(subject6);
        List<Subject> subjectListReturned = subjectService.saveAll(subjectList);

        return new ResponseEntity<>(subjectListReturned, HttpStatus.CREATED);
    }
}
