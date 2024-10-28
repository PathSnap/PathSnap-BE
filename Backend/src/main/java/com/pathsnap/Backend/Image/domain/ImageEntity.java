package com.pathsnap.Backend.Image.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Image")
@Data
public class ImageEntity {
    @Id
    private String imageId;
    private String url;
}
