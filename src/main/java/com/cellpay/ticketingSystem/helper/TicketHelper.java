package com.cellpay.ticketingSystem.helper;

import com.cellpay.ticketingSystem.common.pojo.response.TicketResponse;
import com.cellpay.ticketingSystem.common.util.GenericFileUtil;
import com.cellpay.ticketingSystem.entity.Ticket;
import com.cellpay.ticketingSystem.entity.TicketPhoto;
import com.cellpay.ticketingSystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketHelper {

    private final GenericFileUtil genericFileUtil;
    private final TicketRepository ticketRepository;


    public TicketResponse getTicketById(Long id) throws MalformedURLException {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket Not Found."));
        List<Resource> resourceList = new ArrayList<>();
        List<Integer> photoIds = new ArrayList<>();
//        for (TicketPhoto ticketPhoto : ticket.getPhoto()) {
//            Resource photo = genericFileUtil.getFile(ticketPhoto.getPhoto());
//            resourceList.add(photo);
//            photoIds.add(ticketPhoto.getId());
//        }

        return TicketResponse.builder()
                .id(ticket.getId())
                .ticketCategory(ticket.getTicketCategory())
                .description(ticket.getDescription())
                .photoId(photoIds)
                .photo(resourceList)
                .build();
    }
}
