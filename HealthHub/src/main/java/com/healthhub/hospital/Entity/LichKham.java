package com.healthhub.hospital.Entity;

import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lichkham")
public class LichKham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLK")
    private int maLK;

    @Column(name = "MaBN", nullable = false)
    private int maBN;

    @Column(name = "Ngaygiodatkham", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayGioDatKham;

    @Column(name = "Trangthai", nullable = false)
    private String trangThai;

    // Many-to-one relationship with BenhNhan
    @ManyToOne
    @JoinColumn(name = "MaBN", insertable = false, updatable = false)
    private BenhNhan benhNhan;

    // One-to-one relationship with ChiTietLichKham
    @OneToOne(mappedBy = "lichKham")
    private ChiTietLichKham chiTietLichKham;

//    public Integer getMaLK() {
//        return chiTietLichKham != null ? chiTietLichKham.getMaLK() : null;
//    }
}
