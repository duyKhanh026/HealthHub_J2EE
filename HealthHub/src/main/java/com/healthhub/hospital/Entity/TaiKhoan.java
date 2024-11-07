package com.healthhub.hospital.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "taikhoan")
public class TaiKhoan {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTK")
    private int maTK;

    @Column(name = "TenDN")
    private String tenDN;

    @Column(name = "Matkhau")
    private String matkhau;


    @OneToOne
    @JoinColumn(name = "MaBN", referencedColumnName = "MaBN")
    private BenhNhan benhNhan;

    @Enumerated(EnumType.STRING)  // Lưu giá trị enum dưới dạng String trong DB
    @Column(name = "Vaitro")
    private Role vaitro;

}
