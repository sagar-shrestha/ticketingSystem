package com.cellpay.ticketingSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JoinColumn(name = "ticket_category_id", foreignKey = @ForeignKey(name = "fk_ticket_category_id"))
    @JsonBackReference
    private TicketCategory ticketCategory;

}
