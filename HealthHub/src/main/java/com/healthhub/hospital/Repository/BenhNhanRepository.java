package com.healthhub.hospital.Repository;

import java.util.List;

import com.healthhub.hospital.Entity.BenhNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BenhNhanRepository extends JpaRepository<BenhNhan, Integer>{
	
//	List<BenhNhan> findByTitle(String title);
	
	
//	@Query("SELECT b.hoten FROM BenhNhan b")
//    List<String> findAllHoten();
//
//	@Query("SELECT e.maBN, e.hoten, e.ngaySinh FROM BenhNhan e")
//    List<Object[]> getBenhNhanSqlRowSet();
    
//  Note:  Sài findALl() thay thế phương thức bên dưới
//    public SqlRowSet getinforBenhNhan() {
//        System.out.println("Đọc danh sách thành công");
//        try {
//            String sql = "SELECT * FROM benhnhan";
//            return this.getJdbcTemplate().queryForRowSet(sql);
//        }catch (DataAccessException e){
//            throw new RuntimeException("Lỗi khi truy xuất danh sách bệnh nhân", e);
//        }
//    }

//   Note: save(<Thằng bệnh nào đó>) 
//    public int addBenhNhan(BenhNhan benhNhan) {
//
//        String sql = "INSERT INTO benhnhan (Hoten, Ngaysinh, Gioitinh, SDT, Email, Diachi, Tiensubenh) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        try {
//            System.out.println("Thêm thành công");
//            return this.getJdbcTemplate().update(sql,
//                    benhNhan.getHoTen(),
//                    benhNhan.getNgaySinh(),
//                    benhNhan.getGioitinh(),
//                    benhNhan.getSDT(),
//                    benhNhan.getEmail(),
//                    benhNhan.getDiachi(),
//                    benhNhan.getTiensubenh());
//        } catch (DataAccessException e) {
//            // Log lỗi
//
//            throw new RuntimeException("Lỗi khi thêm bệnh nhân mới", e);
//        }
//    }
    
//    Note: findById(id).orElse(null);
//    public BenhNhan getBenhNhanById(Integer id) {
//        String sql = "SELECT * FROM benhnhan WHERE MaBN = ?";
//        try {
//            return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
//                BenhNhan bn = new BenhNhan();
//                bn.setMaBN(rs.getInt("MaBN"));
//                bn.setHoTen(rs.getString("Hoten"));
//                bn.setNgaySinh(rs.getDate("Ngaysinh"));
//                bn.setGioitinh(rs.getString("Gioitinh"));
//                bn.setSDT(rs.getString("SDT"));
//                bn.setEmail(rs.getString("Email"));
//                bn.setDiachi(rs.getString("Diachi"));
//                bn.setTiensubenh(rs.getString("Tiensubenh"));
//                return bn;
//            });
//        } catch (DataAccessException e) {
//            logger.error("Không tìm thấy bệnh nhân với MaBN {}: {}");
//            return null;
//        }
//    }
    
// edit thì sài save() để ghi đề, dùng existsById(benhNhan.getMaBN()) để kiểm tra đã tồn tại chưa
}
