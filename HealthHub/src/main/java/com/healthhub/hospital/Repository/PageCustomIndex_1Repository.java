package com.healthhub.hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthhub.hospital.Entity.PageCustomIndex_1;

@Repository
public interface PageCustomIndex_1Repository extends JpaRepository<PageCustomIndex_1, Long> {
    // You can define custom query methods here if needed
}