package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.Archive;

public interface ArchiveRepository extends JpaRepository<Archive, Long> {
}
