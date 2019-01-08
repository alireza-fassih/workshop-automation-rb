package ir.fassih.workshop.usermanagement.repository;

import ir.fassih.workshop.core.baseentity.WorkshopKey;
import ir.fassih.workshop.usermanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, WorkshopKey> {


    Optional<UserEntity> findOneByIdAppIdAndUsername(Long appId, String username);

}
