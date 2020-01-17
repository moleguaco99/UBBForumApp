package src.web.rest;

import com.google.common.io.Files;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import src.domain.Subject;
import src.domain.Teacher;
import src.service.TeacherService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class TeacherResource {

    final String TEACHER_PHOTOS_PATH = "./src/main/java/src/web/rest/resources/teacherPhotos/";

    @Autowired
    private TeacherService teacherService;

    public TeacherResource() {
    }

    @GetMapping("/teachers/photo/{path}")
    public ResponseEntity<byte[]> getTeacherPhoto(@PathVariable String path) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        if (path.contains("..")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        File photo = new File(TEACHER_PHOTOS_PATH + path);
        InputStream in = Files.asByteSource(photo).openStream();
        byte[] media = IOUtils.toByteArray(in);
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
    }

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

    @GetMapping("/teachers/{idT}")
    public ResponseEntity<Teacher> findOne(@PathVariable("idT") Long idTeacher) {
        Teacher teacher = teacherService.findOne(idTeacher);
        if (Objects.nonNull(teacher)) {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-some-teachers")
    @Transactional
    public ResponseEntity<?> createSomeTeachers() {
        List<Teacher> teacherList = new ArrayList<>();

        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Alexandru");
        teacher1.setLastName("Vancea");
        teacher1.setEmail("vancea@");
        teacher1.setRank("Lector Universitar");
        teacher1.setWebPage("http://www.cs.ubbcluj.ro/~vancea/");
        teacher1.setPhotoPath("http://www.cs.ubbcluj.ro/wp-content/uploads/Vancea-Alexandru.jpg");

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Andreea Diana");
        teacher2.setLastName("Pop");
        teacher2.setEmail("mihis@");
        teacher2.setRank("Lector Universitar");
        teacher2.setWebPage("http://www.cs.ubbcluj.ro/~mihis/");
        teacher2.setPhotoPath("http://www.cs.ubbcluj.ro/wp-content/uploads/Mihis-Andreea.jpg");

        Teacher teacher3 = new Teacher();
        teacher3.setFirstName("Istvan");
        teacher3.setLastName("Czibula");
        teacher3.setEmail("istvanc@");
        teacher3.setRank("Profesor Universitar");
        teacher3.setWebPage("http://www.cs.ubbcluj.ro/~istvanc/");
        teacher3.setPhotoPath("http://www.cs.ubbcluj.ro/wp-content/uploads/Czibula-Istvan.jpg");

        Teacher teacher4 = new Teacher();
        teacher4.setFirstName("Dan");
        teacher4.setLastName("Suciu");
        teacher4.setEmail("dsuciu@");
        teacher4.setRank("Lector Universitar");
        teacher4.setWebPage("http://www.cs.ubbcluj.ro/~dsuciu");
        teacher4.setPhotoPath("http://www.cs.ubbcluj.ro/wp-content/uploads/Suciu-Dan.jpg");

        Teacher teacher5 = new Teacher();
        teacher5.setFirstName("Ioan");
        teacher5.setLastName("Lazar");
        teacher5.setEmail("ilazar@");
        teacher5.setRank("Lector Universitar");
        teacher5.setWebPage("http://www.cs.ubbcluj.ro/~ilazar/");
        teacher5.setPhotoPath("http://www.cs.ubbcluj.ro/wp-content/uploads/Lazar-Ioan.jpg");

        Teacher teacher6 = new Teacher();
        teacher6.setFirstName("Dana");
        teacher6.setLastName("Lupsa");
        teacher6.setEmail("dana@");
        teacher6.setRank("Lector Universitar");
        teacher6.setWebPage("http://www.cs.ubbcluj.ro/~dana/");
        teacher6.setPhotoPath("http://www.cs.ubbcluj.ro/wp-content/uploads/Lupsa-Dana.jpg");

        Teacher teacher7 = new Teacher();
        teacher7.setFirstName("Virginia");
        teacher7.setLastName("Niculescu");
        teacher7.setEmail("vniculescu@");
        teacher7.setRank("Conferentiar Universitar");
        teacher7.setWebPage("http://www.cs.ubbcluj.ro/~vniculescu/");
        teacher7.setPhotoPath("http://www.cs.ubbcluj.ro/wp-content/uploads/Niculescu-Virginia.jpg");

        teacherList.add(teacher1);
        teacherList.add(teacher2);
        teacherList.add(teacher3);
        teacherList.add(teacher4);
        teacherList.add(teacher5);
        teacherList.add(teacher6);
        teacherList.add(teacher7);

        List<Teacher> teacherListReturned = teacherService.saveAll(teacherList);

        return new ResponseEntity<>(teacherListReturned, HttpStatus.CREATED);
    }
}




