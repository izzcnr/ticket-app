package com.omerkoc.tickets.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.omerkoc.tickets.domain.CreateEventRequest;
import com.omerkoc.tickets.domain.CreateTicketTypeRequest;
import com.omerkoc.tickets.domain.UpdateEventRequest;
import com.omerkoc.tickets.domain.UpdateTicketTypeRequest;
import com.omerkoc.tickets.domain.dtos.CreateEventRequestDto;
import com.omerkoc.tickets.domain.dtos.CreateEventResponseDto;
import com.omerkoc.tickets.domain.dtos.CreateTicketTypeRequestDto;
import com.omerkoc.tickets.domain.dtos.GetEventDetailsResponseDto;
import com.omerkoc.tickets.domain.dtos.GetEventDetailsTicketTypesResponseDto;
import com.omerkoc.tickets.domain.dtos.GetPublishedEventDetailsResponseDto;
import com.omerkoc.tickets.domain.dtos.GetPublishedEventDetailsTicketTypesResponseDto;
import com.omerkoc.tickets.domain.dtos.ListEventResponseDto;
import com.omerkoc.tickets.domain.dtos.ListEventTicketTypesResponseDto;
import com.omerkoc.tickets.domain.dtos.ListPublishedEventResponseDto;
import com.omerkoc.tickets.domain.dtos.UpdateEventRequestDto;
import com.omerkoc.tickets.domain.dtos.UpdateEventResponseDto;
import com.omerkoc.tickets.domain.dtos.UpdateTicketTypeRequestDto;
import com.omerkoc.tickets.domain.dtos.UpdateTicketTypeResponseDto;
import com.omerkoc.tickets.domain.entities.Event;
import com.omerkoc.tickets.domain.entities.TicketType;

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
