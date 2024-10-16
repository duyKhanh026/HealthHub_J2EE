package com.healthhub.hospital.model;

import java.util.Date;

public class LichKham {

    private int maLK;
    private int maBN;
    private Date ngayGioDatKham;
    private String trangThai;
    private ChiTietLichKham chiTietLichKham;

    public LichKham() {
    }

    public LichKham(int maLK, int maBN, Date ngayGioDatKham, String trangThai, ChiTietLichKham chiTietLichKham) {
        this.maLK = maLK;
        this.maBN = maBN;
        this.ngayGioDatKham = ngayGioDatKham;
        this.trangThai = trangThai;
        this.chiTietLichKham = chiTietLichKham;
    }

    public int getMaLK() {
        return maLK;
    }

    public void setMaLK(int maLK) {
        this.maLK = maLK;
    }

    public int getMaBN() {
        return maBN;
    }

    public void setMaBN(int maBN) {
        this.maBN = maBN;
    }

    public Date getNgayGioDatKham() {
        return ngayGioDatKham;
    }

    public void setNgayGioDatKham(Date ngayGioDatKham) {
        this.ngayGioDatKham = ngayGioDatKham;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public ChiTietLichKham getChiTietLichKham() {
        return chiTietLichKham;
    }

    public void setChiTietLichKham(ChiTietLichKham chiTietLichKham) {
        this.chiTietLichKham = chiTietLichKham;
    }
}
