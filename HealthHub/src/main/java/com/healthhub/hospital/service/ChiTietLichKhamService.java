package com.healthhub.hospital.service;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Repository.ChiTietLichKhamRepository;
import com.healthhub.hospital.Entity.ChiTietLichKham;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietLichKhamService {
    private final ChiTietLichKhamRepository chiTietLichKhamRepository;

    public ChiTietLichKhamService(ChiTietLichKhamRepository chiTietLichKhamRepository) {
        this.chiTietLichKhamRepository = chiTietLichKhamRepository;
    }

    public ChiTietLichKham getChiTietLichKhamByMaLK(Integer maLK) {
        return chiTietLichKhamRepository.findByLichKham_MaLK(maLK);
    }

    public void updateChiTietLichKham(ChiTietLichKham chiTietLichKham) {
        chiTietLichKhamRepository.save(chiTietLichKham); // This will update if the entity ID already exists
    }
}
