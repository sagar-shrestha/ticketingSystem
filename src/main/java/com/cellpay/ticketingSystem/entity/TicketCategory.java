package com.cellpay.ticketingSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany(mappedBy = "ticketCategory", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TicketTopic> ticketTopic;
    @ManyToMany(mappedBy = "ticketCategories", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Ticket> tickets;

    @Override
    public String toString() {
        return "TicketCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", ticketTopic=" + ticketTopic +
                '}';
    }
}
