package com.healthhub.hospital.Entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LichKham {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int maLK;
    private int maBN;
    private Date ngayGioDatKham;
    private String trangThai;
    private ChiTietLichKham chiTietLichKham;
    private BenhNhan benhNhan;
}
