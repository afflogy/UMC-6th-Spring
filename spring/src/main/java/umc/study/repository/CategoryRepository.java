package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.FoodCategory;

public interface CategoryRepository extends JpaRepository<FoodCategory, Long> {
}
