package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.Role;
import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.Repository.TaiKhoanRepository;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private BenhNhanService benhnhanService;

    @GetMapping({ "/register" })
    public String register(Model model) {
        return "User/DangKy";

    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("confirmPassword") String confirmPassword,
                               @RequestParam("SDT") String SDT,
                               RedirectAttributes redirectAttributes) {
        // Kiểm tra mật khẩu và mật khẩu xác nhận có trùng nhau không
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("modalMessage", "Mật khẩu không khớp!");
            redirectAttributes.addFlashAttribute("modalType", "danger"); // error
            return "redirect:/register";
        }

        // Kiểm tra xem tên đăng nhập đã tồn tại chưa
        TaiKhoan existingAccount = taiKhoanService.findByTenDN(username);
        if (existingAccount != null) {
            redirectAttributes.addFlashAttribute("modalMessage", "Tên đăng nhập đã tồn tại!");
            redirectAttributes.addFlashAttribute("modalType", "danger");
            return "redirect:/register";
        }

        // Kiểm tra xem số điện thoại đã tồn tại trong bảng BenhNhan chưa
        BenhNhan existingBenhNhan = benhnhanService.findBySDT(SDT);
        BenhNhan benhNhan;

        if (existingBenhNhan != null) {
            // Nếu số điện thoại đã tồn tại, sử dụng BenhNhan hiện có
            benhNhan = existingBenhNhan;
        } else {
            // Nếu số điện thoại chưa tồn tại, tạo một BenhNhan mới với SDT
            benhNhan = new BenhNhan();
            benhNhan.setSDT(SDT);
            benhnhanService.LuuTTBenhNhan(benhNhan);
        }

        // Tạo tài khoản mới và ánh xạ với BenhNhan tương ứng
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenDN(username);
        taiKhoan.setMatkhau(passwordEncoder.encode(password)); // Mã hóa mật khẩu
        taiKhoan.setVaitro(Role.USER); // Gán vai trò mặc định
        taiKhoan.setBenhNhan(benhNhan); // Ánh xạ với BenhNhan tương ứng
        taiKhoanService.LuuTTTaiKhoan(taiKhoan);

        // Thêm thông báo thành công và redirect đến trang đăng nhập
        redirectAttributes.addFlashAttribute("modalMessage", "Đăng ký thành công!");
        redirectAttributes.addFlashAttribute("modalType", "success");
        return "redirect:/login";
    }

}
