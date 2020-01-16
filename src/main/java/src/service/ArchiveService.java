package src.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.domain.Archive;
import src.repository.ArchiveRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ArchiveService {

    @Autowired
    private ArchiveRepository archiveRepository;

    public List<Archive> getAllArchives(){
        return archiveRepository.findAll();
    }

    public void addArchive(Archive archive){
        archiveRepository.save(archive);
        archiveRepository.flush();
    }

}
