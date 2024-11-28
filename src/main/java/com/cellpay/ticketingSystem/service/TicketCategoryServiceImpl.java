package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.Exception.DataNotFoundException;
import com.cellpay.ticketingSystem.common.pojo.request.TicketCategoryRequest;
import com.cellpay.ticketingSystem.common.pojo.request.TicketRequest;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.repository.TicketCategoryRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;

    @Override
    public boolean saveTicketCategory(TicketRequest ticketCategoryRequest) {
        return false;
    }

    @Override
    @Transactional
    public boolean saveTicketCategory(TicketCategoryRequest ticketCategoryRequest) {
        try {
          //  List<TicketTopic> ticketTopicList = ticketCategoryRequest.getTicketTopic().stream()
            //        .map(ticketTopicService::getTopicById).collect(Collectors.toList());
            TicketCategory ticketCategory = TicketCategory
                    .builder()
                    .category(ticketCategoryRequest.getCategory())
              //      .ticketTopic(ticketTopicList)
                    .build();
            ticketCategoryRepository.save(ticketCategory);
            return false;
        } catch (Exception e) {
            throw new DataNotFoundException("unable to save ticket category");
        }
    }

    @Override
    @Transactional
    public TicketCategory updateTicketCategory(TicketCategoryRequest ticketCategoryRequest, int id) {
        try {
            TicketCategory existingTicketCategory = getCategoryById(id).getFirst();
            //List<TicketTopic> ticketTopicList = ticketCategoryRequest.getTicketTopic().stream()
             //       .map(ticketTopicService::getTopicById).collect(Collectors.toList());
            TicketCategory updatedTicketCategory = TicketCategory
                    .builder()
                    .id(existingTicketCategory.getId())
                    .category(ticketCategoryRequest.getCategory())
                 //   .ticketTopic(ticketTopicList)
                    .build();
            return ticketCategoryRepository.save(updatedTicketCategory);
        } catch (Exception e) {
            throw new DataNotFoundException("unable to update ticket category");
        }
    }


    @Override
    public List<TicketCategory> getCategoryById(int categoryId) {
        try {
            return Collections.singletonList(ticketCategoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found")));
        } catch (RuntimeException e) {
            throw new DataNotFoundException("Category not found" + categoryId);
        }
    }

    @Override
    public Page<TicketCategory> getAllCategoryWithPagination(int pageNo, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("category").ascending());
            return ticketCategoryRepository.findAll(pageable);
        } catch (RuntimeException e) {
            throw new DataNotFoundException("unable to get all category");
        }
    }

    @Override
    public List<TicketCategory> getAllCategoryWithOutPagination() {
        try {
            return ticketCategoryRepository.findAll();
        } catch (RuntimeException e) {
            throw new DataNotFoundException("unable to get all category");
        }
    }

    public void removeSessionMessage() {
        HttpSession httpSession = (HttpSession) ((Objects.requireNonNull(RequestContextHolder.getRequestAttributes())))
                .getSessionMutex();
        httpSession.removeAttribute("sessionMessage");
    }


}