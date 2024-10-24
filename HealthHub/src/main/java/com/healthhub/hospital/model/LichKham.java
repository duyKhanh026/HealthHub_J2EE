package com.healthhub.hospital.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LichKham {

    private int maLK;
    private int maBN;
    private Date ngayGioDatKham;
    private String trangThai;
    private ChiTietLichKham chiTietLichKham;
    private BenhNhan benhNhan;
}
