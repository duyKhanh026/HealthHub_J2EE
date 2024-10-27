package com.healthhub.hospital.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthhub.hospital.Entity.LichKham;

@Repository
public interface LichKhamRepository extends JpaRepository<LichKham, Integer> {

	List<LichKham> findByMaBN(Integer maBN);

	List<LichKham> findByNgayGioDatKhamBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
