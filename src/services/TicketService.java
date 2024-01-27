package services;

import exceptions.GateNotFoundException;
import models.*;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import strategies.RandomSpotAssignmentStrategy;
import strategies.SpotAssignmentStrategy;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

public class TicketService {

    private final GateRepository gateRepository;
    private final VehicleRepository vehicleRepository;
    private final SpotAssignmentStrategy randomSpotStrategy;
    private final TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, TicketRepository ticketRepository, SpotAssignmentStrategy randomSpotAssignmentStrategy) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
        this.randomSpotStrategy = randomSpotAssignmentStrategy;
    }

    public Ticket issueTicket(String vehicleNumber, VehicleType vehicleType, Long gateId) throws GateNotFoundException{
        // Query the DataBase to get the objects using ID.

        //Save Ticket object and vehicle number in the DB. -> Repository

        // 1. Create Ticket Object
        // 2. Select a spot for the vehicle
        // 3. Return the object;
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

        ParkingSpot parkingSpot = randomSpotStrategy.getSpot(1L, gate, vehicleType);

        //Fill the spot with the vehicle and 'status' as OCCUPIED
        parkingSpot.setVehicle(savedVehicle);
        parkingSpot.setParkingSpotStatus(ParkingSpotStatus.OCCUPIED);

        //Parking spot repository and save this
        ticket.setParkingSpot(parkingSpot);

        ticketRepository.save(ticket);
        return ticket;
    }
}
