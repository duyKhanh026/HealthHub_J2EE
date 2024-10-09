package com.healthhub.hospital.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.healthhub.hospital.model.BenhNhan;


@Repository
public interface BenhNhanRepository {
 
    List<BenhNhan> findAll();
    BenhNhan findById(Long id);
}