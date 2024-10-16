package com.healthhub.hospital.dao;

import com.healthhub.hospital.model.ChiTietLichKham;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.util.List;

public class ChiTietLichKhamRepository extends JdbcDaoSupport {
    public ChiTietLichKhamRepository(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<ChiTietLichKham> getChiTietLichKhamByMaLK(Integer maLK) {
        String sql = "SELECT MaHS, MaLK, Chuandoan, Donthuoc, Ghichuthem FROM chitietlichkham WHERE MaLK = ?";

        try {
            return this.getJdbcTemplate().query(sql, new Object[]{maLK}, (rs, rowNum) -> {
                ChiTietLichKham dto = new ChiTietLichKham();
                dto.setMaHS(rs.getInt("MaHS"));
                dto.setMaLK(rs.getInt("MaLK"));
                dto.setChuanDoan(rs.getString("Chuandoan"));
                dto.setDonThuoc(rs.getString("Donthuoc"));
                dto.setGhiChuThem(rs.getString("Ghichuthem"));
                return dto;
            });
        } catch (DataAccessException e) {

            return null;
        }
    }
}
