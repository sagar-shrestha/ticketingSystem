package com.cellpay.ticketingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_category", foreignKey = @ForeignKey(name = "fk_ticket_category_id"),
            referencedColumnName = "id")
    private TicketCategory ticketCategory;
    private String description;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
//    private List<TicketImage> photo;
}
