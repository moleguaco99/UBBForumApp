package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.domain.Subject;
import src.domain.SubjectTeacher;
import src.domain.Teacher;
import src.repository.SubjectRepository;
import src.repository.SubjectTeacherRepository;

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

    public Optional<Subject> getSubject(long id) {
        return subjectRepository.findById(id);
    }

    public List<Teacher> getTeachersForSubject(long id) {
        return subjectTeacherRepository.findAll().stream().filter(a -> a.getSubject().getIdSubject() == id)
            .map(SubjectTeacher::getTeacher).collect(Collectors.toList());
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public String getSubjectsForTeacher(long idT) {
        return subjectTeacherRepository.findAll().stream().filter(s-> s.getTeacher().getIdTeacher() == idT)
            .map(SubjectTeacher::getSubject).map(Subject::getTitle).reduce((s1, s2)-> s1 + " " + s2).get();
    }
}
