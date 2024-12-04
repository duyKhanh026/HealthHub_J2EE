package com.healthhub.hospital.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "page_custom_index")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageCustomIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "page_name")
    private String pageName;

    @Column(name = "welcome_line")
    private String welcomeLine;
    
    @Column(name = "welcome_line_1")
    private String welcomeLine1;
    
    private String box1_1;
    
    private String box1_2;
    
    private String about_us;
    
    private String Location;
    
    private String ytburl;
    
    @Lob // Lưu dữ liệu lớn, như file nhị phân
    private byte[] imgdata;
    
    @Lob // Lưu dữ liệu lớn, như file nhị phân
    private byte[] imgdata2;

}
