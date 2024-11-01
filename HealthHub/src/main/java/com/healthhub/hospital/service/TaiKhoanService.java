package com.healthhub.hospital.service;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.Repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

//    public BenhNhan getBenhNhan(String tendn){
//       return taiKhoanRepository.findByBenhNhan_TenDN(tendn);
//    }


}
