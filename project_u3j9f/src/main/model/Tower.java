package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a tower having a collection of floors
public class Tower implements Writable {

    private String name;
    private final ArrayList<Floor> floors;
    private int numFloors;

    // EFFECTS: constructs tower with a name and empty list of floors
    public Tower() {
        this.name = "";
        this.floors = new ArrayList<Floor>();
    }

    //EFFECTS: sets the name of the tower
    public void setName(String name) {
        this.name = name;
    }

    //EFFECTS: gets the name of the tower
    public String getName() {
        return this.name;
    }


    //MODIFIES: this
    //EFFECTS: adds a floor to the tower
    public void addFloor(Floor floor) {
        this.floors.add(floor);
        EventLog.getInstance().logEvent(new Event("Added " + floor.getFloorType().toString().toLowerCase()
                + " floor: " + floor.getFloorName()));
    }

    //MODIFIES: this
    //EFFECTS: removes a floor to the tower
    public void removeFloor(int floorNum) {
        this.floors.remove(floorNum - 1);
        EventLog.getInstance().logEvent(new Event("Removed floor " + floorNum));
    }

    //EFFECTS: gets the list of floors
    public ArrayList<Floor> getFloors() {
        return this.floors;
    }

    //EFFECTS: gets the total number of floors in the tower
    public int getTotalFloors() {
        return this.floors.size();
    }

    //EFFECTS: gets number of a specific floor type in the tower
    public int getNumFloorType(FloorType floorType) {
        int count = 0;
        for (Floor floor : this.floors) {
            if (floor.getFloorType() == floorType) {
                count++;
            }
        }
        return count;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("floors", floorsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray floorsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Floor f : floors) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }


}
