package com.seis739.gourmetcompass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seis739.gourmetcompass.model.UserSession;

@Repository
public interface UserSessionRepository extends CrudRepository<UserSession, Integer> {
    Optional<UserSession> findByToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from user_sessions where user_id = ?1", nativeQuery = true)
    void deleteByUserId(int userId);
}
