package com.personalprojects.portfolio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personalprojects.portfolio.model.ProjectDto;
import com.personalprojects.portfolio.repository.ProjectRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDto> getAllWithTechnologiesAndLinks() {
        return projectRepository.findAllWithTechnologiesAndLinks()
                .stream()
                .map(ProjectDto::from)
                .toList();
    }

    public ProjectDto getByIdWithTechnologiesAndLinks(Integer id) {
        return projectRepository.findByIdWithTechnologiesAndLinks(id)
                .map(ProjectDto::from)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    // --- Exception ---

    public static class ProjectNotFoundException extends RuntimeException {
        public ProjectNotFoundException(Integer id) {
            super("Project not found with id: " + id);
        }
    }
}
