package com.cellpay.ticketingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ticket_photo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String photo;
    @ManyToOne
    @JoinColumn
    private Ticket ticket;
}