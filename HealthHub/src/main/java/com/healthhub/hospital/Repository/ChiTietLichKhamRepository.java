package com.healthhub.hospital.Repository;

import com.healthhub.hospital.Entity.ChiTietLichKham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietLichKhamRepository extends JpaRepository<ChiTietLichKham, Integer> {

	ChiTietLichKham findByLichKham_MaLK(Integer maLK);
    
}
