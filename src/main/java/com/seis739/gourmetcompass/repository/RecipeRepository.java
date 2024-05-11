package com.seis739.gourmetcompass.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.seis739.gourmetcompass.model.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    @Query(value = "select * from recipes r where r.is_private = 0 order by created_at desc", nativeQuery = true)
    ArrayList<Recipe> findAllPublic();

    @Query(value = "select * from recipes r " 
        + "where r.is_private = 0 and (name like ?1 or description like ?1 or ingredients like ?1 or steps like ?1) " 
        + "order by created_at desc", nativeQuery = true)
    ArrayList<Recipe> filterPublicByQuery(String query);

    @Query(value = "select * from recipes r " 
        + "where r.user_id = ?1 and (name like ?2 or description like ?2 or ingredients like ?2 or steps like ?2) " 
        + "order by created_at desc", nativeQuery = true)
    ArrayList<Recipe> filterByUserIdAndQuery(Integer userId, String query);

    Optional<Recipe> findByIdAndUserId(Integer id, Integer userId);
    Boolean existsByIdAndUserId(Integer id, Integer userId);

    @Modifying
    @Transactional
    void deleteByIdAndUserId(Integer id, Integer userId);
}
