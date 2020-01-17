package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.domain.SubjectTeacher;
import src.repository.SubjectTeacherRepository;

@Service
@Transactional
public class SubjectTeacherService {

    @Autowired
    private SubjectTeacherRepository subjectTeacherRepository;

    public SubjectTeacher save(SubjectTeacher subjectTeacher) {
        return this.subjectTeacherRepository.saveAndFlush(subjectTeacher);
    }
}
