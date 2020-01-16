package src.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.domain.Archive;
import src.service.ArchiveService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class ArchiveResource {

    @Autowired
    private ArchiveService archiveService;

    @GetMapping("/archives/")
    public ResponseEntity<List<Archive>> getArchiveLinks(){
        return new ResponseEntity<>(archiveService.getAllArchives().stream().sorted(Comparator.comparing(Archive::getIdArchive)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/archiveUpload")
    @Transactional
    public ResponseEntity uploadArchive(@RequestBody Archive archive){
        archive.setTimestamp(Instant.now());
        archiveService.addArchive(archive);
        return new ResponseEntity(HttpStatus.OK);
    }
}
