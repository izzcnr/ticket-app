package com.omerkoc.tickets.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.omerkoc.tickets.domain.entities.EventStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListEventTicketTypesResponseDto {
	private UUID id;
	private String name;
	private Double price;
	private String description;
	private Integer totalAvailable;
}
