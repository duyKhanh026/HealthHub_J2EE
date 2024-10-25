//package com.healthhub.hospital.service;
//
//import com.healthhub.hospital.Repository.TaiKhoanRepository;
//import com.healthhub.hospital.Entity.TaiKhoan;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private TaiKhoanRepository accountRepository;
//
//
//    @SuppressWarnings("serial")
//	@Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        TaiKhoan user = accountRepository.getAccountByUserName(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//            return new CustomUserDetails(user) {
//        };
//        // Create a custom UserDetails implementation
//    }
//}
