package persistence;

import model.Floor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFloor(String name, String type, int occupants, int floorNum, Floor floor) {
        assertEquals(name, floor.getFloorName());
        assertEquals(type, floor.getFloorType().toString());
        assertEquals(floorNum, floor.getFloorNum());
        assertEquals(occupants, floor.getOccupancy());
    }
}
