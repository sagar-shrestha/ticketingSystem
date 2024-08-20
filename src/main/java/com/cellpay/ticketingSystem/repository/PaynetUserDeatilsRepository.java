package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.PaynetUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaynetUserDeatilsRepository extends JpaRepository<PaynetUserDetails, Integer> {

    Optional<PaynetUserDetails> getPaynetUserDetailsByUsername(String username);
}
