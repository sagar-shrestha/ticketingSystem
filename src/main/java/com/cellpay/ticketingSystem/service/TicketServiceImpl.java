package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketRequestPojo;
import com.cellpay.ticketingSystem.common.util.GenericFileUtil;
import com.cellpay.ticketingSystem.entity.TicketEntity;
import com.cellpay.ticketingSystem.entity.TicketPhotos;
import com.cellpay.ticketingSystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final GenericFileUtil genericFileUtil;
    private final TicketRepository ticketRepository;

    @Transactional
    @Override
    public void saveTicket(TicketRequestPojo ticketRequestPojo) throws Exception {
        List<TicketPhotos> ticketPhotosList = new ArrayList<>();
        for (MultipartFile photo : ticketRequestPojo.getPhotos()) {
            String imagePath = genericFileUtil.saveFile(ticketRequestPojo.getPhotos().get(ticketRequestPojo.getPhotos().indexOf(photo)));
            TicketPhotos ticketPhotos = TicketPhotos
                    .builder()
                    .photo(imagePath)
                    .ticket(TicketEntity.builder().build())
                    .build();
            ticketPhotosList.add(ticketPhotos);
        }

        TicketEntity ticketEntity = TicketEntity
                .builder()
                .ticketTopic(ticketRequestPojo.getTicketTopic())
                .photo(ticketPhotosList)
                .build();
        ticketRepository.save(ticketEntity);
    }

}
