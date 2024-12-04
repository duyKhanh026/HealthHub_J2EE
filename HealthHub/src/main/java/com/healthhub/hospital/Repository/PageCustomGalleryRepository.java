package com.healthhub.hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthhub.hospital.Entity.PageCustomGallery;

@Repository
public interface PageCustomGalleryRepository extends JpaRepository<PageCustomGallery, Integer> {
    // Các phương thức tùy chỉnh (nếu cần)
}
