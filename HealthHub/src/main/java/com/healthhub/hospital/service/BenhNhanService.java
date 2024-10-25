package com.healthhub.hospital.service;

import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.Entity.BenhNhan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BenhNhanService {
    private final BenhNhanRepository benhNhanrepo;

    public BenhNhanService(BenhNhanRepository benhNhanrepo) {
        this.benhNhanrepo = benhNhanrepo;
    }

    public List<BenhNhan> getAllBenhNhan() {
    	return benhNhanrepo.findAll();
    }

    public void addBenhNhan(BenhNhan benhNhan) {
    	benhNhanrepo.save(benhNhan);
    }

    public BenhNhan getBenhNhanById(Integer id) {
        return benhNhanrepo.findById(id).orElse(null);
    }

    public void updateBenhNhan(BenhNhan benhNhan) {
    	try {
            // Kiểm tra nếu bệnh nhân đã tồn tại (dựa trên MaBN)
            if (benhNhanrepo.existsById(benhNhan.getMaBN())) {
                System.out.println("Cập nhật bệnh nhân thành công");
                benhNhanrepo.save(benhNhan);
            } else {
                throw new RuntimeException("Không tìm thấy bệnh nhân với MaBN: " + benhNhan.getMaBN());
            }
        } catch (Exception e) {
        	// gọi message 
            throw new RuntimeException("Lỗi khi cập nhật bệnh nhân", e);
        }
    }
}
