package com.cellpay.ticketingSystem.entity;

import com.cellpay.ticketingSystem.common.constant.TicketTopic;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private TicketTopic ticketTopic;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
    private List<TicketPhotos> photo;
}
