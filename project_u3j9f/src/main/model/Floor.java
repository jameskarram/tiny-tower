package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a floor having a name, floorNum, occupancy, and maximum occupancy
public abstract class Floor implements Writable {

    private final String name;
    private int floorNum;
    private final int maxOccupancy;
    private int occupancy;

    // EFFECTS: constructs a floor with a name, occupancy, and max occupancy
    public Floor(String name) {
        this.name = name;
        this.maxOccupancy = 5;
        this.occupancy = 0;
    }

    //EFFECTS: returns the floor name
    public String getFloorName() {
        return this.name;
    }

    //EFFECTS: returns the floor number
    public int getFloorNum() {
        return this.floorNum;
    }

    //EFFECTS: sets the floor number
    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    //EFFECTS: returns the floor number
    public int getOccupancy() {
        return this.occupancy;
    }

    //EFFECTS: sets the floor number
    public void addOccupant() {
        if (!this.atCapacity()) {
            this.occupancy += 1;
        }
    }

    //EFFECTS: checks if floor occupancy is at capacity
    public boolean atCapacity() {
        return this.maxOccupancy == this.occupancy;
    }

    //EFFECTS: returns the type of the floor
    public abstract FloorType getFloorType();

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("floorNum", floorNum);
        json.put("floorType", getFloorType().toString());
        json.put("occupancy", occupancy);
        return json;
    }

}
