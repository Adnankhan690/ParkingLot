package dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import models.VehicleType;

@Setter
@Getter
@Builder
public class IssueTicketRequest {
    private String vehicleNumber;
    private Long gateId;
    private VehicleType vehicleType;
}
