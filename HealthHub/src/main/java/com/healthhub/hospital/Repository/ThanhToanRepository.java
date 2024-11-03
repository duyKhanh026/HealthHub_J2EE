package com.healthhub.hospital.Repository;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThanhToanRepository extends JpaRepository<ThanhToan, Integer> {
}
