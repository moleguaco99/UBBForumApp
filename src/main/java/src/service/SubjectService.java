package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.domain.Subject;
import src.domain.SubjectTeacher;
import src.domain.Teacher;
import src.repository.SubjectRepository;
import src.repository.SubjectTeacherRepository;

import java.util.ArrayList;
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

    public Subject save(Subject subject) {
        return subjectRepository.saveAndFlush(subject);
    }

    public List<Subject> saveAll(List<Subject> subjectListReceived) {
        List<Subject> subjectList = new ArrayList<>();
        subjectListReceived.forEach(subject -> {
            Subject subjectSaved = subjectRepository.saveAndFlush(subject);
            subjectList.add(subjectSaved);
        });
        return subjectList;
    }

    public Optional<Subject> findByTitleAndSemesterAndSectionAndLanguage(String title, Integer semester, String section, String language) {
        return subjectRepository.findByTitleAndSemesterAndSectionAndLanguage(title, semester, section, language);
    }

    public Optional<Subject> getSubject(long id) {
        return subjectRepository.findById(id);
    }

    public List<Teacher> getTeachersForSubject(long id) {
        return subjectTeacherRepository
            .findAll()
            .stream()
            .filter(a -> a.getSubject().getIdSubject() == id)
            .map(SubjectTeacher::getTeacher)
            .collect(Collectors.toList());
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
