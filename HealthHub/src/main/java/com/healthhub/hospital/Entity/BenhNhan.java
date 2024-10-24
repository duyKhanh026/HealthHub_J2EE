package com.healthhub.hospital.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data // Tự động tạo getter, setter, toString, equals, và hashCode
@NoArgsConstructor // Tạo constructor không tham số
@AllArgsConstructor // Tạo constructor với tất cả các tham số
public class BenhNhan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
