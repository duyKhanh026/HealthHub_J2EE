package com.healthhub.hospital.Repository;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaiKhoanRepository  extends JpaRepository<TaiKhoan, Integer> {

    TaiKhoan findByTenDN(String tenDN);  // Tìm kiếm TaiKhoan theo tên đăng nhập
    TaiKhoan findByBenhNhan(BenhNhan benhnhan);

}
