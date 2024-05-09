package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {

    private Tower myTower;
    private Floor resFloor;
    private Floor resFloor2;
    private Floor recFloor;
    private Floor comFloor;
    private List<Floor> testList;

    @BeforeEach
    void runBefore() {
        myTower = new Tower();
        resFloor = new ResidentialFloor("Spring Apartments");
        resFloor2 = new ResidentialFloor("Lovely Apartments");
        recFloor = new RecreationalFloor("Yoga");
        comFloor = new CommercialFloor("Sushi");
        testList = new ArrayList<>();

    }

    @Test
    void testWithOneFloor() {
        assertEquals(0, myTower.getTotalFloors());
        assertTrue(myTower.getFloors().isEmpty());
        myTower.addFloor(resFloor);
        assertEquals(1, myTower.getTotalFloors());
        assertFalse(myTower.getFloors().isEmpty());
        testList.add(resFloor);
        assertEquals(testList, myTower.getFloors());
        assertEquals(1, myTower.getNumFloorType(FloorType.RESIDENTIAL));
        assertEquals(0, myTower.getNumFloorType(FloorType.RECREATION));
        assertEquals(0, myTower.getNumFloorType(FloorType.COMMERCIAL));

    }

    @Test
    void testWithMultipleFloorsSameType() {
        myTower.addFloor(resFloor);
        myTower.addFloor(resFloor2);
        assertEquals(2, myTower.getTotalFloors());
        assertFalse(myTower.getFloors().isEmpty());
        testList.add(resFloor);
        testList.add(resFloor2);
        assertEquals(testList, myTower.getFloors());
        assertEquals(2, myTower.getNumFloorType(FloorType.RESIDENTIAL));
        assertEquals(0, myTower.getNumFloorType(FloorType.RECREATION));
        assertEquals(0, myTower.getNumFloorType(FloorType.COMMERCIAL));


    }

    @Test
    void testWithMultipleFloorsDifferentTypes() {
        myTower.addFloor(resFloor);
        myTower.addFloor(recFloor);
        myTower.addFloor(comFloor);
        assertEquals(3, myTower.getTotalFloors());
        assertFalse(myTower.getFloors().isEmpty());
        testList.add(resFloor);
        testList.add(recFloor);
        testList.add(comFloor);
        assertEquals(testList, myTower.getFloors());
        assertEquals(1, myTower.getNumFloorType(FloorType.RESIDENTIAL));
        assertEquals(1, myTower.getNumFloorType(FloorType.RECREATION));
        assertEquals(1, myTower.getNumFloorType(FloorType.COMMERCIAL));
        myTower.removeFloor(2);
        assertEquals(2, myTower.getTotalFloors());
        assertEquals(1, myTower.getNumFloorType(FloorType.RESIDENTIAL));
        assertEquals(0, myTower.getNumFloorType(FloorType.RECREATION));
        assertEquals(1, myTower.getNumFloorType(FloorType.COMMERCIAL));


    }


}
