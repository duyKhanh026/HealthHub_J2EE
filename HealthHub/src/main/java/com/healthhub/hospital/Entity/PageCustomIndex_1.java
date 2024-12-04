package com.healthhub.hospital.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "page_custom_index_1")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageCustomIndex_1 {

	@Id
    private Long id_b;

    @Column(nullable = false) // Box name should be a required field
    private String boxName;

    @Column(nullable = false) // Box content should be a required field
    private String boxContent;

}
