package ua.kobzev.theatre.repository.impl.inmemory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.TicketRepository;

public class TicketRepositoryImpl implements TicketRepository {

	private List<Ticket> tickets;

	{
		tickets = new ArrayList<>();
	}

	@Override
	public void save(Ticket ticket) {
		tickets.add(ticket);
	}

	@Override
	public List<Ticket> findAllByEvent(Event event, LocalDateTime dateTime) {
		return tickets.stream().filter(
				p -> event.equals(p.getAssignedEvent().getEvent()) && dateTime.equals(p.getAssignedEvent().getDate()))
				.collect(Collectors.toList());
	}

	@Override
	public boolean isPurchased(AssignedEvent assignedEvent, int seat) {
		return tickets.stream().anyMatch(t -> assignedEvent.equals(t.getAssignedEvent()) && seat == t.getSeat());
	}

	@Override
	public List<Ticket> findAllByUser(User user) {
		return tickets.stream().filter(p -> user.equals(p.getUser())).collect(Collectors.toList());
	}

}
