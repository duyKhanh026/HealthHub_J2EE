package com.healthhub.hospital.service;

import com.healthhub.hospital.dao.ChiTietLichKhamRepository;
import com.healthhub.hospital.model.ChiTietLichKham;

import java.util.List;

public class ChiTietLichKhamService {
    private final ChiTietLichKhamRepository chiTietLichKhamRepository;

    public ChiTietLichKhamService(ChiTietLichKhamRepository chiTietLichKhamRepository) {
        this.chiTietLichKhamRepository = chiTietLichKhamRepository;
    }

    public List<ChiTietLichKham> getChiTietLichKhamByMaLK(Integer maLK) {
        return chiTietLichKhamRepository.getChiTietLichKhamByMaLK(maLK);
    }
}
