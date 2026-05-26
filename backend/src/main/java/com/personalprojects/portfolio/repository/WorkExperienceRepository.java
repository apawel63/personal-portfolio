package com.personalprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.personalprojects.portfolio.model.WorkExperience;

import java.util.List;
import java.util.Optional;
 
@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {
 
    // Tasks and technologies are fetched in separate queries to avoid a Cartesian product.
    @Query("SELECT DISTINCT w FROM WorkExperience w LEFT JOIN FETCH w.tasks ORDER BY w.startDate DESC")
    List<WorkExperience> findAllWithTasks();

    @Query("SELECT DISTINCT w FROM WorkExperience w LEFT JOIN FETCH w.tasks WHERE w.id = :id")
    Optional<WorkExperience> findByIdWithTasks(Integer id);

    @Query("SELECT DISTINCT w FROM WorkExperience w " +
           "LEFT JOIN FETCH w.technologies wet " +
           "LEFT JOIN FETCH wet.technology t " +
           "LEFT JOIN FETCH t.icon " +
           "ORDER BY w.startDate DESC")
    List<WorkExperience> findAllWithTechnologies();

    @Query("SELECT w FROM WorkExperience w " +
           "LEFT JOIN FETCH w.technologies wet " +
           "LEFT JOIN FETCH wet.technology t " +
           "LEFT JOIN FETCH t.icon " +
           "WHERE w.id = :id")
    Optional<WorkExperience> findByIdWithTechnologies(Integer id);
}
