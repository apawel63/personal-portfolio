package com.personalprojects.portfolio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.personalprojects.portfolio.service.EducationService;
import com.personalprojects.portfolio.model.Education;

@RestController
@RequestMapping("/api/education")
public class EducationController {

	private final EducationService educationService;

	public EducationController(EducationService educationService) {
		this.educationService = educationService;
	}

	@GetMapping
	public List<Education> findAll() {
		return educationService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Education> findById(@PathVariable Integer id) {
		return educationService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Education> create(@RequestBody Education education) {
		Education createdEducation = educationService.create(education);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdEducation.getId())
				.toUri();

		return ResponseEntity.created(location).body(createdEducation);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Education> update(@PathVariable Integer id, @RequestBody Education education) {
		return educationService.update(id, education)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (!educationService.delete(id)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}
}
