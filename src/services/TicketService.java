package services;

import models.Gate;
import models.Ticket;
import models.VehicleType;
import repositories.GateRepository;

import java.util.Optional;

public class TicketService {

    private final GateRepository gateRepository;
    public TicketService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }
    public Ticket issueTicket(String vehicleNumber, VehicleType vehicleType, Long gateId) {
        // Query the DataBase to get the objects using ID.

        //Save Ticket object and vehicle number in the DB. -> Repository

        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);
        gateOptional.get().getCurrentOperator();

        // 1. Create Ticket Object
        // 2. Select a spot for the vehicle
        // 3. Return the object;
        return null;
    }
}
