import java.util.LinkedList;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

public class ParkingLot {

    LinkedList<Car> compactSpaces = new LinkedList<>();
    LinkedList<Car> normalSpaces = new LinkedList<>();
    private final int normalCapacity = 10;
    private final int compactCapacity = 5;

    public boolean parkNormal(Car car) {
        if (normalSpaces.size() < normalCapacity) {
            normalSpaces.add(car);
            return true;
        } else {
            return false;
        }
    }

    public boolean parkCompact(Car car) {
        if (compactSpaces.size() < compactCapacity) {
            compactSpaces.add(car);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws NotImplementedException {
        ParkingLot lot = new ParkingLot();
        for (int i = 0; i < 20; i++) {
            Car car;
            if (Math.random() < 0.5) {
                car = new CompactCar();
            } else {
                car = new FullSizeCar();
            }
            car.park(lot);
        }
    }
}

class Car {

    public void park(ParkingLot lot) throws NotImplementedException {
        throw new NotImplementedException("");
    }
}

class CompactCar extends Car {
    // This class should first try to park in a compact space. 
    // If none are available, it should try to park in a normal-sized space.
    public void park(ParkingLot lot) {
        if (lot.parkCompact(this)) {
            System.out.println("Compact car parked in compact space");
        } else if (lot.parkNormal(this)) {
            System.out.println("Compact car parked in normal space");
        } else {
            System.out.println("Compact car could not find space");
        }
    }
}

class FullSizeCar extends Car {
    // This class should only attempt to park in a normal-sized space.
    public void park(ParkingLot lot) {
        if (lot.parkNormal(this)) {
            System.out.println("Normal car parked in normal space");
        } else {
            System.out.println("Normal car could not find space");
        }
    }
}