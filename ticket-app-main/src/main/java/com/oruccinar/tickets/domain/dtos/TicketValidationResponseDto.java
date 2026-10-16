package com.oruccinar.tickets.domain.dtos;

import java.util.UUID;

import com.oruccinar.tickets.domain.entities.TicketValidationStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketValidationResponseDto {
  private UUID ticketId;
  private TicketValidationStatusEnum status;
}
