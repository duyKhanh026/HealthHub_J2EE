package com.healthhub.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Tự động tạo getter, setter, toString, equals, và hashCode
@NoArgsConstructor // Tạo constructor không tham số
@AllArgsConstructor // Tạo constructor với tất cả các tham số
public class ChiTietLichKham {

    private int maHS;
    private int maLK;
    private String chuanDoan;
    private String donThuoc;
    private String ghiChuThem;

}
