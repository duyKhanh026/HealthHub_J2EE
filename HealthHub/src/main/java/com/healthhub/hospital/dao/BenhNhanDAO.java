package com.healthhub.hospital.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class BenhNhanDAO extends JdbcDaoSupport{
	@Autowired
    public BenhNhanDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	public List<String> getBenhNhan() {

        String sql = "Select b.MaBN from benhnhan b ";

        // queryForList(String sql, Class<T> elementType)
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);

        return list;
    }

}
