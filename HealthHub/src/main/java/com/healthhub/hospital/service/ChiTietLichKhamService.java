package com.healthhub.hospital.service;

import com.healthhub.hospital.Repository.ChiTietLichKhamRepository;
import com.healthhub.hospital.model.ChiTietLichKham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietLichKhamService {
    private final ChiTietLichKhamRepository chiTietLichKhamRepository;

    @Autowired
    public ChiTietLichKhamService(ChiTietLichKhamRepository chiTietLichKhamRepository) {
        this.chiTietLichKhamRepository = chiTietLichKhamRepository;
    }

    public List<ChiTietLichKham> getChiTietLichKhamByMaLK(Integer maLK) {
        return chiTietLichKhamRepository.getChiTietLichKhamByMaLK(maLK);
    }
}
