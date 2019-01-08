package ir.fassih.workshop.core.repository;

import ir.fassih.workshop.core.entity.RsaKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsaKeyRepository extends JpaRepository<RsaKeyEntity, Long> {

    RsaKeyEntity findOneByOrderByIdDesc();

}
