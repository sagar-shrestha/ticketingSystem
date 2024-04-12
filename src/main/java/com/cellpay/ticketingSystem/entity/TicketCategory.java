package com.cellpay.ticketingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String category;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_topic", foreignKey = @ForeignKey(name = "fk_ticket_topic_id"))
    private TicketTopic ticketTopic;
}
