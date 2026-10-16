package com.oruccinar.tickets.services;

import java.util.UUID;

import com.oruccinar.tickets.domain.entities.QrCode;
import com.oruccinar.tickets.domain.entities.Ticket;

public interface QrCodeService {

  QrCode generateQrCode(Ticket ticket);

  byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId);
}
