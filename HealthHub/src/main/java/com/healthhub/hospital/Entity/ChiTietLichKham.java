package com.healthhub.hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Tự động tạo getter, setter, toString, equals, và hashCode
@NoArgsConstructor // Tạo constructor không tham số
@AllArgsConstructor // Tạo constructor với tất cả các tham số
public class ChiTietLichKham {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int maHS;
    private int maLK;
    private String chuanDoan;
    private String donThuoc;
    private String ghiChuThem;

}
