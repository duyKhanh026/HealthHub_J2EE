package com.healthhub.hospital.Entity;

import java.time.LocalDateTime;
import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Column(name = "Hoten", nullable = true)
    private String hoten;

    @Column(name = "Email", nullable = true)
    private String email;

    @Column(name = "SDT", nullable = true)
    private String sDT;

    @Column(name = "Note", nullable = true)
    private String note;

    @Column(name = "Ngaygiodatkham", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime ngayGioDatKham;

    @Column(name = "Trangthai", nullable = false)
    private String trangThai;

    // Many-to-one relationship with BenhNhan
    @ManyToOne
    @JoinColumn(name = "MaBN")
    private BenhNhan benhNhan;

}
