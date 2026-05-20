package com.personalprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.personalprojects.portfolio.model.Project;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    // Fetch all projects with technologies and links in a single query (avoids N+1)
    @Query("SELECT DISTINCT p FROM Project p " +
           "LEFT JOIN FETCH p.technologies t " +
           "LEFT JOIN FETCH t.icon " +
           "LEFT JOIN FETCH p.links l " +
           "LEFT JOIN FETCH l.icon " +
           "ORDER BY p.id")
    List<Project> findAllWithTechnologiesAndLinks();

    // Single project with technologies and links
    @Query("SELECT p FROM Project p " +
           "LEFT JOIN FETCH p.technologies t " +
           "LEFT JOIN FETCH t.icon " +
           "LEFT JOIN FETCH p.links l " +
           "LEFT JOIN FETCH l.icon " +
           "WHERE p.id = :id")
    Optional<Project> findByIdWithTechnologiesAndLinks(Integer id);
}
