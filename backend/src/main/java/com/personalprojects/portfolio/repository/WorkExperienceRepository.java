package com.personalprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.personalprojects.portfolio.model.WorkExperience;

import java.util.List;
import java.util.Optional;
 
@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {
 
    // DISTINCT avoids duplicate root rows from joining two collections.
    // List<tasks> + Set<technologies> avoids Hibernate MultipleBagFetchException.
    @Query("SELECT DISTINCT w FROM WorkExperience w LEFT JOIN FETCH w.tasks LEFT JOIN FETCH w.technologies t LEFT JOIN FETCH t.icon ORDER BY w.startDate DESC")
    List<WorkExperience> findAllWithTasks();

    @Query("SELECT DISTINCT w FROM WorkExperience w LEFT JOIN FETCH w.tasks LEFT JOIN FETCH w.technologies t LEFT JOIN FETCH t.icon WHERE w.id = :id")
    Optional<WorkExperience> findByIdWithTasks(Integer id);
}
