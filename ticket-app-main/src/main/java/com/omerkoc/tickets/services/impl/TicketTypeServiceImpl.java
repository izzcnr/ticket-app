package com.omerkoc.tickets.services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.omerkoc.tickets.domain.entities.Ticket;
import com.omerkoc.tickets.domain.entities.TicketStatusEnum;
import com.omerkoc.tickets.domain.entities.TicketType;
import com.omerkoc.tickets.domain.entities.User;
import com.omerkoc.tickets.exceptions.TicketTypeNotFoundException;
import com.omerkoc.tickets.exceptions.TicketsSoldOutException;
import com.omerkoc.tickets.exceptions.UserNotFoundException;
import com.omerkoc.tickets.repositories.TicketRepository;
import com.omerkoc.tickets.repositories.TicketTypeRepository;
import com.omerkoc.tickets.repositories.UserRepository;
import com.omerkoc.tickets.services.QrCodeService;
import com.omerkoc.tickets.services.TicketTypeService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

  private final UserRepository userRepository;
  private final TicketTypeRepository ticketTypeRepository;
  private final TicketRepository ticketRepository;
  private final QrCodeService qrCodeService;

  @Override
  @Transactional
  public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
        String.format("User with ID %s was not found", userId)
    ));

    TicketType ticketType = ticketTypeRepository.findByIdWithLock(ticketTypeId)
        .orElseThrow(() -> new TicketTypeNotFoundException(
            String.format("Ticket type with ID %s was not found", ticketTypeId)
        ));

    int purchasedTickets = ticketRepository.countByTicketTypeId(ticketType.getId());
    Integer totalAvailable = ticketType.getTotalAvailable();

    if(purchasedTickets + 1 > totalAvailable) {
      throw new TicketsSoldOutException();
    }

    Ticket ticket = new Ticket();
    ticket.setStatus(TicketStatusEnum.PURCHASED);
    ticket.setTicketType(ticketType);
    ticket.setPurchaser(user);

    Ticket savedTicket = ticketRepository.save(ticket);
    qrCodeService.generateQrCode(savedTicket);

    return ticketRepository.save(savedTicket);
  }
}
