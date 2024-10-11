package com.healthhub.hospital.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class BenhNhanDAO extends JdbcDaoSupport{
	
    public BenhNhanDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	public List<String> getBenhNhan() {

        String sql = "Select b.Hoten from benhnhan b ";

        // queryForList(String sql, Class<T> elementType)
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);

        return list;
    }

	public SqlRowSet getBenhNhanSqlRowSet() {

		String sql = "Select e.MaBN,e.Hoten,e.Ngaysinh From benhnhan e ";

        // SqlRowSet queryForRowSet(String sql)
        SqlRowSet rowSet = this.getJdbcTemplate().queryForRowSet(sql);

        return rowSet;

    }

    public SqlRowSet getinforBenhNhan() {
        String sql = "SELECT * FROM benhnhan";

        return this.getJdbcTemplate().queryForRowSet(sql);
    }


}
