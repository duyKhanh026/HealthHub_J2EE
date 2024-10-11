package com.healthhub.hospital.dao;

import java.util.List;

import javax.sql.DataSource;

import com.healthhub.hospital.model.BenhNhan;
import org.springframework.dao.DataAccessException;
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
        System.out.println("Đọc danh sách thành công");
        try {
            String sql = "SELECT * FROM benhnhan";
            return this.getJdbcTemplate().queryForRowSet(sql);
        }catch (DataAccessException e){
            throw new RuntimeException("Lỗi khi truy xuất danh sách bệnh nhân", e);
        }

    }

    public int addBenhNhan(BenhNhan benhNhan) {

        String sql = "INSERT INTO benhnhan (Hoten, Ngaysinh, Gioitinh, SDT, Email, Diachi, Tiensubenh) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            System.out.println("Thêm thành công");
            return this.getJdbcTemplate().update(sql,
                    benhNhan.getHoTen(),
                    benhNhan.getNgaySinh(),
                    benhNhan.getGioitinh(),
                    benhNhan.getSDT(),
                    benhNhan.getEmail(),
                    benhNhan.getDiachi(),
                    benhNhan.getTiensubenh());
        } catch (DataAccessException e) {
            // Log lỗi

            throw new RuntimeException("Lỗi khi thêm bệnh nhân mới", e);
        }
    }

}
