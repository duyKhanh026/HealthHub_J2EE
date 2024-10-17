package com.healthhub.hospital.model;

import java.util.Date;

public class ChiTietLichKham {

    private int maHS;
    private int maLK;
    private String chuanDoan;
    private String donThuoc;
    private String ghiChuThem;

    public ChiTietLichKham() {
    }

    public ChiTietLichKham(int maHS, int maLK, String chuanDoan, String donThuoc, String ghiChuThem) {
        this.maHS = maHS;
        this.maLK = maLK;
        this.chuanDoan = chuanDoan;
        this.donThuoc = donThuoc;
        this.ghiChuThem = ghiChuThem;
    }

    public int getMaHS() {
        return maHS;
    }

    public void setMaHS(int maHS) {
        this.maHS = maHS;
    }

    public int getMaLK() {
        return maLK;
    }

    public void setMaLK(int maLK) {
        this.maLK = maLK;
    }

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public String getDonThuoc() {
        return donThuoc;
    }

    public void setDonThuoc(String donThuoc) {
        this.donThuoc = donThuoc;
    }

    public String getGhiChuThem() {
        return ghiChuThem;
    }

    public void setGhiChuThem(String ghiChuThem) {
        this.ghiChuThem = ghiChuThem;
    }
}
