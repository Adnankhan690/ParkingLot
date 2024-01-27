package repositories;

import lombok.Getter;
import lombok.Setter;
import models.ParkingLot;
import java.util.HashMap;
import java.util.Optional;

@Getter
@Setter
public class ParkingLotRepository {
    HashMap<Long, ParkingLot> db;

    public Optional<ParkingLot> getParkingLotById(Long id) {

            if(db.containsKey(id)) {
                return Optional.of(db.get(id));
            }
            return Optional.empty();
    }
}
