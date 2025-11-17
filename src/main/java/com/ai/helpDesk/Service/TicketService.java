package com.ai.helpDesk.Service;

import com.ai.helpDesk.entity.Ticket;
import org.springframework.stereotype.Service;


public interface TicketService {

    public Ticket createTicket(Ticket ticket);

    public Ticket getTicket(long id );

    public Ticket updateTicket(Ticket ticket);

    public void deleteTicket(long id);

    public Ticket getTicketByEmail(String email);

}
