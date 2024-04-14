package com.cellpay.ticketingSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ticket_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String category;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_topic", foreignKey = @ForeignKey(name = "fk_ticket_topic_id"),
    referencedColumnName = "id")
    private TicketTopic ticketTopic;
}
