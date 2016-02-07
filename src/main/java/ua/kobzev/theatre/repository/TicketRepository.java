package ua.kobzev.theatre.repository;

import java.time.LocalDateTime;
import java.util.List;

import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;

public interface TicketRepository {

	void save(Ticket ticket);

	List<Ticket> findAllByEvent(Event event, LocalDateTime dateTime);

	boolean isPurchased(AssignedEvent assignedEvent, int seat);

	List<Ticket> findAllByUser(User user);

}
