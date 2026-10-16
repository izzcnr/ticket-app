package com.oruccinar.tickets.domain.dtos;

import java.util.UUID;

import com.oruccinar.tickets.domain.entities.TicketStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTicketResponseDto {
  private UUID id;
  private TicketStatusEnum status;
  private ListTicketTicketTypeResponseDto ticketType;
}
