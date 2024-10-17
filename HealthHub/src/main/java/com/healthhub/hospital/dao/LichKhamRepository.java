package com.healthhub.hospital.dao;

import com.healthhub.hospital.model.BenhNhan;
import com.healthhub.hospital.model.LichKham;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class LichKhamRepository extends JdbcDaoSupport {

    public LichKhamRepository(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<LichKham> getinforLichKham() {
        String sql = "SELECT lk.MaLK, lk.MaBN, bn.Hoten AS TenBenhNhan, lk.Ngaygiodatkham, lk.Trangthai, bn.Ngaysinh, bn.Gioitinh " +
                "FROM lichkham lk " +
                "JOIN benhnhan bn ON lk.MaBN = bn.MaBN"; // JOIN bảng benhnhan

        try {
            return this.getJdbcTemplate().query(sql, (rs, rowNum) -> {
                LichKham lk = new LichKham();
                lk.setMaLK(rs.getInt("MaLK"));

                // Tạo đối tượng BenhNhan
                BenhNhan bn = new BenhNhan();
                bn.setMaBN(rs.getInt("MaBN"));
                bn.setHoTen(rs.getString("TenBenhNhan"));
                // Gán đối tượng BenhNhan vào LichKham

                lk.setMaBN(rs.getInt("MaBN"));
                lk.setBenhNhan(bn);

                lk.setNgayGioDatKham(rs.getTimestamp("Ngaygiodatkham"));
                lk.setTrangThai(rs.getString("Trangthai"));
                return lk;
            });
        } catch (DataAccessException e) {
            logger.error("Lỗi khi truy xuất danh sách lịch khám", e);
            return null;
        }
    }


    @SuppressWarnings("deprecation")
	public List<LichKham> getLichKhamByBenhNhanId(Integer maBN) {
        String sql = "SELECT MaLK, MaBN, Ngaygiodatkham, Trangthai FROM lichkham WHERE MaBN = ?";

        try {
            return this.getJdbcTemplate().query(sql, new Object[]{maBN}, (rs, rowNum) -> {
                LichKham dto = new LichKham();
                dto.setMaLK(rs.getInt("MaLK"));
                dto.setMaBN(rs.getInt("MaBN"));
                dto.setNgayGioDatKham(rs.getTimestamp("Ngaygiodatkham"));
                dto.setTrangThai(rs.getString("Trangthai"));
                return dto;
            });
        } catch (DataAccessException e) {
            logger.error("Không tìm thấy bệnh nhân với MaBN {}: {}");

            return null;
        }
    }
    @SuppressWarnings("deprecation")
	public List<LichKham> getLichKhamByLichKhamId(Integer maLK) {
        String sql = "SELECT MaLK, MaBN, Ngaygiodatkham, Trangthai FROM lichkham WHERE MaLK = ?";

        try {
            return this.getJdbcTemplate().query(sql, new Object[]{maLK}, (rs, rowNum) -> {
                LichKham dto = new LichKham();
                dto.setMaLK(rs.getInt("MaLK"));
                dto.setMaBN(rs.getInt("MaBN"));
                dto.setNgayGioDatKham(rs.getTimestamp("Ngaygiodatkham"));
                dto.setTrangThai(rs.getString("Trangthai"));
                return dto;
            });
        } catch (DataAccessException e) {
            logger.error("Không tìm thấy MaLK {}: {}");

            return null;
        }
    }
}
