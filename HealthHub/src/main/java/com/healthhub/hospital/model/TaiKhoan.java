package com.healthhub.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoan {
    private int maTK;
    private String tenDN;
    private String matkhau;
    private int maBN;  // Đây là khóa ngoại liên kết với bảng BenhNhan
    private String vaitro;
}
