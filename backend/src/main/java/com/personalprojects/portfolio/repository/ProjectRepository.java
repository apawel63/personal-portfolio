package com.personalprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.personalprojects.portfolio.model.Project;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("SELECT DISTINCT p FROM Project p " +
           "LEFT JOIN FETCH p.technologies t " +
           "LEFT JOIN FETCH t.icon " +
           "ORDER BY p.id")
    List<Project> findAllWithTechnologies();

    @Query("SELECT DISTINCT p FROM Project p " +
           "LEFT JOIN FETCH p.links l " +
           "LEFT JOIN FETCH l.icon " +
           "ORDER BY p.id")
    List<Project> findAllWithLinks();

    @Query("SELECT p FROM Project p " +
           "LEFT JOIN FETCH p.technologies t " +
           "LEFT JOIN FETCH t.icon " +
           "WHERE p.id = :id")
    Optional<Project> findByIdWithTechnologies(Integer id);

    @Query("SELECT p FROM Project p " +
           "LEFT JOIN FETCH p.links l " +
           "LEFT JOIN FETCH l.icon " +
           "WHERE p.id = :id")
    Optional<Project> findByIdWithLinks(Integer id);
}
