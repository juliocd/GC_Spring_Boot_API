package com.seis739.gourmetcompass.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seis739.gourmetcompass.model.UserSession;

@Repository
public interface UserSessionReposritory extends CrudRepository<UserSession, Integer> {
    Optional<UserSession> findByToken(String token);
}
