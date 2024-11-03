package com.healthhub.hospital.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "thanhtoan")
public class ThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTT")
    private int maTT;

    @Column(name = "Sotien", nullable = true)
    private int soTien;

    @Column(name = "Ngaythanhtoan", nullable = true)
    private LocalDateTime ngayThanhToan;

    @Column(name = "Hinhthucthanhtoan", nullable = true)
    private String hinhThucThanhToan;

    // One-to-One relationship with LichKham
    @OneToOne
    @JoinColumn(name = "MaLK", nullable = false)
    private LichKham lichKham;
}

