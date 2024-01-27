package dtos;

import lombok.Getter;
import lombok.Setter;
import models.VehicleType;

@Setter
@Getter
public class IssueTicketRequest {
    private String vehicleNumber;
    private Long gateId;
    private VehicleType vehicleType;
}
