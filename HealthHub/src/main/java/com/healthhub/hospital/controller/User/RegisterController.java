package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.Role;
import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.Repository.TaiKhoanRepository;
import com.healthhub.hospital.Entity.TaiKhoan;
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
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private BenhNhanRepository benhNhanRepository; // Thêm BenhNhanRepository

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
        TaiKhoan existingAccount = taiKhoanRepository.findByTenDN(username);
        if (existingAccount != null) {
            redirectAttributes.addFlashAttribute("modalMessage", "Tên đăng nhập đã tồn tại!");
            redirectAttributes.addFlashAttribute("modalType", "danger");
            return "redirect:/register";
        }

        // Kiểm tra xem số điện thoại đã tồn tại chưa
        BenhNhan existingBenhNhan = benhNhanRepository.findBySDT(SDT);
        if (existingBenhNhan != null) {
            redirectAttributes.addFlashAttribute("modalMessage", "Số điện thoại đã tồn tại!");
            redirectAttributes.addFlashAttribute("modalType", "danger");
            return "redirect:/register";
        }

        // Tạo và lưu bệnh nhân mới với các trường trống
        BenhNhan benhNhan = new BenhNhan();
        benhNhan.setSDT(SDT);
        benhNhanRepository.save(benhNhan);

        // Tạo tài khoản mới
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenDN(username);
        taiKhoan.setMatkhau(passwordEncoder.encode(password)); // Mã hóa mật khẩu
        taiKhoan.setVaitro(Role.USER); // Gán vai trò mặc định
        taiKhoan.setBenhNhan(benhNhan);
        taiKhoanRepository.save(taiKhoan);

        // Thêm thông báo thành công và redirect đến trang đăng nhập
        redirectAttributes.addFlashAttribute("modalMessage", "Đăng ký thành công!");
        redirectAttributes.addFlashAttribute("modalType", "success");
        return "redirect:/login";
    }

}
