package com.healthhub.hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.healthhub.hospital.Entity.PageCustomIndex;

@Repository
public interface PageCustomIndexRepository extends JpaRepository<PageCustomIndex, String> {
	
    @Query(value = "SELECT * FROM page_custom_index LIMIT 1", nativeQuery = true)
    PageCustomIndex findFirstRow();
}

