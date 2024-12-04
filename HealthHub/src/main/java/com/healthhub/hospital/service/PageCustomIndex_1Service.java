package com.healthhub.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthhub.hospital.Entity.PageCustomIndex_1;
import com.healthhub.hospital.Repository.PageCustomIndex_1Repository;

import java.util.List;
import java.util.Optional;

@Service
public class PageCustomIndex_1Service {

    @Autowired
    private PageCustomIndex_1Repository repository;

    // Save a new PageCustomIndex
    public PageCustomIndex_1 save(PageCustomIndex_1 pageCustomIndex) {
        return repository.save(pageCustomIndex);
    }

    // Get all PageCustomIndexes
    public List<PageCustomIndex_1> findAll() {
        return repository.findAll();
    }

    // Get a specific PageCustomIndex by ID
    public Optional<PageCustomIndex_1> findById(Long id) {
        return repository.findById(id);
    }

    // Delete a PageCustomIndex by ID
    public void deleteById(Long id) {
    	repository.deleteById(id);
    }
}

