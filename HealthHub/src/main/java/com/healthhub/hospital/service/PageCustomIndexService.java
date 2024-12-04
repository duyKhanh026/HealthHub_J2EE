package com.healthhub.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthhub.hospital.Entity.PageCustomIndex;
import com.healthhub.hospital.Repository.PageCustomIndexRepository;

@Service
public class PageCustomIndexService {

    @Autowired
    private PageCustomIndexRepository repository;
    
    public PageCustomIndex saveOrUpdatePageCustomIndex(PageCustomIndex pageCustomIndex) {
        return repository.save(pageCustomIndex);
    }
    public PageCustomIndex getFirstPageCustomIndex() {
        return repository.findFirstRow();
    }
    
    
}
