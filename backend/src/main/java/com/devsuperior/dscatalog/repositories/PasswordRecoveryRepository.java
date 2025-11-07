package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.PasswordRecovery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PasswordRecoveryRepository extends JpaRepository<PasswordRecovery, Long> {

    @Query("SELECT obj FROM PasswordRecovery obj WHERE obj.token = :token AND obj.expiration > :now")
    List<PasswordRecovery> searchValidTokens(String token, Instant now);
}