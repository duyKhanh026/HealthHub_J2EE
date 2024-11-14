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

	// Đếm số lịch khám trong ngày
	long countByNgayGioDatKhamBetween(LocalDateTime startDate, LocalDateTime endDate);

	// Đếm số lịch khám trong tuần
	@Query("SELECT COUNT(l) FROM LichKham l WHERE YEAR(l.ngayGioDatKham) = :year AND WEEK(l.ngayGioDatKham) = :week")
	long countByWeek(@Param("year") int year, @Param("week") int week);

	// Đếm số lịch khám trong tháng
	long countByNgayGioDatKhamBetweenAndTrangThai(LocalDateTime startDate, LocalDateTime endDate, String trangThai);

	// Đếm số lịch khám theo trạng thái (Confirmed, Pending, etc.)
	long countByTrangThai(String trangThai);
	@Query("SELECT COUNT(l) FROM LichKham l WHERE l.trangThai = :status AND DATE(l.ngayGioDatKham) = :date")
	long countByStatusAndDate(@Param("status") String status, @Param("date") LocalDate date);
}
