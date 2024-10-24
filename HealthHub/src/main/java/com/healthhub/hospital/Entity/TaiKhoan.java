package com.healthhub.hospital.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int maTK;
    private String tenDN;
    private String matkhau;
    private int maBN;  // Đây là khóa ngoại liên kết với bảng BenhNhan
    private String vaitro;
}
