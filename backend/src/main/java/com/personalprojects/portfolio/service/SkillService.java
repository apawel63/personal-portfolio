package com.personalprojects.portfolio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personalprojects.portfolio.model.SkillDto;
import com.personalprojects.portfolio.repository.SkillRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<SkillDto> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(SkillDto::from)
                .toList();
    }

    public SkillDto getSkillById(Integer id) {
        return skillRepository.findById(id)
                .map(SkillDto::from)
                .orElseThrow(() -> new SkillNotFoundException(id));
    }

    // --- Exception ---
 
    public static class SkillNotFoundException extends RuntimeException {
        public SkillNotFoundException(Integer id) {
            super("Skill not found with id: " + id);
        }
    }
}
