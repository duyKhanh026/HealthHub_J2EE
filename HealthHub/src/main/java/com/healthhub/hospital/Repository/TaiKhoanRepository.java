package com.healthhub.hospital.Repository;

import com.healthhub.hospital.Mapper.TaiKhoanMapper;
import com.healthhub.hospital.model.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class TaiKhoanRepository extends JdbcDaoSupport {
    @Autowired
    public TaiKhoanRepository(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<TaiKhoan> getListAccount() {
        String sql = TaiKhoanMapper.BASE_SQL;

        Object[] params = new Object[]{};
        TaiKhoanMapper taikhoanMapper = new TaiKhoanMapper();
        List<TaiKhoan> list = this.getJdbcTemplate().query(sql, params, taikhoanMapper);

        return list;
    }

    public TaiKhoan getAccountByUserName(String email) {
        String sql = TaiKhoanMapper.BASE_SQL + " where TenDN = ?";  // Sử dụng ? làm placeholder

        Object[] params = new Object[]{ email };  // Truyền tham số userName vào mảng params
        TaiKhoanMapper taikhoanMapper = new TaiKhoanMapper();
        try{
            TaiKhoan account = this.getJdbcTemplate().queryForObject(sql, params, taikhoanMapper);
            return account;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
