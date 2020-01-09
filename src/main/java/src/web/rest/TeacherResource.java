package src.web.rest;

import com.google.common.io.Files;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.domain.Subject;
import src.domain.Teacher;
import src.security.AuthoritiesConstants;
import src.service.TeacherService;

import java.util.List;

import java.io.*;

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
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<Teacher>> findAll() {
        return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/teacher/{idT}/subjects")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<Subject>> findAllSubjects(@PathVariable("idT") Long idTeacher) {
        try {
            List<Subject> subjects = teacherService.findAllSubjectsForTeacher(idTeacher);
            return new ResponseEntity<>(subjects, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}




