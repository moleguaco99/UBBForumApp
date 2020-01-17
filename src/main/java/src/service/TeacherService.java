package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.domain.Subject;
import src.domain.SubjectTeacher;
import src.domain.Teacher;
import src.repository.SubjectTeacherRepository;
import src.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectTeacherRepository subjectTeacherRepository;

    public List<Teacher> saveAll(List<Teacher> teacherListReceived) {
        List<Teacher> teacherList = new ArrayList<>();
        teacherListReceived.forEach(teacher -> {
            Teacher teacherSaved = teacherRepository.saveAndFlush(teacher);
            teacherList.add(teacherSaved);
        });
        return teacherList;
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher findOne(Long idTeacher) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(idTeacher);
        return optionalTeacher.orElse(null);
    }

    public List<Subject> findAllSubjectsForTeacher(Long idTeacher) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(idTeacher);
        if (optionalTeacher.isPresent()) {
            List<SubjectTeacher> subjectTeacherList = subjectTeacherRepository.findAllByTeacher(optionalTeacher.get());
            return subjectTeacherList.stream().map(SubjectTeacher::getSubject).collect(Collectors.toList());
        } else {
            throw new RuntimeException("No existent teacher for given id.");
        }
    }

    public Optional<Teacher> findByFirstName(String firstName) {
        return teacherRepository.findByFirstName(firstName);
    }

    public Optional<Teacher> findByLastName(String lastName) {
        return teacherRepository.findByLastName(lastName);
    }

    public Optional<Teacher> findByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    public Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName) {
        return teacherRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Optional<Teacher> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email) {
        return teacherRepository.findByFirstNameAndLastNameAndEmail(firstName, lastName, email);
    }
}
