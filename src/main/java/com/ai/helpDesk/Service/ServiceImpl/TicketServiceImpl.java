package com.ai.helpDesk.Service.ServiceImpl;

import com.ai.helpDesk.Service.TicketService;
import com.ai.helpDesk.entity.Ticket;
import com.ai.helpDesk.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket) {
       return  this.ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicket(long id) {
        return  this.ticketRepository.findById(id).get();
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        Ticket existingTicket = ticketRepository.findById(ticket.getId())
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticket.getId())); 
        return ticketRepository.save(existingTicket);
    }

    @Override
    public void deleteTicket(long id) {

    }

    @Override
    public Ticket getTicketByEmail(String email) {
        return ticketRepository.findTicketByEmail(email).orElse(null);
    }
}
