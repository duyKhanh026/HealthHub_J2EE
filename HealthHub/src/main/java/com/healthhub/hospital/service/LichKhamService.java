package com.healthhub.hospital.service;

import com.healthhub.hospital.Repository.LichKhamRepository;
import com.healthhub.hospital.Entity.LichKham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return lichKhamRepository.findByMaBN(maBN);
    }
    public LichKham getLichKhambyID(Integer id){
        return lichKhamRepository.findById(id).orElse(null);
    }
}
