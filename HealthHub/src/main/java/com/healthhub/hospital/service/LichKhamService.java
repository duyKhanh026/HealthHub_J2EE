package com.healthhub.hospital.service;

import com.healthhub.hospital.Repository.LichKhamRepository;
import com.healthhub.hospital.model.LichKham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichKhamService {
    private final LichKhamRepository lichKhamRepository;

    @Autowired
    public LichKhamService(LichKhamRepository lichKhamRepository) {
        this.lichKhamRepository = lichKhamRepository;
    }


    public List<LichKham> getAllLichKham() {
        return lichKhamRepository.getinforLichKham();
    }

    public List<LichKham> getLichKhamByBenhNhanId(Integer maBN) {
        return lichKhamRepository.getLichKhamByBenhNhanId(maBN);
    }
    public List<LichKham> getLichKhamByLichKhamId(Integer maLK) {
        return lichKhamRepository.getLichKhamByLichKhamId(maLK);
    }
}
