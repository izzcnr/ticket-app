package com.omerkoc.tickets.services;

import java.util.UUID;

import com.omerkoc.tickets.domain.entities.QrCode;
import com.omerkoc.tickets.domain.entities.Ticket;

public interface QrCodeService {

  QrCode generateQrCode(Ticket ticket);

  byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId);
}
