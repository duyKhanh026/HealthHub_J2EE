package com.healthhub.hospital.Entity;

import java.time.LocalDate; // Sử dụng LocalDate thay cho java.sql.Date
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BenhNhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    @Column(name = "MaBN") // Chỉ định tên cột trong bảng
    private int maBN;

    @Column(name = "Hoten")
    private String hoTen;

    @Column(name = "Ngaysinh")
    private LocalDate ngaySinh; // Sử dụng LocalDate

    @Column(name = "Gioitinh")
    private String gioitinh;

    @Column(name = "SDT")
    private String SDT;

    @Column(name = "Email")
    private String email;

    @Column(name = "Diachi")
    private String diachi;

    @Column(name = "Tiensubenh")
    private String tiensubenh;

    // Constructor có 2 tham số
    public BenhNhan(int maBN, String hoTen) {
        this.maBN = maBN;
        this.hoTen = hoTen;
    }
}
