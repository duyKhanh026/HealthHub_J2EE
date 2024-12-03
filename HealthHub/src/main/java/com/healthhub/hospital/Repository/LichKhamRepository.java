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

	// Thống kê theo khoảng thời gian (sử dụng LocalDateTime)
	@Query("SELECT DATE_FORMAT(l.ngayGioDatKham, '%d/%m'), COUNT(l) " +
			"FROM LichKham l " +
			"WHERE l.ngayGioDatKham BETWEEN :startDate AND :endDate " +
			"GROUP BY DATE_FORMAT(l.ngayGioDatKham, '%d/%m') " +
			"ORDER BY l.ngayGioDatKham")
	List<Object[]> findStatisticsByDateRange(@Param("startDate") LocalDateTime startDate,
											 @Param("endDate") LocalDateTime endDate);

	// Thống kê theo khoảng thời gian và trạng thái (sử dụng LocalDateTime)
	@Query("SELECT DATE_FORMAT(l.ngayGioDatKham, '%d/%m'), COUNT(l) " +
			"FROM LichKham l " +
			"WHERE l.ngayGioDatKham BETWEEN :startDate AND :endDate " +
			"AND l.trangThai = :status " +
			"GROUP BY DATE_FORMAT(l.ngayGioDatKham, '%d/%m') " +
			"ORDER BY l.ngayGioDatKham")
	List<Object[]> findStatisticsByDateRangeAndStatus(@Param("startDate") LocalDateTime startDate,
													  @Param("endDate") LocalDateTime endDate,
													  @Param("status") String status);


	List<LichKham> findByBenhNhan_MaBNAndNgayGioDatKhamBetween(int maBN, LocalDateTime startDate, LocalDateTime endDate);

	boolean existsByBenhNhan_MaBNAndNgayGioDatKhamBetween(int benhNhanId, LocalDateTime start, LocalDateTime end);

}
