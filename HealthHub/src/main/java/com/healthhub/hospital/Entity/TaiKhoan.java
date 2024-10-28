package com.healthhub.hospital.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
    @JoinColumn(name = "MaBN", insertable = false, updatable = false)
    private BenhNhan maBN;  // Đây là khóa ngoại liên kết với bảng BenhNhan

    @Column(name = "Vaitro")
    private String vaitro;
}
