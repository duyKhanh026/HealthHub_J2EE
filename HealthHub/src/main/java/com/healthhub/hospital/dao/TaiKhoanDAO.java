package com.healthhub.hospital.dao;

import com.healthhub.hospital.model.TaiKhoan;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaiKhoanDAO {

    private final JdbcTemplate jdbcTemplate;

    public TaiKhoanDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Truy vấn để tìm tài khoản theo tên đăng nhập
    public TaiKhoan findByUsername(String tenDN) {
        String sql = "SELECT * FROM taikhoan WHERE TenDN = ?"; // Câu lệnh SQL để tìm tài khoản
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{tenDN}, new BeanPropertyRowMapper<>(TaiKhoan.class));
        } catch (Exception e) {
            return null; // Trường hợp không tìm thấy tài khoản
        }
    }
}
