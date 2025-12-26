package com.example.SplitLend.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name="Users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"Username"}),
        @UniqueConstraint(columnNames = {"mobileNumber"})
    }
)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    private String email;

    private String password;

    private String Username;

    private String mobileNumber;

    @ElementCollection
    @CollectionTable(
        name = "user_connections",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "connected_user_id")
    private Set<Long> Connected_Users = new HashSet<>();

}
