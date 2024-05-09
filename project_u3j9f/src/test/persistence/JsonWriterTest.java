package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Tower tower = new Tower();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTower() {
        try {
            Tower tower = new Tower();
            tower.setName("My tower");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTower.json");
            writer.open();
            writer.write(tower);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTower.json");
            tower = reader.read();
            assertEquals("My tower", tower.getName());
            assertEquals(0, tower.getTotalFloors());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTower() {
        try {
            Tower tower = new Tower();
            tower.setName("My tower");
            tower.addFloor(new ResidentialFloor("apartments"));
            tower.getFloors().get(0).addOccupant();
            tower.getFloors().get(0).addOccupant();
            tower.getFloors().get(0).addOccupant();
            tower.getFloors().get(0).setFloorNum(1);
            tower.addFloor(new CommercialFloor("sushi"));
            tower.getFloors().get(1).addOccupant();
            tower.getFloors().get(1).setFloorNum(2);
            tower.addFloor(new RecreationalFloor("yoga"));
            tower.getFloors().get(2).addOccupant();
            tower.getFloors().get(2).setFloorNum(3);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTower.json");
            writer.open();
            writer.write(tower);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTower.json");
            tower = reader.read();
            assertEquals("My tower", tower.getName());
            List<Floor> floors = tower.getFloors();
            assertEquals(3, floors.size());
            checkFloor("apartments", "RESIDENTIAL", 3, 1, floors.get(0));
            checkFloor("sushi", "COMMERCIAL", 1, 2, floors.get(1));
            checkFloor("yoga", "RECREATION", 1, 3, floors.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
