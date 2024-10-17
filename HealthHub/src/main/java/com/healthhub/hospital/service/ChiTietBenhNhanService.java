package com.healthhub.hospital.service;

import com.healthhub.hospital.Repository.ChiTietLichKhamRepository;

public class ChiTietBenhNhanService {
    private final ChiTietLichKhamRepository chiTietLichKhamRepository;

    public ChiTietBenhNhanService(ChiTietLichKhamRepository chiTietLichKhamRepository) {
        this.chiTietLichKhamRepository = chiTietLichKhamRepository;
    }
}
