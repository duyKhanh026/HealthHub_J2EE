package com.healthhub.hospital.Repository;

import com.healthhub.hospital.Entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanRepository  extends JpaRepository<TaiKhoan, Integer> {

    TaiKhoan getAccountByTenDN(String tenDN);

}
