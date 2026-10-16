package com.omerkoc.tickets.services;

import java.util.UUID;

import com.omerkoc.tickets.domain.entities.Ticket;

public interface TicketTypeService {
	  Ticket purchaseTicket(UUID userId, UUID ticketTypeId);
	}
