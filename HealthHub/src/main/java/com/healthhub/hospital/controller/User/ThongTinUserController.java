package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.Repository.TaiKhoanRepository;
import com.healthhub.hospital.service.BenhNhanService;

import com.healthhub.hospital.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/thongtinuser")
@SessionAttributes("hoTen")
public class ThongTinUserController {
    @Autowired
    private BenhNhanService benhnhanService;
    @Autowired
    private TaiKhoanService taiKhoanService;


    private BenhNhan benhnhan;
    @GetMapping
    public String getUserInfo(Model model, Authentication authentication) {

        TaiKhoan tk = taiKhoanService.GetTKByID(authentication.getName());
        
        benhnhan = benhnhanService.getBenhNhanById(tk.getBenhNhan().getMaBN());

        model.addAttribute("benhnhan", benhnhan);



        // Trả về tên view
        return "User/ThongTinUser";
    }
    @PostMapping
    public String updateUser(@ModelAttribute("benhnhan") BenhNhan bn, RedirectAttributes redirectAttributes) {
        // Kiểm tra xem số điện thoại đã tồn tại trong cơ sở dữ liệu hay chưa
        if (benhnhanService.isSDTExist(bn.getSDT())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Số điện thoại đã tồn tại trong hệ thống!");
            return "redirect:/thongtinuser"; // Chuyển hướng lại trang thông tin người dùng với thông báo lỗi
        }

        benhnhan.setHoTen(bn.getHoTen());
        benhnhan.setNgaySinh(bn.getNgaySinh());
        benhnhan.setGioitinh(bn.getGioitinh());
        benhnhan.setSDT(bn.getSDT());
        benhnhan.setEmail(bn.getEmail());
        benhnhan.setDiachi(bn.getDiachi());
        benhnhan.setTiensubenh(bn.getTiensubenh());
        // Lưu thông tin bệnh nhân vào database
        benhnhanService.LuuTTBenhNhan(benhnhan);
        redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin thành công!");
        // Chuyển hướng về trang cập nhật thông tin hoặc một trang khác
        return "redirect:/thongtinuser"; // Đường dẫn redirect về trang bạn muốn
    }


}
