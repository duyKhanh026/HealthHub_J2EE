package com.healthhub.hospital.config;

import com.healthhub.hospital.Entity.Role;
import com.healthhub.hospital.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class AppConfig{
    // Inject the custom UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    // Use BCrypt password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configure DaoAuthenticationProvider to use custom UserDetailsService and password encoder
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    //Create AuthenticationSuccessHandler to redirect based on role
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, IOException {
                boolean isBacSi = authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_BACSI"));

                if (isBacSi) {
                    response.sendRedirect("DSLichKham");  // Redirect to admin page if role is bacsi
                } else {
                    response.sendRedirect("index");   // Redirect to user page if role is not bacsi
                }
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/","/anhcuatao/**", "/index", "/assets2/**", "/css/**", "/assets/**"
                                ,"DSLichKham","/ChiTietBenhNhan","/ThongTinLichKham","/register","/send_email","/ThongTinUser","/DatLichKham"
                                ,"/DSBenhNhan", "/ThanhToan","/forgot_password","/reset_password","/api/getAvailableTimes","/NgayNghi","/NgayNghi/xoa").permitAll()
                        .requestMatchers("/forgot_password").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(myAuthenticationSuccessHandler())
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/index")
                        .permitAll())
                .rememberMe((rememberMe) -> rememberMe
                        .key("5bZUZjoAB21JT1gYRkfm")  // Khóa dùng để mã hóa cookie remember-me
                        .tokenValiditySeconds(86400)  // Thời gian hiệu lực của cookie (ở đây là 24 giờ)
                        .rememberMeParameter("remember-me")  // Tên tham số của checkbox "Remember Me"
                        .userDetailsService(userDetailsService())  // Thêm UserDetailsService cho Remember Me
                );
        return http.build();
    }
}