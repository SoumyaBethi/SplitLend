package com.example.SplitLend.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table( name="connected_users" ,

   uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "connected_user_id"})
    }
)
public class Connected_Users {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    // User who initiated connection
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Connected friend
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "connected_user_id", nullable = false)
    private User connectedUser;

    private ConnectionStatus status;

    private LocalDateTime createdAt;

}
