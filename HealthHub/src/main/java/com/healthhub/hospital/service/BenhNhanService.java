package com.healthhub.hospital.service;

import com.healthhub.hospital.Repository.BenhNhanDAO;
import com.healthhub.hospital.model.BenhNhan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BenhNhanService {
    private final BenhNhanDAO benhNhanDAO;

    @Autowired
    public BenhNhanService(BenhNhanDAO benhNhanDAO) {
        this.benhNhanDAO = benhNhanDAO;
    }

    public List<BenhNhan> getAllBenhNhan() {
        SqlRowSet rowSet = benhNhanDAO.getinforBenhNhan();
        List<BenhNhan> benhnhans = new ArrayList<>();
        while (rowSet.next()) {
            BenhNhan bn = new BenhNhan();
            bn.setMaBN(rowSet.getInt("MaBN"));
            bn.setHoTen(rowSet.getString("Hoten"));
            bn.setNgaySinh(rowSet.getDate("Ngaysinh"));
            bn.setGioitinh(rowSet.getString("Gioitinh"));
            bn.setSDT(rowSet.getString("SDT"));
            bn.setEmail(rowSet.getString("Email"));
            bn.setDiachi(rowSet.getString("Diachi"));
            bn.setTiensubenh(rowSet.getString("Tiensubenh"));
            benhnhans.add(bn);
        }
        return benhnhans;
    }

    public void addBenhNhan(BenhNhan benhNhan) {
        benhNhanDAO.addBenhNhan(benhNhan);
    }

    public BenhNhan getBenhNhanById(Integer id) {
        return benhNhanDAO.getBenhNhanById(id);
    }

    public void updateBenhNhan(BenhNhan benhNhan) {
        benhNhanDAO.updateBenhNhan(benhNhan);
    }
}
