package com.cellpay.ticketingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @OneToMany
    @JoinColumn(name = "ticket_topic", foreignKey = @ForeignKey(name = "fk_ticket_topic_id"))
    private List<TicketTopic> ticketTopic;

    @Override
    public String toString() {
        return "TicketCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", ticketTopic=" + ticketTopic +
                '}';
    }
}
