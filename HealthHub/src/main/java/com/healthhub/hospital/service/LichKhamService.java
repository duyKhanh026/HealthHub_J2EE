package com.healthhub.hospital.service;

import com.healthhub.hospital.dao.LichKhamRepository;
import com.healthhub.hospital.model.LichKham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
