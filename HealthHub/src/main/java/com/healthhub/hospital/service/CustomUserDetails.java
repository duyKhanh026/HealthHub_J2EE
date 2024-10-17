package com.healthhub.hospital.service;

import com.healthhub.hospital.model.TaiKhoan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor
    public CustomUserDetails(TaiKhoan taiKhoan) {
        this.username = taiKhoan.getTenDN();
        this.password = taiKhoan.getMatkhau();
        // Phân quyền
        this.authorities = List.of(() -> taiKhoan.getVaitro()); // Hoặc bạn có thể chuyển đổi vai trò thành GrantedAuthority
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Thay đổi nếu bạn có chính sách hết hạn tài khoản
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Thay đổi nếu bạn có chính sách khóa tài khoản
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Thay đổi nếu bạn có chính sách hết hạn thông tin xác thực
    }

    @Override
    public boolean isEnabled() {
        return true; // Thay đổi nếu bạn có chính sách kích hoạt tài khoản
    }
}
