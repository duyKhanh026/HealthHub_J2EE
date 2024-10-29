package com.healthhub.hospital.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "taikhoan")
public class TaiKhoan {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTK")
    private int maTK;

    @Column(name = "TenDN")
    private String tenDN;

    @Column(name = "Matkhau")
    private String matkhau;


    @Column(name = "MaBN")
    private int maBN; // Chỉ lưu trữ mã bệnh nhân (số nguyên)

    @Column(name = "Vaitro")
    private String vaitro;



}
