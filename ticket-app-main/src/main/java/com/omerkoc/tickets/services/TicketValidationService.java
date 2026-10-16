package com.omerkoc.tickets.services;

import java.util.UUID;

import com.omerkoc.tickets.domain.entities.TicketValidation;

public interface TicketValidationService {
	  TicketValidation validateTicketByQrCode(UUID qrCodeId);
	  TicketValidation validateTicketManually(UUID ticketId);
	}
