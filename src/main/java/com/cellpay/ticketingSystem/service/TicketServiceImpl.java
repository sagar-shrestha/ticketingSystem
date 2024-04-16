package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketRequest;
import com.cellpay.ticketingSystem.common.pojo.response.TicketResponse;
import com.cellpay.ticketingSystem.common.util.GenericFileUtil;
import com.cellpay.ticketingSystem.entity.Ticket;
import com.cellpay.ticketingSystem.entity.TicketImage;
import com.cellpay.ticketingSystem.helper.TicketHelper;
import com.cellpay.ticketingSystem.repository.TicketImageRepository;
import com.cellpay.ticketingSystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final GenericFileUtil genericFileUtil;
    private final TicketRepository ticketRepository;
    private final TicketImageRepository ticketImageRepository;
    private final TicketCategoryService ticketCategoryService;
    private final TicketHelper ticketHelper;

    @Transactional
    @Override
    public void saveTicket(TicketRequest ticketRequestPojo) throws Exception {
        Ticket ticket = ticketRepository.save(Ticket
                .builder()
                .ticketCategory(ticketCategoryService.getCategoryById(ticketRequestPojo.getTicketCategory()))
                .description(ticketRequestPojo.getDescription())
                .build());
        for (MultipartFile image : ticketRequestPojo.getImages()) {
            String imagePath = genericFileUtil.saveFile(ticketRequestPojo.getImages().get(ticketRequestPojo.getImages().indexOf(image)));
            TicketImage ticketImage = TicketImage
                    .builder()
                    .image(imagePath)
                    .ticket(ticket)
                    .build();
            ticketImageRepository.save(ticketImage);
        }
    }

    @Override
    public TicketResponse getTicketById(Long id) throws MalformedURLException {
        return ticketHelper.getTicketById(id);
    }

}
