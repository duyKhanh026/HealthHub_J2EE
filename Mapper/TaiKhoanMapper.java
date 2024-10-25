package com.healthhub.hospital.Mapper;

import com.healthhub.hospital.Entity.TaiKhoan;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.ResultSet;

public class TaiKhoanMapper implements RowMapper<TaiKhoan> {

    public static final String BASE_SQL = "select * from taikhoan";


    @Override
    public TaiKhoan mapRow(ResultSet rs, int rowNum) throws SQLException {

        // Ánh xạ các cột từ ResultSet vào các trường của đối tượng TaiKhoan
        int maTK = rs.getInt("MaTK");  // Mã tài khoản
        String tenDN = rs.getString("TenDN");  // Tên đăng nhập
        String matkhau = rs.getString("Matkhau");  // Mật khẩu
        int maBN = rs.getInt("MaBN");  // Mã bệnh nhân (khóa ngoại)
        String vaitro = rs.getString("Vaitro");  // Vai trò

        // Trả về đối tượng TaiKhoan đã được khởi tạo với các giá trị trên
        return new TaiKhoan(maTK, tenDN, matkhau, maBN, vaitro);
    }
}
