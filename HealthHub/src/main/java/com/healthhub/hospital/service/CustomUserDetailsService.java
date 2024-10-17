package com.healthhub.hospital.service;

import com.healthhub.hospital.dao.TaiKhoanDAO;
import com.healthhub.hospital.model.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TaiKhoanDAO taiKhoanDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanDAO.findByUsername(username);
        if (taiKhoan == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Trả về đối tượng CustomUserDetails
        return new CustomUserDetails(taiKhoan);
    }
}
