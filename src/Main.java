import controllers.TicketController;
import dtos.IssueTicketRequest;
import dtos.IssueTicketResponse;
import models.VehicleType;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import services.TicketService;
import strategies.RandomSpotAssignmentStrategy;
import strategies.SpotAssignmentStrategy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Parking Lot");

        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        TicketRepository ticketRepository = new TicketRepository();


        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy(parkingLotRepository);

        TicketService ticketService = new TicketService(gateRepository, vehicleRepository, ticketRepository, spotAssignmentStrategy);

        TicketController ticketController = new TicketController(ticketService);

        IssueTicketResponse response = ticketController.issueTicket(IssueTicketRequest.builder()
                .gateId(1L)
                .vehicleNumber("UP32HM2198")
                .vehicleType(VehicleType.BIKE).build());
    }
}
