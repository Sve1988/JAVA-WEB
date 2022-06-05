package bg.softuni.SpringIntro.Cats.repository;

import bg.softuni.SpringIntro.Cats.model.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
}
