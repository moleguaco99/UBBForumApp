package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.domain.Teacher;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByFirstName(String firstName);
    Optional<Teacher> findByLastName(String lastName);
    Optional<Teacher> findByRank(String rank);
    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<Teacher> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
}
