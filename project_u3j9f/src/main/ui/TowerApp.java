package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

// Represents the console application
public class TowerApp {

    private static final String JSON_STORE = "./data/workroom.json";
    private Tower userTower;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Tower App
    public TowerApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        startTower();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void startTower() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            System.out.println("Your tower is: " + userTower.getTotalFloors() + " floors tall");

            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }

        }


    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            processAddition();
        } else if (command.equals("r")) {
            processRemoval();
        } else if (command.equals("v")) {
            viewTower();
        } else if (command.equals("s")) {
            saveTower();
        } else if (command.equals("l")) {
            loadTower();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: initializes tower and scanner
    public void init() {
        input = new Scanner(System.in);
        userTower = new Tower();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: displays menu
    public void displayMenu() {
        System.out.println("Select an Action.");
        System.out.println("\ta -> Add a floor");
        System.out.println("\tr -> Remove a floor");
        System.out.println("\tv -> View tower statistics");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit");
    }

    //REQUIRES:
    //MODIFIES: userTower
    //EFFECTS: handles the addition of a floor to the tower
    private void processAddition() {
        String command;
        boolean requestType = true;

        while (requestType) {

            displayTypes();
            command = input.next();

            if (command.equals("1")) {
                userTower.addFloor(createResFloor());
                requestType = false;
            } else if (command.equals("2")) {
                userTower.addFloor(createRecFloor());
                requestType = false;
            } else if (command.equals("3")) {
                userTower.addFloor(createComFloor());
                requestType = false;
            } else {
                System.out.println("Selection not valid, try again.");
            }
        }

    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: display the types of residences
    private void displayTypes() {
        System.out.println("Select what type you'd like your new floor to be:");
        System.out.println("\t1 -> Residential");
        System.out.println("\t2 -> Recreational");
        System.out.println("\t3 -> Commercial");
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: creates a residential floor, sets its floor number and returns it
    private ResidentialFloor createResFloor() {
        String name;

        System.out.println("Name your new Residential floor: \n");
        name = input.next();

        ResidentialFloor floor = new ResidentialFloor(name);
        floor.setFloorNum(userTower.getTotalFloors() + 1);

        return floor;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: creates a recreational floor, sets its floor number and returns it
    private RecreationalFloor createRecFloor() {
        String name;

        System.out.println("Name your new Recreational floor: \n");
        name = input.next();

        RecreationalFloor floor = new RecreationalFloor(name);
        floor.setFloorNum(userTower.getTotalFloors() + 1);

        return floor;

    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: creates a commercial floor, sets its floor number and returns it
    private CommercialFloor createComFloor() {
        String name;

        System.out.println("Name your new Commercial floor: \n");
        name = input.next();

        CommercialFloor floor = new CommercialFloor(name);
        floor.setFloorNum(userTower.getTotalFloors() + 1);

        return floor;
    }


    //REQUIRES:
    //MODIFIES: userTower
    //EFFECTS: processes the removal of a floor from the tower
    private void processRemoval() {
        if (userTower.getFloors().isEmpty()) {
            System.out.println("No existing floors to remove.");
        } else {

            String number;
            boolean keepGoing = true;

            while (keepGoing) {

                System.out.println("Which floor would you like to remove?");
                number = input.next();
                int floorNum = Integer.parseInt(number);

                if (floorNum <= userTower.getTotalFloors() && floorNum > 0) {
                    userTower.removeFloor(floorNum);
                    fixNumbering(floorNum - 1);
                    keepGoing = false;
                } else {
                    System.out.println("That floor does not exist. Try Again");
                }

            }
        }

    }

    //REQUIRES:
    //MODIFIES: userTower
    //EFFECTS: changes the floorNums of all the floors higher than the floor that was removed to floorNum - 1
    private void fixNumbering(int floorNum) {
        for (Floor floor : userTower.getFloors()) {
            if (floor.getFloorNum() > floorNum) {
                floor.setFloorNum(floor.getFloorNum() - 1);
            }
        }

    }


    //EFFECTS: handles the viewing tower option
    private void viewTower() {
        boolean keepViewing = true;
        String command = null;

        while (keepViewing) {

            presentMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepViewing = false;
            } else {
                viewItem(command);
            }

        }
    }

    //EFFECTS: displays the menu for the tower statistics that are able to be viewed
    private void presentMenu() {
        System.out.println("Select what type you'd like to know:");
        System.out.println("\tn -> Number of floors");
        System.out.println("\tl -> List of existing floors");
        System.out.println("\tt -> Type distribution of floors");
        System.out.println("\tq -> Quit");

    }

    //EFFECTS: handles the input for the viewTower menu
    private void viewItem(String command) {
        if (command.equals("n")) {
            System.out.println(userTower.getTotalFloors());
        } else if (command.equals("l")) {
            buildTower();
        } else if (command.equals("t")) {
            viewTypes();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: prints a list of the floors in the order they would be in if it were an actual tower
    private void buildTower() {
        int length = userTower.getTotalFloors() - 1;
        while (length >= 0) {
            System.out.println(userTower.getFloors().get(length).getFloorNum() + ". "
                    + userTower.getFloors().get(length).getFloorName());
            length -= 1;
        }

    }

    //EFFECTS: prints the type distribution of a tower
    private void viewTypes() {
        System.out.println("Residential Floors:" + userTower.getNumFloorType(FloorType.RESIDENTIAL));
        System.out.println("Recreational Floors:" + userTower.getNumFloorType(FloorType.RECREATION));
        System.out.println("Commercial Floors:" + userTower.getNumFloorType(FloorType.COMMERCIAL));
    }

    // EFFECTS: saves the tower to file
    private void saveTower() {
        if (Objects.equals(userTower.getName(), "")) {
            System.out.println("Name your tower:");
            input = new Scanner(System.in);
            String name = input.nextLine();
            userTower.setName(name);
        }
        try {
            jsonWriter.open();
            jsonWriter.write(userTower);
            jsonWriter.close();
            System.out.println("Saved " + userTower.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tower from file
    private void loadTower() {
        try {
            userTower = jsonReader.read();
            System.out.println("Loaded " + userTower.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}


















