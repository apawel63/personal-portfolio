package com.personalprojects.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personalprojects.portfolio.model.About;
import com.personalprojects.portfolio.repository.AboutRepository;

@Service
public class AboutService {

    private final AboutRepository aboutRepository;

    public AboutService(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    public List<About> findAll() {
        return aboutRepository.findAll();
    }

    public Optional<About> findById(Integer id) {
        return aboutRepository.findById(id);
    }
}
