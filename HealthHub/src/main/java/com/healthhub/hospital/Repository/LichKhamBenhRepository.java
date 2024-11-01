package com.healthhub.hospital.Repository;
import com.healthhub.hospital.Entity.LichKham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LichKhamBenhRepository extends JpaRepository<LichKham, Integer> {
    List<LichKham> findByBenhNhan_MaBN(int maBN);
    // Có thể thêm các phương thức tùy chỉnh nếu cần
}
