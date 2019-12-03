package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
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

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public List<Teacher> findAllTeachersForSubject(Long idSubject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(idSubject);
        if (optionalSubject.isPresent()) {
            List<SubjectTeacher> subjectTeacherList = subjectTeacherRepository.findAllBySubject(optionalSubject.get());
            return subjectTeacherList.stream().map(SubjectTeacher::getTeacher).collect(Collectors.toList());
        } else {
            throw new RuntimeException("Not existent subject for given id.");
        }
    }
}
