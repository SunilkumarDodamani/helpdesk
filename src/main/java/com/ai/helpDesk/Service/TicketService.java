package com.ai.helpDesk.Service;

import com.ai.helpDesk.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TicketService {

    public Ticket createTicket(Ticket ticket);

    public Ticket getTicket(long id );

    public Ticket updateTicket(Ticket ticket);

    public void deleteTicket(long id);

    public List<Ticket> getTicketByEmail(String email);

}
