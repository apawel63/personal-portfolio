package com.personalprojects.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.personalprojects.portfolio.model.ProjectDto;
import com.personalprojects.portfolio.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // GET /api/projects
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAll() {
        return ResponseEntity.ok(projectService.getAllWithTechnologiesAndLinks());
    }

    // GET /api/projects/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.getByIdWithTechnologiesAndLinks(id));
    }
}
