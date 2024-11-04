package com.healthhub.hospital.Entity;

import java.time.LocalDate; // Sử dụng LocalDate thay cho java.sql.Date
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "benhnhan")
public class BenhNhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    @Column(name = "MaBN") // Chỉ định tên cột trong bảng
    private int maBN;

    @Column(name = "Hoten")
    private String hoTen;

    @Column(name = "Ngaysinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @OneToOne(mappedBy = "benhNhan")
    private TaiKhoan taiKhoan;
    public BenhNhan(String hoTen, String gioitinh, String SDT, String email, String diachi, String tiensubenh) {
        this.hoTen = hoTen;
        this.gioitinh = gioitinh;
        this.SDT = SDT;
        this.email = email;
        this.diachi = diachi;
        this.tiensubenh = tiensubenh;
    }

}
