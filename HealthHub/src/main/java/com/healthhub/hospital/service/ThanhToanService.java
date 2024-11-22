package com.healthhub.hospital.service;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.ThanhToan;
import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.Repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThanhToanService {
    @Autowired
    private ThanhToanRepository thanhToanRepository;

    public ThanhToanService(ThanhToanRepository thanhToanRepository) {
        this.thanhToanRepository = thanhToanRepository;
    }

    public List<ThanhToan> getAllThanhtoan() {
        return thanhToanRepository.findAll();
    }

    public ThanhToan findbyid_thanhtoan(int id){
        return thanhToanRepository.findById(id).orElse(null);
    }

    public ThanhToan updateThanhToan(ThanhToan thanhToan){
        return thanhToanRepository.save(thanhToan);
    }

}
