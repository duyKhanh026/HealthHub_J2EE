package com.healthhub.hospital.service;

import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Service
public class BenhNhanService {

    @Autowired
    private BenhNhanRepository benhNhanrepo;

    public BenhNhanService(BenhNhanRepository benhNhanrepo) {
        this.benhNhanrepo = benhNhanrepo;
    }

    public List<BenhNhan> getAllBenhNhan() {
    	return benhNhanrepo.findAll();
    }

    public void updateBenhNhan(BenhNhan benhNhan) {
        benhNhanrepo.save(benhNhan); // This will update if the entity ID already exists
    }


    public BenhNhan getBenhNhanById(Integer id) {
        return benhNhanrepo.findById(id).orElse(null);
    }


    public void LuuTTBenhNhan(BenhNhan bn) {
        benhNhanrepo.save(bn);
    }


    public boolean isSDTExist(String sdt) {
        // Giả sử bạn sử dụng repository hoặc DAO để truy vấn cơ sở dữ liệu
        BenhNhan benhNhan = benhNhanrepo.findBySDT(sdt);  // Phương thức tìm bệnh nhân theo số điện thoại
        return benhNhan != null;  // Nếu tìm thấy bệnh nhân, tức là số điện thoại đã tồn tại
    }


}
