package com.example.SplitLend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SplitLend.Entity.Settlement;

public interface SettlementRepository extends JpaRepository<Settlement, Long>{

    
}
