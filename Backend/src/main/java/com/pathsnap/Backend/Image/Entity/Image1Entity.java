package com.pathsnap.Backend.Image.Entity;

import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhoto1Entity;
import com.pathsnap.Backend.User.Entity.User1Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image1")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image1Entity {
    @Id
    private String imageId;
    private String url;
    private String fileKey;

    @OneToOne(mappedBy = "image")
    private User1Entity user;
    @OneToOne(mappedBy = "image")
    private ImagePhoto1Entity imagePhotos;
}
