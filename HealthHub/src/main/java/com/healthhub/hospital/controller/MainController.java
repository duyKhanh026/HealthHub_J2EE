package com.healthhub.hospital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthhub.hospital.config.AppConfig;
import com.healthhub.hospital.dao.BenhNhanDAO;
import com.healthhub.hospital.dao.ChiTietLichKhamRepository;
import com.healthhub.hospital.dao.LichKhamRepository;
import com.healthhub.hospital.model.BenhNhan;
import com.healthhub.hospital.model.ChiTietLichKham;
import com.healthhub.hospital.model.LichKham;
import com.healthhub.hospital.service.ChiTietBenhNhanService;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;

@Controller
public class MainController {

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;
	
	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		
		BenhNhanDAO dao = context.getBean(BenhNhanDAO.class);
		SqlRowSet rowSet = dao.getBenhNhanSqlRowSet();
		List<BenhNhan> benhnhans = new ArrayList<BenhNhan>();
		while (rowSet.next()) {
			BenhNhan bn = new BenhNhan();
			bn.setMaBN(rowSet.getInt(1));
			bn.setHoTen(rowSet.getString(2));
			bn.setNgaySinh(rowSet.getDate(3));
			benhnhans.add(bn);
        }
		model.addAttribute("benhnhans", benhnhans);

		return "User/index";
	}

	@GetMapping({ "/LichKhamLS" })
	public String lichKham(Model model) {
		LichKhamService lksv = new LichKhamService(context.getBean(LichKhamRepository.class));
		List<LichKham> listkham = new ArrayList<>();
		listkham = lksv.getAllLichKham();
		model.addAttribute("listkham", listkham);
		return "User/LichKham";
	}
	@GetMapping("/LichKhamLS")
    public String chiTietBenhNhan(@RequestParam("id") Integer id, Model model) {
		ChiTietLichKhamService lksv = new ChiTietLichKhamService(context.getBean(ChiTietLichKhamRepository.class));
        // Lấy thông tin bệnh nhân từ service
        LichKham lk = (LichKham) lksv.getChiTietLichKhamByMaLK(id);

        if (lk == null) {
            return "error/404";
        }

        model.addAttribute("lickkhamchitiet", lk);

        return "User/LichKham";
    }
	
	@GetMapping({ "/Login" })
	public String login(Model model) {

		return "User/DangNhap";
	}

	@GetMapping({ "/make_appointment" })
	public String make_appoint(Model model) {

		return "User/make_appointment";
	}


}