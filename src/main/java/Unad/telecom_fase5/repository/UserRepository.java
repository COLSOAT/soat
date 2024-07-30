package Unad.telecom_fase5.repository;

import Unad.telecom_fase5.entity.consultaVerifik.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
