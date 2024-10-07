package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.TicketDto;
import com.DevJavaMinh.exception.NotFoundException;
import com.DevJavaMinh.mapper.TicketMapping;
import com.DevJavaMinh.model.Schedule;
import com.DevJavaMinh.model.Seat;
import com.DevJavaMinh.model.Ticket;
import com.DevJavaMinh.repository.ScheduleRepository;
import com.DevJavaMinh.repository.SeatRepository;
import com.DevJavaMinh.repository.TicketRepository;
import com.DevJavaMinh.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceIml implements TicketService {

    private final TicketRepository ticketRepository;
    private final ScheduleRepository scheduleRepository;
    private final SeatRepository seatRepository;
    @Override
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketMapping::mapToTicketDto).toList();
    }

    @Override
    public TicketDto getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));
        return TicketMapping.mapToTicketDto(ticket);
    }

    @Override
    public TicketDto addTicket(TicketDto ticketDto) {
        Ticket ticket = TicketMapping.mapToTicket(ticketDto);

        Optional<Schedule> scheduleOpt = scheduleRepository.findById(ticketDto.getScheduleid());
        if (scheduleOpt.isPresent()) {
            ticket.setSchedule(scheduleOpt.get()); // Gán schedule nếu tồn tại
        } else {
            throw new RuntimeException("Schedule not found with id: " + ticketDto.getScheduleid());
        }

        Optional<Seat> seatOpt = seatRepository.findById(ticketDto.getSeatid());
        if (seatOpt.isPresent()) {
            ticket.setSeat(seatOpt.get()); // Gán seat nếu tồn tại
        } else {
            throw new RuntimeException("Seat not found with id: " + ticketDto.getSeatid());
        }

        // Lưu ticket vào cơ sở dữ liệu
        Ticket savedTicket = ticketRepository.save(ticket);

        // Trả về TicketDto sau khi lưu
        return TicketMapping.mapToTicketDto(savedTicket);
    }


    @Override
    public TicketDto updateTicket(Long id, TicketDto ticketDto) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));
        ticket.setTicketid(ticketDto.getTicketid());

        Optional< Schedule> schedule = scheduleRepository.findById(ticketDto.getScheduleid());

        return null;
    }

    @Override
    public void deleteTicket(Long id) {
       Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Ticket not found"));
        ticketRepository.delete(ticket);
    }
}
