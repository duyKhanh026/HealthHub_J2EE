package com.healthhub.hospital.controller.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthhub.hospital.Entity.*;
import com.healthhub.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("hoTen")
public class LichKhamController {
    @Autowired
    private LichKhamService LKBService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private BenhNhanService benhnhanService;
    private BenhNhan benhnhan;

    @Autowired
    private PageCustomIndexService pageCustomIndexService;
    @Autowired
    private PageCustomIndex_1Service pageCustomIndex_1Service;

    @GetMapping("/lichkhambenh")
    public String danhSachLichKham(Model model, Authentication authentication) {
        // Kiểm tra xem người dùng đang đăng nhập bằng tài khoản Google hay tài khoản mật khẩu thông thường
        if (authentication.getPrincipal() instanceof OAuth2User) {
            // Nếu đăng nhập bằng Google, lấy thông tin từ OAuth2User
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            String email = oauth2User.getAttribute("email");

            // Tìm bệnh nhân theo email từ Google
            benhnhan = benhnhanService.findByEmail(email);
        } else {
            // Nếu đăng nhập bằng tài khoản mật khẩu thông thường
            TaiKhoan tk = taiKhoanService.findByTenDN(authentication.getName());
            benhnhan = benhnhanService.getBenhNhanById(tk.getBenhNhan().getMaBN());
        }
        List<LichKham> lichKhamList = LKBService.getLichKhamByMaBN(benhnhan.getMaBN());

        model.addAttribute("lichKhamList", lichKhamList);
        PageCustomIndex page = pageCustomIndexService.getFirstPageCustomIndex();
        List<PageCustomIndex_1> page_1 = pageCustomIndex_1Service.findAll();

        model.addAttribute("page", page);

        model.addAttribute("page_1", page_1);
        return "User/LichKhamBenh"; // Tên của view HTML bạn muốn render
    }

    @GetMapping("/api/lich-kham/filter")
    public ResponseEntity<List<LichKham>> filterLichKhamByDateRange(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {
        try {
            // Chuyển đổi chuỗi thành LocalDate
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);

            // Lấy thông tin người dùng hiện tại
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            TaiKhoan tk = taiKhoanService.findByTenDN(authentication.getName());
            benhnhan = benhnhanService.getBenhNhanById(tk.getBenhNhan().getMaBN());

            List<LichKham> lichKhamList = LKBService.filterLichKhamByDateRange(startDate, endDate, benhnhan.getMaBN());

            return ResponseEntity.ok(lichKhamList); // Trả về danh sách các lịch khám
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build(); // Trả về lỗi nếu có vấn đề
        }
    }

    @GetMapping("/lichkham/all")
    public ResponseEntity<List<LichKham>> getAllLichKham() {
        // Lấy thông tin người dùng hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TaiKhoan tk = taiKhoanService.findByTenDN(authentication.getName());
        benhnhan = benhnhanService.getBenhNhanById(tk.getBenhNhan().getMaBN());
        List<LichKham> lichKhamList = LKBService.getLichKhamByMaBN(benhnhan.getMaBN());
        return ResponseEntity.ok(lichKhamList);
    }

}
