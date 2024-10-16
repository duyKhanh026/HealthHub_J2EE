package com.healthhub.hospital.service;

import com.healthhub.hospital.dao.LichKhamRepository;
import com.healthhub.hospital.model.LichKham;

import java.util.List;

public class LichKhamService {
    private final LichKhamRepository lichKhamRepository;


    public LichKhamService(LichKhamRepository lichKhamRepository) {
        this.lichKhamRepository = lichKhamRepository;
    }

    public List<LichKham> getLichKhamByBenhNhanId(Integer maBN) {
        return lichKhamRepository.getLichKhamByBenhNhanId(maBN);
    }
}
