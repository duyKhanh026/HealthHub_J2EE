package com.healthhub.hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthhub.hospital.Entity.PageCustomGallery;
import com.healthhub.hospital.Repository.PageCustomGalleryRepository;

@Service
public class PageCustomGalleryService {

    @Autowired
    private PageCustomGalleryRepository repository;

    public PageCustomGallery createPage(String imglink) {
    	PageCustomGallery page = new PageCustomGallery();
        page.setUrl(imglink);
        return repository.save(page);
    }
    
    public List<PageCustomGallery> findAll() {
        return repository.findAll();
    }

    // Get a specific PageCustomIndex by ID
    public Optional<PageCustomGallery> findById(int id) {
        return repository.findById(id);
    }

    // Delete a PageCustomIndex by ID
    public void deleteById(int id) {
    	repository.deleteById(id);
    }
}
