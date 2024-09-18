package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.PaynetUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaynetUserDeatilsRepository extends JpaRepository<PaynetUserDetails, Integer> {

    PaynetUserDetails getPaynetUserDetailsByUsername(String username);

    Optional<PaynetUserDetails> getPaynetUserDetailsByusername(String username);
}
