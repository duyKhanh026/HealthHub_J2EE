package com.healthhub.hospital.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chitietlichkham")
public class ChiTietLichKham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHS")
    private int maHS;

    @Column(name = "Chuandoan", nullable = true)
    private String chuanDoan;

    @Column(name = "Donthuoc", nullable = true)
    private String donThuoc;

    @Column(name = "Ghichuthem", nullable = true)
    private String ghiChuThem;

    // Quan hệ One-to-One với LichKham
    @OneToOne
    @JoinColumn(name = "MaLK", nullable = false) // ánh xạ khóa ngoại MaLK tới LichKham
    private LichKham lichKham;
}
