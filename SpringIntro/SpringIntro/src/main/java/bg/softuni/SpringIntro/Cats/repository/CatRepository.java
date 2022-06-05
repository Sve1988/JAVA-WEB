package bg.softuni.SpringIntro.Cats.repository;

import bg.softuni.SpringIntro.Cats.model.entity.CatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<CatEntity, Long> {
}
