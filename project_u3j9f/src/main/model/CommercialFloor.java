package model;

// Represents a commercial floor, subclass of floor
public class CommercialFloor extends Floor {

    // EFFECTS: constructs a commercial floor with a name and passes to super
    public CommercialFloor(String name) {
        super(name);

    }

    @Override
    public FloorType getFloorType() {
        return FloorType.COMMERCIAL;
    }
}
