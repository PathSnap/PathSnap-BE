package com.pathsnap.Backend.Friend.Entity;

import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.User.Entity.User1Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friend1")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Friend1Entity {
    @Id
    private String friendId;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private Record1Entity record;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User1Entity user;

}


