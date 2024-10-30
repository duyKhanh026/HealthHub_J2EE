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
    private TaiKhoanRepository tkRepo;

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



}
