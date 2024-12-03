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
                               Model model, RedirectAttributes redirectAttributes) {

        // Kiểm tra các trường không được để trống
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("username", username);
            model.addAttribute("SDT", SDT);
            model.addAttribute("password", password);
            model.addAttribute("confirmPassword", confirmPassword);
            model.addAttribute("modalMessage", "Tên đăng nhập không được để trống!");
            model.addAttribute("modalType", "error");
            return "User/DangKy";
        }
        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("username", username);
            model.addAttribute("SDT", SDT);
            model.addAttribute("password", password);
            model.addAttribute("confirmPassword", confirmPassword);
            model.addAttribute("modalMessage", "Mật khẩu không được để trống!");
            model.addAttribute("modalType", "error");
            return "User/DangKy";
        }
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            model.addAttribute("username", username);
            model.addAttribute("SDT", SDT);
            model.addAttribute("password", password);
            model.addAttribute("confirmPassword", confirmPassword);
            model.addAttribute("modalMessage", "Mật khẩu xác nhận không được để trống!");
            model.addAttribute("modalType", "error");
            return "User/DangKy";
        }
        if (SDT == null || SDT.trim().isEmpty()) {
            model.addAttribute("username", username);
            model.addAttribute("SDT", SDT);
            model.addAttribute("password", password);
            model.addAttribute("confirmPassword", confirmPassword);
            model.addAttribute("modalMessage", "Số điện thoại không được để trống!");
            model.addAttribute("modalType", "error");
            return "User/DangKy";
        }

        // Kiểm tra mật khẩu và mật khẩu xác nhận có trùng nhau không
        if (!password.equals(confirmPassword)) {
            model.addAttribute("username", username);
            model.addAttribute("SDT", SDT);
            model.addAttribute("password", password);
            model.addAttribute("confirmPassword", confirmPassword);
            model.addAttribute("modalMessage", "Mật khẩu không khớp!");
            model.addAttribute("modalType", "error");
            return "User/DangKy";
        }

        // Kiểm tra tên đăng nhập đã tồn tại chưa
        TaiKhoan existingAccount = taiKhoanService.findByTenDN(username);
        if (existingAccount != null) {
            model.addAttribute("username", username);
            model.addAttribute("SDT", SDT);
            model.addAttribute("password", password);
            model.addAttribute("confirmPassword", confirmPassword);
            model.addAttribute("modalMessage", "Tên đăng nhập đã tồn tại!");
            model.addAttribute("modalType", "error");
            return "User/DangKy";
        }

        // Kiểm tra số điện thoại đã tồn tại trong bảng BenhNhan
        BenhNhan existingBenhNhan = benhnhanService.findBySDT(SDT);
        BenhNhan benhNhan;

        if (existingBenhNhan != null) {
            // Nếu số điện thoại đã có trong hệ thống, kiểm tra xem đã có tài khoản chưa
            TaiKhoan existingTaiKhoan = taiKhoanService.findByMaBN(existingBenhNhan);

            if (existingTaiKhoan != null) {
                // Nếu đã có tài khoản cho bệnh nhân này, giữ lại tài khoản cũ
                model.addAttribute("modalMessage", "Số điện thoại này đã được đăng ký tài khoản.");
                model.addAttribute("modalType", "error");
                return "User/DangKy";
            }

            // Nếu chưa có tài khoản, tạo tài khoản mới
            benhNhan = existingBenhNhan;
        } else {
            // Nếu số điện thoại chưa có trong hệ thống, tạo bệnh nhân mới
            if (!isValidPhoneNumber(SDT)) {
                model.addAttribute("username", username); // Giữ lại tên đăng nhập
                model.addAttribute("SDT", SDT); // Giữ lại số điện thoại
                model.addAttribute("modalMessage", "Số điện thoại không hợp lệ!");
                model.addAttribute("modalType", "error");
                return "User/DangKy";
            }

            benhNhan = new BenhNhan();
            benhNhan.setSDT(SDT);
            benhnhanService.LuuTTBenhNhan(benhNhan);
        }

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenDN(username);
        taiKhoan.setMatkhau(passwordEncoder.encode(password)); // Mã hóa mật khẩu
        taiKhoan.setVaitro(Role.USER); // Gán vai trò mặc định
        taiKhoan.setBenhNhan(benhNhan); // Ánh xạ với BenhNhan
        taiKhoanService.LuuTTTaiKhoan(taiKhoan);

        // Thêm thông báo thành công và redirect đến trang đăng nhập
        redirectAttributes.addFlashAttribute("modalMessage", "Đăng ký thành công!");
        redirectAttributes.addFlashAttribute("modalType", "success");
        return "redirect:/login";
    }

    // Hàm kiểm tra số điện thoại có hợp lệ không
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Kiểm tra số điện thoại có đúng định dạng (ví dụ: 10 chữ số)
        String regex = "^\\d{10}$";
        return phoneNumber.matches(regex);
    }

}
