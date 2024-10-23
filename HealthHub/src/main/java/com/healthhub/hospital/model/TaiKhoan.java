package com.healthhub.hospital.model;

public class TaiKhoan {


    private int maTK;
    private String tenDN;
    private String matkhau;
    private int maBN;  // Đây là khóa ngoại liên kết với bảng BenhNhan
    private String vaitro;

    // Constructor
    public TaiKhoan(int maTK, String tenDN, String matkhau, int maBN, String vaitro) {
        this.maTK = maTK;
        this.tenDN = tenDN;
        this.matkhau = matkhau;
        this.maBN = maBN;
        this.vaitro = vaitro;
    }

    // Constructor mặc định
    public TaiKhoan() {
    }

    // Getters and setters
    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getMaBN() {
        return maBN;
    }

    public void setMaBN(int maBN) {
        this.maBN = maBN;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

}
