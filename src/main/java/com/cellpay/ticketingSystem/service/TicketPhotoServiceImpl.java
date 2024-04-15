package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.entity.TicketPhoto;
import com.cellpay.ticketingSystem.repository.TicketPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketPhotoServiceImpl implements TicketPhotoService {

    private final TicketPhotoRepository ticketPhotoRepository;

    @Override
    public void saveTicketPhoto(TicketPhoto ticketPhoto) {
         ticketPhotoRepository.save(ticketPhoto);
    }
}
