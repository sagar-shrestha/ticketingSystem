package com.cellpay.ticketingSystem.entity;

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
    @ManyToOne
    @JoinColumn(name = "ticket_topic", foreignKey = @ForeignKey(name = "fk_ticket_topic_id"))
    private TicketTopic ticketTopic;

    @Override
    public String toString() {
        return "TicketCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", ticketTopic=" + ticketTopic +
                '}';
    }
}
