package com.pathsnap.Backend.Oauth2Login.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "refresh1")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String refresh;
    private String expiration;

}
