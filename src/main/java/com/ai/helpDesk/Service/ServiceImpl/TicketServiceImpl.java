package com.ai.helpDesk.Service.ServiceImpl;

import com.ai.helpDesk.Service.TicketService;
import com.ai.helpDesk.entity.Ticket;
import com.ai.helpDesk.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {

        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket) {
        ticket.setId(null);
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
        existingTicket.setSummary(ticket.getSummary());
        existingTicket.setStatus(ticket.getStatus());
        existingTicket.setPriority(ticket.getPriority());
        existingTicket.setEmail(ticket.getEmail());
        existingTicket.setUpdatedOn(ticket.getUpdatedOn());
        return ticketRepository.save(existingTicket);
    }

    @Override
    public void deleteTicket(long id) {

    }

    @Override
    public List<Ticket> getTicketByEmail(String email) {
        return ticketRepository.findTicketsByEmail(email);
    }
}
