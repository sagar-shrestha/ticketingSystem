package com.cellpay.ticketingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paynet_user_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaynetUserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String memberId;
    private String memberType;
    private String memberName;
    private String username;

}
