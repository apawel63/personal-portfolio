package com.personalprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.personalprojects.portfolio.model.WorkExperience;

import java.util.List;
import java.util.Optional;
 
@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {
 
    // Fetch all records with tasks in a single query (avoids N+1)
    // Note: task order is handled by @OrderBy on the entity — don't repeat it here
    @Query("SELECT w FROM WorkExperience w LEFT JOIN FETCH w.tasks ORDER BY w.startDate DESC")
    List<WorkExperience> findAllWithTasks();
 
    // Single record with tasks
    @Query("SELECT w FROM WorkExperience w LEFT JOIN FETCH w.tasks WHERE w.id = :id")
    Optional<WorkExperience> findByIdWithTasks(Integer id);
}
