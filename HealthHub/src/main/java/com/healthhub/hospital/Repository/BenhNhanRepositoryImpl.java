package com.healthhub.hospital.Repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.healthhub.hospital.model.BenhNhan;

@Repository
public class BenhNhanRepositoryImpl implements BenhNhanRepository {
 
    private JdbcTemplate jdbcTemplate;
 
    public BenhNhanRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
 
    @Override
    public List<BenhNhan> findAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BenhNhan.class));
    }
 
    @SuppressWarnings("deprecation")
	@Override
    public BenhNhan findById(Long id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(BenhNhan.class));
    }

}