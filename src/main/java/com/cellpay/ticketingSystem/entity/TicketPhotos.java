package com.cellpay.ticketingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ticket_photos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    private String photo;
}
