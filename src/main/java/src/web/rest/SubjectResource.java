package src.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import src.domain.Subject;
import src.domain.SubjectTeacher;
import src.domain.Teacher;
import src.service.SubjectService;
import src.service.SubjectTeacherService;
import src.service.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class SubjectResource {

    private final Logger log = LoggerFactory.getLogger(Subject.class);

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectTeacherService subjectTeacherService;

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
        subject4.setTitle("Baze de date");
        subject4.setDescription("Baze de date");
        subject4.setSemester(3);
        subject4.setSection("Informatica");
        subject4.setLanguage("Romana");
        subject4.setType("Obligatorie");

        Subject subject5 = new Subject();
        subject5.setTitle("Sisteme de gestiune a bazelor de date");
        subject5.setDescription("Sisteme de gestiune a bazelor de date");
        subject5.setSemester(4);
        subject5.setSection("Informatica");
        subject5.setLanguage("Romana");
        subject5.setType("Obligatorie");

        Subject subject6 = new Subject();
        subject6.setTitle("Programare pentru dispozitive mobile");
        subject6.setDescription("Programare pentru dispozitive mobile");
        subject6.setSemester(5);
        subject6.setSection("Informatica");
        subject6.setLanguage("Romana");
        subject6.setType("Obligatorie");

        Subject subject7 = new Subject();
        subject7.setTitle("Limbaje formale si tehnici de compilare");
        subject7.setDescription("Limbaje formale si tehnici de compilare");
        subject7.setSemester(5);
        subject7.setSection("Informatica");
        subject7.setLanguage("Romana");
        subject7.setType("Obligatorie");

        Subject subject8 = new Subject();
        subject8.setTitle("Programare paralela si distribuita");
        subject8.setDescription("Programare paralela si distribuita");
        subject8.setSemester(5);
        subject8.setSection("Informatica");
        subject8.setLanguage("Romana");
        subject8.setType("Obligatorie");

        subjectList.add(subject1);
        subjectList.add(subject2);
        subjectList.add(subject3);
        subjectList.add(subject4);
        subjectList.add(subject5);
        subjectList.add(subject6);
        subjectList.add(subject7);
        subjectList.add(subject8);
        List<Subject> subjectListReturned = subjectService.saveAll(subjectList);

        return new ResponseEntity<>(subjectListReturned, HttpStatus.CREATED);
    }

    @PostMapping("/create-some-subject-teachers")
    @Transactional
    public ResponseEntity<?> createSomeSubjectTeachers() {
        List<SubjectTeacher> subjectTeacherList = new ArrayList<>();

        Optional<Subject> optionalSubject1 = subjectService.findByTitleAndSemesterAndSectionAndLanguage(
            "Arhitectura sistemelor de calcul", 1, "Informatica", "Romana");
        Optional<Teacher> optionalTeacher1 = teacherService.findByFirstNameAndLastNameAndEmail(
            "Alexandru", "Vancea", "vancea@");
        SubjectTeacher subjectTeacher1 = new SubjectTeacher();
        subjectTeacher1.setSubject(optionalSubject1.get());
        subjectTeacher1.setTeacher(optionalTeacher1.get());

        Optional<Subject> optionalSubject2 = subjectService.findByTitleAndSemesterAndSectionAndLanguage(
            "Logica computationala", 1, "Informatica", "Romana");
        Optional<Teacher> optionalTeacher2 = teacherService.findByFirstNameAndLastNameAndEmail(
            "Andreea Diana", "Pop", "mihis@");
        SubjectTeacher subjectTeacher2 = new SubjectTeacher();
        subjectTeacher2.setSubject(optionalSubject2.get());
        subjectTeacher2.setTeacher(optionalTeacher2.get());

        Optional<Subject> optionalSubject3 = subjectService.findByTitleAndSemesterAndSectionAndLanguage(
            "Programare orientata obiect", 2, "Informatica", "Romana");
        Optional<Teacher> optionalTeacher3 = teacherService.findByFirstNameAndLastNameAndEmail(
            "Istvan", "Czibula", "istvanc@");
        SubjectTeacher subjectTeacher3 = new SubjectTeacher();
        subjectTeacher3.setSubject(optionalSubject3.get());
        subjectTeacher3.setTeacher(optionalTeacher3.get());

        Optional<Subject> optionalSubject4 = subjectService.findByTitleAndSemesterAndSectionAndLanguage(
            "Baze de date", 3, "Informatica", "Romana");
        Optional<Teacher> optionalTeacher4 = teacherService.findByFirstNameAndLastNameAndEmail(
            "Dan", "Suciu", "dsuciu@");
        SubjectTeacher subjectTeacher4 = new SubjectTeacher();
        subjectTeacher4.setSubject(optionalSubject4.get());
        subjectTeacher4.setTeacher(optionalTeacher4.get());

        Optional<Subject> optionalSubject5 = subjectService.findByTitleAndSemesterAndSectionAndLanguage(
            "Sisteme de gestiune a bazelor de date", 4, "Informatica", "Romana");
        SubjectTeacher subjectTeacher5 = new SubjectTeacher();
        subjectTeacher5.setSubject(optionalSubject5.get());
        subjectTeacher5.setTeacher(optionalTeacher4.get());

        Optional<Subject> optionalSubject6 = subjectService.findByTitleAndSemesterAndSectionAndLanguage(
            "Programare pentru dispozitive mobile", 5, "Informatica", "Romana");
        Optional<Teacher> optionalTeacher5 = teacherService.findByFirstNameAndLastNameAndEmail(
            "Ioan", "Lazar", "ilazar@");
        SubjectTeacher subjectTeacher6 = new SubjectTeacher();
        subjectTeacher6.setSubject(optionalSubject6.get());
        subjectTeacher6.setTeacher(optionalTeacher5.get());

        Optional<Subject> optionalSubject7 = subjectService.findByTitleAndSemesterAndSectionAndLanguage(
            "Limbaje formale si tehnici de compilare", 5, "Informatica", "Romana");
        Optional<Teacher> optionalTeacher6 = teacherService.findByFirstNameAndLastNameAndEmail(
            "Dana", "Lupsa", "dana@");
        SubjectTeacher subjectTeacher7 = new SubjectTeacher();
        subjectTeacher7.setSubject(optionalSubject7.get());
        subjectTeacher7.setTeacher(optionalTeacher6.get());

        Optional<Subject> optionalSubject8 = subjectService.findByTitleAndSemesterAndSectionAndLanguage(
            "Programare paralela si distribuita", 5, "Informatica", "Romana");
        Optional<Teacher> optionalTeacher7 = teacherService.findByFirstNameAndLastNameAndEmail(
            "Virginia", "Niculescu", "vniculescu@");
        SubjectTeacher subjectTeacher8 = new SubjectTeacher();
        subjectTeacher8.setSubject(optionalSubject8.get());
        subjectTeacher8.setTeacher(optionalTeacher7.get());

        subjectTeacherList.add(subjectTeacher1);
        subjectTeacherList.add(subjectTeacher2);
        subjectTeacherList.add(subjectTeacher3);
        subjectTeacherList.add(subjectTeacher4);
        subjectTeacherList.add(subjectTeacher5);
        subjectTeacherList.add(subjectTeacher6);
        subjectTeacherList.add(subjectTeacher7);
        subjectTeacherList.add(subjectTeacher8);

        subjectTeacherService.save(subjectTeacher1);
        subjectTeacherService.save(subjectTeacher2);
        subjectTeacherService.save(subjectTeacher3);
        subjectTeacherService.save(subjectTeacher4);
        subjectTeacherService.save(subjectTeacher5);
        subjectTeacherService.save(subjectTeacher6);
        subjectTeacherService.save(subjectTeacher7);
        subjectTeacherService.save(subjectTeacher8);

        return new ResponseEntity<>(subjectTeacherList, HttpStatus.CREATED);
    }
}
