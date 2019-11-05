package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
