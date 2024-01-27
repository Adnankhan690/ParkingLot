package services;

import exceptions.GateNotFoundException;
import models.Gate;
import models.Ticket;
import models.Vehicle;
import models.VehicleType;
import repositories.GateRepository;
import repositories.VehicleRepository;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

public class TicketService {

    private final GateRepository gateRepository;
    private final VehicleRepository vehicleRepository;
    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Ticket issueTicket(String vehicleNumber, VehicleType vehicleType, Long gateId) throws GateNotFoundException{
        // Query the DataBase to get the objects using ID.

        //Save Ticket object and vehicle number in the DB. -> Repository
        Ticket ticket = new Ticket();

        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);

//                if(gateOptional.isEmpty()) {
//            throw new GateNotFoundException();
//        }
        Gate gate = gateOptional.orElseThrow(GateNotFoundException::new);

        Vehicle savedVehicle;

        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicleById(vehicleNumber);

        if(optionalVehicle.isEmpty()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            savedVehicle = vehicle;
        } else {
            savedVehicle = optionalVehicle.get();
        }

        ticket.setVehicle(savedVehicle);
        ticket.setGeneratedAt(gate);
        ticket.setEntryTime(new Date());
        ticket.setGeneratedBy(gate.getCurrentOperator());
        ticket.setNumber(String.valueOf(Instant.now().getEpochSecond()));
//      ticket.setParkingSpot();


        // 1. Create Ticket Object
        // 2. Select a spot for the vehicle
        // 3. Return the object;
        return null;
    }
}
