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

    @Column(name = "MaLK", nullable = false)
    private int maLK;

    @Column(name = "Chuandoan", nullable = false)
    private String chuanDoan;

    @Column(name = "Donthuoc", nullable = false)
    private String donThuoc;

    @Column(name = "Ghichuthem", nullable = false)
    private String ghiChuThem;

    // One-to-one relationship with LichKham
    @OneToOne
    @JoinColumn(name = "MaLK", insertable = false, updatable = false)
    private LichKham lichKham;
}
