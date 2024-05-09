package model;

import java.util.ArrayList;
import java.util.List;

// Represents a commercial floor, subclass of floor

public class ResidentialFloor extends Floor {

    // EFFECTS: constructs a residential floor with a name and passes to super
    public ResidentialFloor(String name) {
        super(name);

    }

    @Override
    public FloorType getFloorType() {
        return FloorType.RESIDENTIAL;
    }


//    //REQUIRES: occupants.size() < maxOccupants
//    //MODIFIES: this
//    //EFFECTS: adds an occupant to the floor
//    public void addOccupant(Tenant tenant) {
//        if (!this.atCapacity()) {
//            this.occupants.add(tenant);
//        }
//    }
//
//    //EFFECTS: returns number of occupants
//    public int getOccupants() {
//        return this.occupants.size();
//    }
//
//    //EFFECTS: checks if floor occupancy is at capacity
//    public boolean atCapacity() {
//        return this.maxOccupancy == this.occupants.size();
//    }
//
//    //REQUIRES: occupants.size() > 0
//    //MODIFIES: this
//    //EFFECTS: removes an occupant from the floor
//    public void removeOccupant(int index) {
//        if (!this.occupants.isEmpty()) {
//            this.occupants.remove(index);
//        }
//    }


}
