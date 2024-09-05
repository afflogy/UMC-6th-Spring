package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
