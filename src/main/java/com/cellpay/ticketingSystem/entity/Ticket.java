package com.cellpay.ticketingSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ticket_category", foreignKey = @ForeignKey(name = "fk_ticket_category"),
//            referencedColumnName = "id")
    @JoinTable(name = "ticket_category_ticket", joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_category_id"))
    @JsonManagedReference
    private List<TicketCategory> ticketCategories;
    private String description;
    @ManyToOne
    @JoinColumn(name = "paynet_user_details", foreignKey = @ForeignKey(name = "fk_paynet_user_details"))
    private PaynetUserDetails paynetUserDetails;

}
