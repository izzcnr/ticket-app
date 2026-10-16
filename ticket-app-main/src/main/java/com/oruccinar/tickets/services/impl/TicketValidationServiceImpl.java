package com.oruccinar.tickets.services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.oruccinar.tickets.domain.entities.QrCode;
import com.oruccinar.tickets.domain.entities.QrCodeStatusEnum;
import com.oruccinar.tickets.domain.entities.Ticket;
import com.oruccinar.tickets.domain.entities.TicketValidation;
import com.oruccinar.tickets.domain.entities.TicketValidationMethod;
import com.oruccinar.tickets.domain.entities.TicketValidationStatusEnum;
import com.oruccinar.tickets.exceptions.QrCodeNotFoundException;
import com.oruccinar.tickets.exceptions.TicketNotFoundException;
import com.oruccinar.tickets.repositories.QrCodeRepository;
import com.oruccinar.tickets.repositories.TicketRepository;
import com.oruccinar.tickets.repositories.TicketValidationRepository;
import com.oruccinar.tickets.services.TicketValidationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketValidationServiceImpl implements TicketValidationService {

  private final QrCodeRepository qrCodeRepository;
  private final TicketValidationRepository ticketValidationRepository;
  private final TicketRepository ticketRepository;

  @Override
  public TicketValidation validateTicketByQrCode(UUID qrCodeId) {
    QrCode qrCode = qrCodeRepository.findByIdAndStatus(qrCodeId, QrCodeStatusEnum.ACTIVE)
        .orElseThrow(() -> new QrCodeNotFoundException(
            String.format(
                "QR Code with ID %s was not found", qrCodeId
            )
        ));

    Ticket ticket = qrCode.getTicket();

    return validateTicket(ticket, TicketValidationMethod.QR_SCAN);
  }

  private TicketValidation validateTicket(Ticket ticket,
      TicketValidationMethod ticketValidationMethod) {
    TicketValidation ticketValidation = new TicketValidation();
    ticketValidation.setTicket(ticket);
    ticketValidation.setValidationMethod(ticketValidationMethod);

    TicketValidationStatusEnum ticketValidationStatus = ticket.getTicketValidations().stream()
        .filter(v -> TicketValidationStatusEnum.VALID.equals(v.getStatus()))
        .findFirst()
        .map(v -> TicketValidationStatusEnum.INVALID)
        .orElse(TicketValidationStatusEnum.VALID);

    ticketValidation.setStatus(ticketValidationStatus);

    return ticketValidationRepository.save(ticketValidation);
  }

  @Override
  public TicketValidation validateTicketManually(UUID ticketId) {
    Ticket ticket = ticketRepository.findById(ticketId)
        .orElseThrow(TicketNotFoundException::new);
    return validateTicket(ticket, TicketValidationMethod.MANUAL);
  }
}
