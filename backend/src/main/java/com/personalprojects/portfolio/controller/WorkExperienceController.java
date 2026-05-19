package com.personalprojects.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.personalprojects.portfolio.model.WorkExperienceDto;
import com.personalprojects.portfolio.service.WorkExperienceService;

import java.util.List;
 
@RestController
@RequestMapping("/api/work-experience")
public class WorkExperienceController {
    private final WorkExperienceService workExperienceService;
 
    public WorkExperienceController(WorkExperienceService workExperienceService) {
        this.workExperienceService = workExperienceService;
    }
 
    // GET /api/work-experience
    @GetMapping
    public ResponseEntity<List<WorkExperienceDto>> getAll() {
        return ResponseEntity.ok(workExperienceService.getAllWithTasks());
    }
 
    // GET /api/work-experience/{id}
    @GetMapping("/{id}")
    public ResponseEntity<WorkExperienceDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(workExperienceService.getByIdWithTasks(id));
    }

}
