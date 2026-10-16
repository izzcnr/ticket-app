package com.oruccinar.tickets.services;

import java.util.UUID;

import com.oruccinar.tickets.domain.entities.Ticket;

public interface TicketTypeService {
	  Ticket purchaseTicket(UUID userId, UUID ticketTypeId);
	}
