package com.oruccinar.tickets.services;

import java.util.UUID;

import com.oruccinar.tickets.domain.entities.TicketValidation;

public interface TicketValidationService {
	  TicketValidation validateTicketByQrCode(UUID qrCodeId);
	  TicketValidation validateTicketManually(UUID ticketId);
	}
