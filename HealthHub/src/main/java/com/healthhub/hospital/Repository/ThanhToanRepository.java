package com.healthhub.hospital.Repository;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.Entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ThanhToanRepository extends JpaRepository<ThanhToan, Integer> {
    ThanhToan findByLichKham(LichKham lichKham);// Query để lấy doanh thu trong khoảng thời gian
    // Query để lấy doanh thu trong khoảng thời gian

}
