package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FloorTest {

    private ResidentialFloor resFloor;
    private RecreationalFloor recFloor;
    private CommercialFloor comFloor;
//    private Tenant tenant1;
//    private Tenant tenant2;
//    private Tenant tenant3;
//    private Tenant tenant4;
//    private Tenant tenant5;
//    private Tenant tenant6;

    @BeforeEach
    void runBefore() {
        resFloor = new ResidentialFloor("Spring Apartments");
        recFloor = new RecreationalFloor("Yoga");
        comFloor = new CommercialFloor("Sushi");
    }

    @Test
    void testGetName() {
        assertEquals("Spring Apartments", resFloor.getFloorName());
        assertEquals("Yoga", recFloor.getFloorName());
        assertEquals("Sushi", comFloor.getFloorName());
    }

    @Test
    void testFloorNum() {
        resFloor.setFloorNum(5);
        assertEquals(5, resFloor.getFloorNum());
    }

    @Test
    void testOccupantThings() {
        resFloor.addOccupant();
        resFloor.addOccupant();
        resFloor.addOccupant();
        resFloor.addOccupant();
        resFloor.addOccupant();
        assertEquals(5, resFloor.getOccupancy());
        resFloor.addOccupant();
        assertEquals(5, resFloor.getOccupancy());
    }




}
