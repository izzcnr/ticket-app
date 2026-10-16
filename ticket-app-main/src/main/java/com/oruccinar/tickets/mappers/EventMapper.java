package com.oruccinar.tickets.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.oruccinar.tickets.domain.CreateEventRequest;
import com.oruccinar.tickets.domain.CreateTicketTypeRequest;
import com.oruccinar.tickets.domain.UpdateEventRequest;
import com.oruccinar.tickets.domain.UpdateTicketTypeRequest;
import com.oruccinar.tickets.domain.dtos.CreateEventRequestDto;
import com.oruccinar.tickets.domain.dtos.CreateEventResponseDto;
import com.oruccinar.tickets.domain.dtos.CreateTicketTypeRequestDto;
import com.oruccinar.tickets.domain.dtos.GetEventDetailsResponseDto;
import com.oruccinar.tickets.domain.dtos.GetEventDetailsTicketTypesResponseDto;
import com.oruccinar.tickets.domain.dtos.GetPublishedEventDetailsResponseDto;
import com.oruccinar.tickets.domain.dtos.GetPublishedEventDetailsTicketTypesResponseDto;
import com.oruccinar.tickets.domain.dtos.ListEventResponseDto;
import com.oruccinar.tickets.domain.dtos.ListEventTicketTypesResponseDto;
import com.oruccinar.tickets.domain.dtos.ListPublishedEventResponseDto;
import com.oruccinar.tickets.domain.dtos.UpdateEventRequestDto;
import com.oruccinar.tickets.domain.dtos.UpdateEventResponseDto;
import com.oruccinar.tickets.domain.dtos.UpdateTicketTypeRequestDto;
import com.oruccinar.tickets.domain.dtos.UpdateTicketTypeResponseDto;
import com.oruccinar.tickets.domain.entities.Event;
import com.oruccinar.tickets.domain.entities.TicketType;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
	CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

	CreateEventRequest fromDto(CreateEventRequestDto dto);

	CreateEventResponseDto toDto(Event event);

	ListEventTicketTypesResponseDto toDto(TicketType ticketType);

	ListEventResponseDto toListEventResponseDto(Event event);//

	GetEventDetailsTicketTypesResponseDto togEventTicketTypesResponseDto(TicketType ticketType);

	GetEventDetailsResponseDto toGetEventDetailsResponseDto(Event event);

	UpdateTicketTypeRequest fromDto(UpdateTicketTypeRequestDto dto);

	UpdateEventRequest fromDto(UpdateEventRequestDto dto);

	UpdateTicketTypeResponseDto toUpdateTicketTypeResponseDto(TicketType ticketType);

	UpdateEventResponseDto toUpdateEventResponseDto(Event event);
	  ListPublishedEventResponseDto toListPublishedEventResponseDto(Event event);

	  GetPublishedEventDetailsTicketTypesResponseDto toGetPublishedEventDetailsTicketTypesResponseDto(
	      TicketType ticketType);

	  GetPublishedEventDetailsResponseDto toGetPublishedEventDetailsResponseDto(Event event);

}
