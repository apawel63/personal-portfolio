package com.personalprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalprojects.portfolio.model.About;

public interface AboutRepository extends JpaRepository<About, Integer> {
}
