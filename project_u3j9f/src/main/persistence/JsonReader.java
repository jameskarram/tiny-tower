package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads tower from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads tower from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Tower read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTower(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses tower from JSON object and returns it
    private Tower parseTower(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Tower tower = new Tower();
        tower.setName(name);
        addFloors(tower, jsonObject);
        return tower;
    }

    // MODIFIES: tower
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addFloors(Tower tower, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("floors");
        for (Object json : jsonArray) {
            JSONObject nextFloor = (JSONObject) json;
            addFloor(tower, nextFloor);
        }
    }

    // MODIFIES: tower
    // EFFECTS: parses floors from JSON object and adds it to tower
    private void addFloor(Tower tower, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("floorType");
        int occupants = jsonObject.getInt("occupancy");
        int floorNum = jsonObject.getInt("floorNum");
        tower.addFloor(createFloor(name, type, occupants, floorNum));

    }

    // EFFECTS: creates a floor with correct type and number of occupants
    private Floor createFloor(String name, String type, int occupants, int floorNum) {
        if (type.equals(FloorType.COMMERCIAL.toString())) {
            CommercialFloor floor = new CommercialFloor(name);
            addOccupants(occupants, floor);
            floor.setFloorNum(floorNum);
            return floor;
        } else if (type.equals(FloorType.RECREATION.toString())) {
            RecreationalFloor floor = new RecreationalFloor(name);
            addOccupants(occupants, floor);
            floor.setFloorNum(floorNum);
            return floor;
        } else {
            ResidentialFloor floor = new ResidentialFloor(name);
            addOccupants(occupants, floor);
            floor.setFloorNum(floorNum);
            return floor;
        }
    }

    // EFFECTS: adds the correct number of occupants to the floor
    private void addOccupants(int occupants, Floor floor) {
        while (occupants > 0) {
            floor.addOccupant();
            occupants -= 1;
        }
    }
}
