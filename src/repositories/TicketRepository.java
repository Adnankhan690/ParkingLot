package repositories;

import models.Gate;
import models.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TicketRepository {

    Map<String, Ticket> db = new HashMap<>();

    public Optional<Ticket> findGateById(String ticketId) {
        if(db.containsKey(ticketId)) {
            return Optional.of(db.get(ticketId));
        }
        return Optional.empty();
    }

    public void save(Ticket ticket) {
        db.put(ticket.getNumber(), ticket);
    }
}
