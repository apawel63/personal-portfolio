package com.personalprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalprojects.portfolio.model.Education;

public interface EducationRepository extends JpaRepository<Education, Integer> {
}
