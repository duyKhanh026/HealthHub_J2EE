package com.healthhub.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthhub.hospital.Entity.PageCustomAboutUs;
import com.healthhub.hospital.Repository.PageCustomAboutUsRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PageCustomAboutUsService {

    @Autowired
    private PageCustomAboutUsRepository repository;

    public PageCustomAboutUs createPage(String iconlink, String title, String content) {
        PageCustomAboutUs page = new PageCustomAboutUs();
        page.setIconlink(iconlink);
        page.setTitle(title);
        page.setContent(content);
        return repository.save(page);
    }
    
    public List<PageCustomAboutUs> findAll() {
        return repository.findAll();
    }

    // Get a specific PageCustomIndex by ID
    public Optional<PageCustomAboutUs> findById(int id) {
        return repository.findById(id);
    }

    // Delete a PageCustomIndex by ID
    public void deleteById(int id) {
    	repository.deleteById(id);
    }
}
