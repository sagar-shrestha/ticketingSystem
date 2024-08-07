package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketRequest;
import com.cellpay.ticketingSystem.common.pojo.response.TicketResponse;
import com.cellpay.ticketingSystem.common.util.GenericFileUtil;
import com.cellpay.ticketingSystem.entity.Ticket;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.entity.TicketImage;
import com.cellpay.ticketingSystem.helper.TicketHelper;
import com.cellpay.ticketingSystem.repository.TicketImageRepository;
import com.cellpay.ticketingSystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final GenericFileUtil genericFileUtil;
    private final TicketRepository ticketRepository;
    private final TicketImageRepository ticketImageRepository;
    private final TicketCategoryService ticketCategoryService;
    private final TicketHelper ticketHelper;

    @Override
    @Transactional
    public boolean saveTicket(TicketRequest ticketRequestPojo) throws Exception {
        Ticket ticket = ticketRepository.save(Ticket
                .builder()
                .ticketCategory(List.of(ticketCategoryService.getCategoryById(ticketRequestPojo.getTicketCategory())))
                .description(ticketRequestPojo.getDescription())
                .build());
        for (MultipartFile image : ticketRequestPojo.getImages()) {
            String imagePath = genericFileUtil.saveFile(ticketRequestPojo.getImages()
                    .get(ticketRequestPojo.getImages().indexOf(image)));
            TicketImage ticketImage = TicketImage
                    .builder()
                    .image(imagePath)
                    .ticket(ticket)
                    .build();
            ticketImageRepository.save(ticketImage);
        }
        return false;
    }

    @Override
    @Transactional
    public void updateTicket(TicketRequest ticketRequest, Long id) throws Exception {
        TicketImage existingTicketImage = null;
        try {
            TicketResponse existingTicket = this.getTicketById(id);
            Ticket updatedTicket = Ticket.builder()
                    .id(id)
                    .description(ticketRequest.getDescription())
                    .ticketCategory(List.of(ticketCategoryService.getCategoryById(ticketRequest.getTicketCategory())))
                    .build();
            if (!(ticketRequest.getImages() == null)) {
                for (int i = 0; i < ticketRequest.getImages().size(); i++) {
                    Integer imageId = existingTicket.getImageId().get(i);
                    existingTicketImage = ticketImageRepository.findById(imageId)
                            .orElseThrow(() -> new RuntimeException("Ticket Image Not Found"));
                    String imagePath = genericFileUtil.updateFile(ticketRequest.getImages().get(i), existingTicketImage.getImage());
                    TicketImage updatedTicketImage = TicketImage.builder()
                            .id(imageId)
                            .image(imagePath)
                            .ticket(ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("d")))
                            .build();
                    ticketImageRepository.save(updatedTicketImage);
                }
            }
            ticketRepository.save(updatedTicket);
        } catch (Exception e) {
            genericFileUtil.reSaveFile(existingTicketImage.getImage());
        }
    }

    @Override
    public TicketResponse getTicketById(Long id) throws MalformedURLException {
        return ticketHelper.getTicketById(id);
    }

    @Override
    public List<TicketResponse> getAllTickets() throws SpelEvaluationException {
        return ticketHelper.getAllTickets();
    }
}