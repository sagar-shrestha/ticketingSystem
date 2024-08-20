package com.cellpay.ticketingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ticket_topic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String topic;
    @ManyToOne
    @JoinColumn(name = "ticket_category", foreignKey = @ForeignKey(name = "fk_ticket_category_id"))
    private TicketCategory ticketCategory;

}
