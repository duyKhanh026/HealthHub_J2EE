package com.healthhub.hospital.service;


import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.Repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService {
    @Autowired
    private TaiKhoanRepository tkRepo;

    public TaiKhoan GetTKByID(String tenDN){
        return tkRepo.getAccountByTenDN(tenDN);
    }

}
