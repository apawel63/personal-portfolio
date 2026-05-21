package com.personalprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalprojects.portfolio.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
