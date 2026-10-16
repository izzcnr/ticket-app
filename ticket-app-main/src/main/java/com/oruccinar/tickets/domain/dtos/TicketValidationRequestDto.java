package com.oruccinar.tickets.domain.dtos;

import java.util.UUID;

import com.oruccinar.tickets.domain.entities.TicketValidationMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketValidationRequestDto {
  private UUID id;
  private TicketValidationMethod method;
}
