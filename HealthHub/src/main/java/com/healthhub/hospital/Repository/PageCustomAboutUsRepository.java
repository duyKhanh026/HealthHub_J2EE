package com.healthhub.hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthhub.hospital.Entity.PageCustomAboutUs;

@Repository
public interface PageCustomAboutUsRepository extends JpaRepository<PageCustomAboutUs, Integer> {
    // Các phương thức tùy chỉnh (nếu cần)
}
