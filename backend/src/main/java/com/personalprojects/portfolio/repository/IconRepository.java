package com.personalprojects.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalprojects.portfolio.model.Icon;

public interface IconRepository extends JpaRepository<Icon, Integer> {
}
