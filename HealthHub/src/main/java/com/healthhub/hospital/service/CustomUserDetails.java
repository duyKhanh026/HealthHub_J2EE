package com.healthhub.hospital.service;
import com.healthhub.hospital.Entity.TaiKhoan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails{
    private final TaiKhoan account;

    public CustomUserDetails(TaiKhoan account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implement logic to retrieve user authorities (roles) from your data source
        // You can use a separate table or logic within Account to define roles
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Example: Add a default role for now
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getMatkhau();
    }

    @Override
    public String getUsername() {
        return account.getTenDN();
    }
}
