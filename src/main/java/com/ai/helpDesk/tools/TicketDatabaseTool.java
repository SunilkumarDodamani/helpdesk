package com.ai.helpDesk.tools;

import com.ai.helpDesk.Service.TicketService;
import com.ai.helpDesk.entity.Ticket;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketDatabaseTool {
    private final TicketService ticketService;

    public TicketDatabaseTool(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Tool(description = "This Tool helps to create new Ticket database")
    public Ticket createTicket(@ToolParam(description = "Ticket fields required to create a new Ticket") Ticket ticket){
       try{
           System.out.println("going to create new ticket"+ticket);
           return this.ticketService.createTicket(ticket);
       } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
       }
    }

    @Tool(description = "this tool helps to get ticket by use email")
    public List<Ticket> getTicket(@ToolParam(description = "email of the user whose Ticket needs to find") String email){

        return this.ticketService.getTicketByEmail(email);
    }

    @Tool(description = "this tool helps to update ticket in database")
    public Ticket updateTicket(@ToolParam (description = "Ticket  fields required to update single ticket") Ticket ticket) {
        try{
            System.out.println("going to update  a ticket"+ticket);
            return this.ticketService.updateTicket(ticket);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}
