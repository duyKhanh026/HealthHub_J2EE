package com.healthhub.hospital.service;

import com.healthhub.hospital.Repository.LichKhamRepository;
import com.healthhub.hospital.Entity.LichKham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LichKhamService {
    @Autowired
    private LichKhamRepository lichKhamRepository;

    public List<LichKham> getAllLichKham() {
        return lichKhamRepository.findAll();
    }

    public List<LichKham> getLichKhamByBenhNhanId(Integer maBN) {
        return lichKhamRepository.findByBenhNhan_MaBN(maBN);
    }
    public LichKham getLichKhambyID(Integer id){
        return lichKhamRepository.findById(id).orElse(null);
    }

    public List<LichKham> getLichKhamByDate_NotOff(LocalDate date) {
        return lichKhamRepository.findByNgayGioDatKhamBetweenAndTrangThaiNot(
                date.atStartOfDay(), date.plusDays(1).atStartOfDay(), "DayOff");
    }


    public LichKham updateLichKham(LichKham lichKham){
        return lichKhamRepository.save(lichKham);
    }


    // Lấy lịch khám của bệnh nhân theo MaBN
    public List<LichKham> getLichKhamByMaBN(int maBN) {
        return lichKhamRepository.findByBenhNhan_MaBN(maBN);
    }

    public List<LocalTime> getBookedTimesByDate(LocalDate date) {
        // Lấy tất cả các lịch khám trong ngày
        List<LichKham> appointments = lichKhamRepository.findByNgayGioDatKhamBetween(
                date.atTime(8, 0), date.atTime(17, 0)  // Lấy các lịch từ 8h đến 17h trong ngày
        );

        // Chuyển đổi giờ khám sang LocalTime
        return appointments.stream()
                .map(lichKham -> lichKham.getNgayGioDatKham().toLocalTime())
                .collect(Collectors.toList());
    }

    public List<LocalTime> getHolidayTimesByDate(LocalDate selectedDate) {
        // Truy vấn các lịch khám có trạng thái 'Nghỉ' trong ngày đã chọn
        List<LichKham> holidayLichKhams = lichKhamRepository.findByNgayGioDatKhamAndTrangThai(selectedDate, "Nghỉ");

        // Chuyển đổi các thời gian nghỉ từ LichKham thành LocalTime
        List<LocalTime> holidayTimes = new ArrayList<>();
        for (LichKham lichKham : holidayLichKhams) {
            holidayTimes.add(lichKham.getNgayGioDatKham().toLocalTime()); // Lấy thời gian từ NgayGioDatKham
        }
        return holidayTimes;
    }

    public List<LichKham> getAllDayOffAppointments() {
        return lichKhamRepository.findByTrangThai("DayOff");
    }

    public List<LichKham> findAllnotDayoff(String tt){
        return lichKhamRepository.findByTrangThaiNot(tt);
    }

    public void deleteLichKhamById(Integer id) {
        lichKhamRepository.deleteById(id);

    }

    public long countByDate(LocalDate date) {
        // Tính toán bắt đầu và kết thúc ngày
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        return lichKhamRepository.countByNgayGioDatKhamBetween(startOfDay, endOfDay);
    }

    public long countByWeek(LocalDate date) {
        int week = date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        int year = date.getYear();
        return lichKhamRepository.countByWeek(year, week);
    }

    public long countByMonth(LocalDate date) {
        LocalDateTime startOfMonth = date.withDayOfMonth(1).atStartOfDay();
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusNanos(1);
        return lichKhamRepository.countByNgayGioDatKhamBetween(startOfMonth, endOfMonth);
    }

    public long countByStatus(String status) {
        return lichKhamRepository.countByTrangThai(status);
    }


    public long countByStatusAndDate(String status, LocalDate date) {
        return lichKhamRepository.countByStatusAndDate(status, date);
    }
}
