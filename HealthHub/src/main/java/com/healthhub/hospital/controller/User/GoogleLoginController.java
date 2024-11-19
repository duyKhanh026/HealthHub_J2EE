package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.Role;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoogleLoginController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private BenhNhanService benhNhanService;

    @GetMapping("/login-success")
    public String handleGoogleLogin(Authentication authentication, Model model) {
        // Lấy thông tin từ Google OAuth2User
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        // Kiểm tra email có tồn tại trong bảng BenhNhan không
        BenhNhan benhNhan = benhNhanService.findByEmail(email);

        if (benhNhan == null) {
            // Nếu không tồn tại, tạo mới BenhNhan và TaiKhoan
            BenhNhan newBenhNhan = new BenhNhan();
            newBenhNhan.setHoTen(name);
            newBenhNhan.setEmail(email);

            // Lưu thông tin bệnh nhân vào database
            benhNhanService.LuuTTBenhNhan(newBenhNhan);

            // Tạo tài khoản liên kết với bệnh nhân
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setTenDN(email); // Dùng email làm tên đăng nhập
            taiKhoan.setMatkhau(""); // Không cần mật khẩu
            taiKhoan.setVaitro(Role.USER); // Vai trò mặc định
            taiKhoan.setBenhNhan(newBenhNhan);

            taiKhoanService.LuuTTTaiKhoan(taiKhoan);

            model.addAttribute("message", "Tài khoản Google đã được tạo thành công!");
        }

        return "redirect:/index";
    }
}

