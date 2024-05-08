package com.seis739.gourmetcompass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seis739.gourmetcompass.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "select * from users u where u.email = ?1 and u.password = ?2 and u.deleted_at is null", nativeQuery = true)
    Optional<User> findByEmailAndPassword(String email, String password);

    @Query(value = "select * from users u where u.email = ?1 and u.deleted_at is null", nativeQuery = true)
    Optional<User> isUserActiveByEmail(String email);

    @Query(value = "select * from users u where u.id = ?1 and u.deleted_at is null", nativeQuery = true)
    Optional<User> findById(String id);
}
