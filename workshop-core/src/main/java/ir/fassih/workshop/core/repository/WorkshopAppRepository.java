package ir.fassih.workshop.core.repository;

import ir.fassih.workshop.core.entity.WorkshopAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopAppRepository extends JpaRepository<WorkshopAppEntity, Long> {

    WorkshopAppEntity findOneByTitle(String title);

}
