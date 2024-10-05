package com.DevJavaMinh.service;

import com.DevJavaMinh.dto.TicketDto;
import com.DevJavaMinh.model.Ticket;

import java.util.List;

public interface TicketService {
    List<TicketDto> getAllTickets();
    TicketDto getTicketById(Long id);
    TicketDto addTicket(TicketDto ticketDto);
    TicketDto updateTicket(Long id, TicketDto ticketDto);
    void deleteTicket(Long id);

}
