package com.healthhub.hospital.Repository;

import java.util.List;

import com.healthhub.hospital.Entity.BenhNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BenhNhanRepository extends JpaRepository<BenhNhan, Integer>{



}
