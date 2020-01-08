package src.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.domain.Subject;
import src.domain.SubjectTeacher;
import src.domain.Teacher;
import src.repository.SubjectRepository;
import src.repository.SubjectTeacherRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectTeacherRepository subjectTeacherRepository;
    public Optional<Subject> getSubject(long id){
        return subjectRepository.findById(id);
    }
    public List<Teacher> getTeachersForSubject(long id) {
        return subjectTeacherRepository.findAll().stream().filter(a -> a.getSubject().getIdSubject()==id)
            .map(SubjectTeacher::getTeacher).collect(Collectors.toList());

    }
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
