package strategies;

import models.*;

public interface SpotAssignmentStrategy {
    public ParkingSpot getSpot(Long parkingLotId, Gate gate, VehicleType vehicleType);
}
