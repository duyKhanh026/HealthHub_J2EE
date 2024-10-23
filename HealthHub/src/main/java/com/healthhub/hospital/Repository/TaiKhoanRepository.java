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

    public TaiKhoan getAccountByUserName(String TenDN) {
        String sql = TaiKhoanMapper.BASE_SQL + " where TenDN = ?";  // Sử dụng ? làm placeholder

        Object[] params = new Object[]{ TenDN };  // Truyền tham số userName vào mảng params
        TaiKhoanMapper taikhoanMapper = new TaiKhoanMapper();
        try{
            TaiKhoan account = this.getJdbcTemplate().queryForObject(sql, params, taikhoanMapper);
            return account;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void save(TaiKhoan taiKhoan) {
        // SQL để thêm một bệnh nhân mới vào bảng benhnhan, chỉ thêm maBN (auto increment)
        String insertBenhNhanSql = "INSERT INTO benhnhan() VALUES ()";

        // SQL để thêm tài khoản mới vào bảng taikhoan, với TenDN, Matkhau, MaBN, Vaitro
        String insertTaiKhoanSql = "INSERT INTO taikhoan (TenDN, Matkhau, MaBN, Vaitro) VALUES (?, ?, ?, ?)";

        // Bắt đầu giao dịch
        try {
            // Thêm mới bệnh nhân vào bảng benhnhan (chỉ có maBN tự động sinh)
            this.getJdbcTemplate().update(insertBenhNhanSql);

            // Lấy ra giá trị maBN vừa được tạo từ bảng benhnhan
            String getLastInsertIdSql = "SELECT LAST_INSERT_ID()";
            int maBN = this.getJdbcTemplate().queryForObject(getLastInsertIdSql, Integer.class);

            // Gán maBN cho đối tượng TaiKhoan
            taiKhoan.setMaBN(maBN);

            // Chèn mới tài khoản vào bảng taikhoan
            Object[] params = new Object[]{ taiKhoan.getTenDN(), taiKhoan.getMatkhau(), taiKhoan.getMaBN(), taiKhoan.getVaitro() };
            this.getJdbcTemplate().update(insertTaiKhoanSql, params);

        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi trong quá trình thêm dữ liệu
            throw new RuntimeException("Error when saving account and patient: " + e.getMessage());
        }
    }


}
