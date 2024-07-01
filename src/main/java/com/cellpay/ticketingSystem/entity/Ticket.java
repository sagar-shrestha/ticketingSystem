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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_category", foreignKey = @ForeignKey(name = "fk_ticket_category_id"),
            referencedColumnName = "id")
    private List<TicketCategory> ticketCategory;
    private String description;
//    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<TicketImage> images;

}
