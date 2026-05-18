package com.personalprojects.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personalprojects.portfolio.model.Education;
import com.personalprojects.portfolio.repository.EducationRepository;

@Service
public class EducationService {

	private final EducationRepository educationRepository;

	public EducationService(EducationRepository educationRepository) {
		this.educationRepository = educationRepository;
	}

	public List<Education> findAll() {
		return educationRepository.findAll();
	}

	public Optional<Education> findById(Integer id) {
		return educationRepository.findById(id);
	}

	public Education create(Education education) {
		education.setId(null);
		return educationRepository.save(education);
	}

	public Optional<Education> update(Integer id, Education updatedEducation) {
		return educationRepository.findById(id)
				.map(existingEducation -> {
					existingEducation.setDegree(updatedEducation.getDegree());
					existingEducation.setSchool(updatedEducation.getSchool());
					existingEducation.setLocation(updatedEducation.getLocation());
					existingEducation.setDuration(updatedEducation.getDuration());
					existingEducation.setAvatar(updatedEducation.getAvatar());
					return educationRepository.save(existingEducation);
				});
	}

	public boolean delete(Integer id) {
		if (!educationRepository.existsById(id)) {
			return false;
		}

		educationRepository.deleteById(id);
		return true;
	}
}
