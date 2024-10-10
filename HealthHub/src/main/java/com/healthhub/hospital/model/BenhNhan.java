package com.healthhub.hospital.model;

import java.sql.Date;

public class BenhNhan {
	int maBN;
	String HoTen;
	Date NgaySinh;
	String Gioitinh;
	String SDT;
	String Email;
	String Diachi;
	String Tiensubenh;
	public BenhNhan(int maBN, String moTen, Date ngaySinh, String gioitinh, String sDT, String email, String diachi,
			String tiensubenh) {
		super();
		this.maBN = maBN;
		HoTen = moTen;
		NgaySinh = ngaySinh;
		Gioitinh = gioitinh;
		SDT = sDT;
		Email = email;
		Diachi = diachi;
		Tiensubenh = tiensubenh;
	}
	public BenhNhan() {
		super();
	}
	public int getMaBN() {
		return maBN;
	}
	public void setMaBN(int maBN) {
		this.maBN = maBN;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String moTen) {
		HoTen = moTen;
	}
	public Date getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}
	public String getGioitinh() {
		return Gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		Gioitinh = gioitinh;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDiachi() {
		return Diachi;
	}
	public void setDiachi(String diachi) {
		Diachi = diachi;
	}
	public String getTiensubenh() {
		return Tiensubenh;
	}
	public void setTiensubenh(String tiensubenh) {
		Tiensubenh = tiensubenh;
	}
	
	
	
}
