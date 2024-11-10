package com.healthhub.hospital.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthhub.hospital.Entity.LichKham;

@Repository
public interface LichKhamRepository extends JpaRepository<LichKham, Integer> {


	List<LichKham> findByNgayGioDatKhamBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

	List<LichKham> findByBenhNhan_MaBN(int maBN);

	@Query("SELECT l FROM LichKham l WHERE DATE(l.ngayGioDatKham) = :selectedDate AND l.trangThai = :trangThai")
	List<LichKham> findByNgayGioDatKhamAndTrangThai(@Param("selectedDate") LocalDate selectedDate, @Param("trangThai") String trangThai);

	List<LichKham> findByTrangThai(String date);

	List<LichKham> findByTrangThaiNot(String trangThai);

	List<LichKham> findByNgayGioDatKhamBetweenAndTrangThaiNot(LocalDateTime startOfDay, LocalDateTime endOfDay, String trangThai);


}
