package persistence;

import model.Floor;
import org.junit.jupiter.api.Test;

import model.Tower;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Tower tower = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTower() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTower.json");
        try {
            Tower tower = reader.read();
            assertEquals("My tower", tower.getName());
            assertEquals(0, tower.getTotalFloors());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTower() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTower.json");
        try {
            Tower tower = reader.read();
            assertEquals("My tower", tower.getName());
            List<Floor> floors = tower.getFloors();
            assertEquals(3, floors.size());
            checkFloor("apartments", "RESIDENTIAL", 3, 1, floors.get(0));
            checkFloor("sushi", "COMMERCIAL", 1, 2, floors.get(1));
            checkFloor("yoga", "RECREATION", 1, 3, floors.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
