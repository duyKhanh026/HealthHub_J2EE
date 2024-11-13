package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.ChiTietLichKham;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.Repository.ChiTietLichKhamRepository;
import com.healthhub.hospital.Repository.LichKhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class ChiTietLichKhamController {
    @Autowired
    private LichKhamRepository lichKhamRepository;
    @Autowired
    private ChiTietLichKhamRepository chiTietLichKhamRepository;

    @GetMapping("/lichkham/details/{maLK}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getLichKhamDetail(@PathVariable int maLK) {
        Optional<LichKham> lichKham = lichKhamRepository.findById(maLK);
        Map<String, Object> response = new HashMap<>();

        if (lichKham.isPresent()) {
            response.put("maLK", lichKham.get().getMaLK());
            response.put("hoten", lichKham.get().getHoten());
            response.put("email", lichKham.get().getEmail());
            response.put("sDT", lichKham.get().getSDT());
            response.put("note", lichKham.get().getNote());
            response.put("ngayGioDatKham", lichKham.get().getNgayGioDatKham());
            response.put("trangThai", lichKham.get().getTrangThai());
            if ("Đã khám".equals(lichKham.get().getTrangThai())) {
                ChiTietLichKham chiTiet = chiTietLichKhamRepository.findByLichKham_MaLK(lichKham.get().getMaLK());
                if (chiTiet != null) {
                    response.put("maHS", chiTiet.getMaHS());
                    response.put("chuandoan", chiTiet.getChuanDoan());
                    response.put("donthuoc", chiTiet.getDonThuoc());
                    response.put("dando", chiTiet.getGhiChuThem());
                }
            }

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
