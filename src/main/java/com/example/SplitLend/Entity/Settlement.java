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
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table( name="settlement" )
public class Settlement {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    //Who paid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user", nullable = false)
    private User fromUser;

    //who received
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user", nullable = false)
    private User toUser;

    @NotNull
    private Double amountPaid;

    private LocalDateTime createdAt;

}
