package com.healthhub.hospital.dao;

import com.healthhub.hospital.model.LichKham;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.util.List;

public class LichKhamRepository extends JdbcDaoSupport {

    public LichKhamRepository(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

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
}
