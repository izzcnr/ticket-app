package com.omerkoc.tickets.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "start", nullable = true)
	private LocalDateTime start;
	
	@Column(name = "event_end_time", nullable = true)
	private LocalDateTime end;

	@Column(name = "venue", nullable = false)
	private String venue;

	@Column(name = "sales_start", nullable = true)
	private LocalDateTime salesStart;

	@Column(name = "sales_end", nullable = true)
	private LocalDateTime salesEnd;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private EventStatusEnum status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizer_id")
	private User organizer; 

	@ManyToMany(mappedBy = "attendingEvent") 
	private List<User> attendees = new ArrayList<>();

	@ManyToMany(mappedBy = "staffingEvents")
	private List<User> staff = new ArrayList<User>();

	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private List<TicketType> ticketTypes = new ArrayList<TicketType>();

	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Event))
			return false;
		Event other = (Event) o;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}