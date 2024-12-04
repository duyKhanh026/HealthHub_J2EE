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
@Table(name = "page_custom_gallery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageCustomGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id tự động tăng
    private int id;
    private String url;
}
