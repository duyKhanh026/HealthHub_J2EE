package com.healthhub.hospital.service;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.Repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    public BenhNhan getBenhNhanByTenDN(String tenDN) {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenDN(tenDN);
        if (taiKhoan != null) {
            return taiKhoan.getBenhNhan();  // Trả về BenhNhan liên kết với TaiKhoan
        }
        return null;  // Nếu không có tài khoản nào khớp, trả về null
    }




    public void LuuTTTaiKhoan(TaiKhoan taiKhoan) {
        taiKhoanRepository.save(taiKhoan);
    }

    public TaiKhoan findByMaBN(BenhNhan BN) {
        return taiKhoanRepository.findByBenhNhan(BN);
    }

    public TaiKhoan findByTenDN(String username) {
        return taiKhoanRepository.findByTenDN(username);
    }
}
