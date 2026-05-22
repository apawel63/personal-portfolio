package com.personalprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.personalprojects.portfolio.model.WorkExperience;

import java.util.List;
import java.util.Optional;
 
@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {
 
    // Technologies are excluded from this join to avoid a Cartesian product (tasks × technologies)
    // that duplicates List<tasks> entries. Technologies lazy-load via @BatchSize on the field.
    @Query("SELECT DISTINCT w FROM WorkExperience w LEFT JOIN FETCH w.tasks ORDER BY w.startDate DESC")
    List<WorkExperience> findAllWithTasks();

    @Query("SELECT DISTINCT w FROM WorkExperience w LEFT JOIN FETCH w.tasks WHERE w.id = :id")
    Optional<WorkExperience> findByIdWithTasks(Integer id);
}
