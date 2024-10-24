package com.healthhub.hospital.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Tự động tạo getter, setter, toString, equals, và hashCode
@NoArgsConstructor // Tạo constructor không tham số
@AllArgsConstructor // Tạo constructor với tất cả các tham số
public class BenhNhan {
    int maBN;
    String hoTen;
    Date ngaySinh;
    String gioitinh;
    String SDT;
    String email;
    String diachi;
    String tiensubenh;

    public BenhNhan(int maBN, String hoTen) {
        this.maBN = maBN;
        this.hoTen = hoTen;
    }
}
