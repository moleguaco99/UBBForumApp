package src.web.rest;

import com.google.common.io.Files;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import src.security.AuthoritiesConstants;


import java.io.*;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class TeacherResource {
    final String TEACHER_PHOTOS_PATH = "./src/main/java/src/web/rest/resources/teacherPhotos/";
    public TeacherResource(){}
    @GetMapping("/teachers/photo/{path}")
    public ResponseEntity<byte[]> getTeacherPhoto(@PathVariable String path) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        if(path.contains("..")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        File photo = new File(TEACHER_PHOTOS_PATH + path);
        InputStream in = Files.asByteSource(photo).openStream();
        byte[] media = IOUtils.toByteArray(in);
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
    }

}
