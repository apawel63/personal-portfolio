package com.personalprojects.portfolio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.personalprojects.portfolio.model.SkillDto;
import com.personalprojects.portfolio.service.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;
 
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }
 
    // GET /api/skills
    @GetMapping
    public ResponseEntity<List<SkillDto>> getAll() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }
 
    // GET /api/skills/{id}
    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(skillService.getSkillById(id));
    }

}
