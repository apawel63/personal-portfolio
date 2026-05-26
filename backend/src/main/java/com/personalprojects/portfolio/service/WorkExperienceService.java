package com.personalprojects.portfolio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personalprojects.portfolio.model.WorkExperienceDto;
import com.personalprojects.portfolio.repository.WorkExperienceRepository;

import java.util.List;
 
@Service
@Transactional(readOnly = true)
public class WorkExperienceService {
 
    private final WorkExperienceRepository workExperienceRepository;
 
    public WorkExperienceService(WorkExperienceRepository workExperienceRepository) {
        this.workExperienceRepository = workExperienceRepository;
    }
 
    public List<WorkExperienceDto> getAllWithTasks() {
        workExperienceRepository.findAllWithTechnologies();
        return workExperienceRepository.findAllWithTasks()
                .stream()
                .map(WorkExperienceDto::from)
                .toList();
    }

    public WorkExperienceDto getByIdWithTasks(Integer id) {
        workExperienceRepository.findByIdWithTechnologies(id);
        return workExperienceRepository.findByIdWithTasks(id)
                .map(WorkExperienceDto::from)
                .orElseThrow(() -> new WorkExperienceNotFoundException(id));
    }
 
    // --- Exception ---
 
    public static class WorkExperienceNotFoundException extends RuntimeException {
        public WorkExperienceNotFoundException(Integer id) {
            super("Work experience not found with id: " + id);
        }
    }
}
