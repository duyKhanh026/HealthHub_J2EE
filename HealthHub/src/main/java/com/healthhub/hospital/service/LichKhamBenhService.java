package com.healthhub.hospital.service;

import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.Repository.LichKhamBenhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichKhamBenhService {
    @Autowired
    private LichKhamBenhRepository LKBRepository;

    public List<LichKham> getAllLichKham() {
        return LKBRepository.findAll();
    }
    // Lấy lịch khám của bệnh nhân theo MaBN
    public List<LichKham> getLichKhamByMaBN(int maBN) {
        return LKBRepository.findByBenhNhan_MaBN(maBN);
    }
}
