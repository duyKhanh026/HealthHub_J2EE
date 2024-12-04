package com.healthhub.hospital.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "page_custom_about_us")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageCustomAboutUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id tự động tăng
    private int id_b;
    private String iconlink;
    private String title;
    private String content;
}
