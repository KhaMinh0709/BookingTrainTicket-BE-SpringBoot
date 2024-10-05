package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.TicketDto;
import com.DevJavaMinh.model.Ticket;

public class TicketMapping {
    public static TicketDto mapToTicketDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto(
                ticket.getTicketid(),
                ticket.getSchedule().getScheduleID(),
                ticket.getSeat().getSeatID()
        );

        return ticketDto;
    }
    public static Ticket mapToTicket(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setTicketid(ticketDto.getTicketid());
        return ticket;
    }
}
