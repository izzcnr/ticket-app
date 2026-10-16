package com.oruccinar.tickets.controllers;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oruccinar.tickets.domain.dtos.GetTicketResponseDto;
import com.oruccinar.tickets.domain.dtos.ListTicketResponseDto;
import com.oruccinar.tickets.mappers.TicketMapper;
import com.oruccinar.tickets.services.QrCodeService;
import com.oruccinar.tickets.services.TicketService;
import com.oruccinar.tickets.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

  private final TicketService ticketService;
  private final TicketMapper ticketMapper;
  private final QrCodeService qrCodeService;

  @GetMapping
  public Page<ListTicketResponseDto> listTickets(
      @AuthenticationPrincipal Jwt jwt,
      Pageable pageable
  ) {
    return ticketService.listTicketsForUser(
    		JwtUtil.parseUserId(jwt),
        pageable
    ).map(ticketMapper::toListTicketResponseDto);
  }

  @GetMapping(path = "/{ticketId}")
  public ResponseEntity<GetTicketResponseDto> getTicket(
      @AuthenticationPrincipal Jwt jwt,
      @PathVariable UUID ticketId
  ) {
    return ticketService
        .getTicketForUser(JwtUtil.parseUserId(jwt), ticketId)
        .map(ticketMapper::toGetTicketResponseDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping(path = "/{ticketId}/qr-codes")
  public ResponseEntity<byte[]> getTicketQrCode(
      @AuthenticationPrincipal Jwt jwt,
      @PathVariable UUID ticketId
  ) {
    byte[] qrCodeImage = qrCodeService.getQrCodeImageForUserAndTicket(
    		JwtUtil.parseUserId(jwt),
        ticketId
    );

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_PNG);
    headers.setContentLength(qrCodeImage.length);

    return ResponseEntity.ok()
        .headers(headers)
        .body(qrCodeImage);
  }

}
