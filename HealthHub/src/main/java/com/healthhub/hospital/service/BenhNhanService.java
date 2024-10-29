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

    public void updateBenhNhan(BenhNhan benhNhan) {
        benhNhanrepo.save(benhNhan); // This will update if the entity ID already exists
    }


    public BenhNhan getBenhNhanById(Integer id) {
        return benhNhanrepo.findById(id).orElse(null);
    }




}
