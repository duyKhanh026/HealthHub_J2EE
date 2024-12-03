package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.Repository.TaiKhoanRepository;
import com.healthhub.hospital.service.BenhNhanService;

import com.healthhub.hospital.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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


        // Thêm thông tin bệnh nhân vào mô hình
        model.addAttribute("benhnhan", benhnhan);

        // Trả về tên view
        return "User/ThongTinUser";
    }
    @PostMapping
    public String updateUser(@ModelAttribute("benhnhan") BenhNhan bn, RedirectAttributes redirectAttributes) {
        // Lấy số điện thoại hiện tại của bệnh nhân
        String currentPhoneNumber = benhnhan.getSDT();

        // Kiểm tra độ dài của số điện thoại
        if (bn.getSDT().length() != 10 || !bn.getSDT().matches("\\d+")) {
            redirectAttributes.addFlashAttribute("errorMessage", "Số điện thoại phải bao gồm đúng 10 chữ số!");
            return "redirect:/thongtinuser"; // Chuyển hướng lại trang thông tin người dùng với thông báo lỗi
        }
        // Nếu số điện thoại mới khác số điện thoại hiện tại, kiểm tra xem nó có tồn tại trong cơ sở dữ liệu không
        if (!bn.getSDT().equals(currentPhoneNumber) && benhnhanService.isSDTExist(bn.getSDT())) {
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
