package model;

// Represents a commercial floor, subclass of floor

public class RecreationalFloor extends Floor {

    // EFFECTS: constructs a recreational floor with a name and passes to super
    public RecreationalFloor(String name) {
        super(name);

    }

    @Override
    public FloorType getFloorType() {
        return FloorType.RECREATION;
    }

}
