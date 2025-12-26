package com.example.SplitLend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SplitLend.Entity.User;

@Repository
public interface  UserRepository extends JpaRepository<User, Long>{


}
