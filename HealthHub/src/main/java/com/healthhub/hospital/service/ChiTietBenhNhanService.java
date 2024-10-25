package com.healthhub.hospital.service;

import org.springframework.stereotype.Service;

import com.healthhub.hospital.Repository.ChiTietLichKhamRepository;

@Service
public class ChiTietBenhNhanService {
    private final ChiTietLichKhamRepository chiTietLichKhamRepository;

    public ChiTietBenhNhanService(ChiTietLichKhamRepository chiTietLichKhamRepository) {
        this.chiTietLichKhamRepository = chiTietLichKhamRepository;
    }
}
