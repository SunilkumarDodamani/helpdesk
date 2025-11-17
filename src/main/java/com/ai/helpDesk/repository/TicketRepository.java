package com.ai.helpDesk.repository;

import com.ai.helpDesk.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Optional<Ticket> findTicketByEmail(String email);
}
