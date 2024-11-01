package com.healthhub.hospital.service;

import com.healthhub.hospital.Repository.LichKhamRepository;
import com.healthhub.hospital.Entity.LichKham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LichKhamService {
    private final LichKhamRepository lichKhamRepository;

    public LichKhamService(LichKhamRepository lichKhamRepository) {
        this.lichKhamRepository = lichKhamRepository;
    }


    public List<LichKham> getAllLichKham() {
        return lichKhamRepository.findAll();
    }

    public List<LichKham> getLichKhamByBenhNhanId(Integer maBN) {
        return lichKhamRepository.findByBenhNhan_MaBN(maBN);
    }
    public LichKham getLichKhambyID(Integer id){
        return lichKhamRepository.findById(id).orElse(null);
    }

    public List<LichKham> getLichKhamByDate(LocalDate date) {
        return lichKhamRepository.findByNgayGioDatKhamBetween(
                date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    public LichKham updateLichKham(LichKham lichKham){
        return lichKhamRepository.save(lichKham);
    }


    // Lấy lịch khám của bệnh nhân theo MaBN
    public List<LichKham> getLichKhamByMaBN(int maBN) {
        return lichKhamRepository.findByBenhNhan_MaBN(maBN);
    }

}
