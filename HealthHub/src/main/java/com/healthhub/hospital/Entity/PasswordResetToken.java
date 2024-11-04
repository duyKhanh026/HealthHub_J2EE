package com.healthhub.hospital.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "password_reset_token")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "MaBN")
    private BenhNhan benhNhan;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    // Constructor phù hợp
    public PasswordResetToken(String token, BenhNhan benhNhan, LocalDateTime expiryDate) {
        this.token = token;
        this.benhNhan = benhNhan;
        this.expiryDate = expiryDate;
    }
}
