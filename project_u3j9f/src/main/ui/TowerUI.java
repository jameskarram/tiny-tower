package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import static javax.swing.BoxLayout.Y_AXIS;

//Represents the swing application for this app
public class TowerUI extends JFrame implements ActionListener {

    private JLabel label;
    private JTextField floorNumField;
    private JTextField floorNameField;
    private JTextField floorTypeField;
    private Tower tower;
    private JPanel panel;

    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: initializes the tower, write, reader and starts the gui
    public TowerUI() {
        super("Tower App");
        tower = new Tower();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        startGUI();

    }

    //EFFECTS: creates the entire gui with multiple panels
    private void startGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                printEvents(EventLog.getInstance());
            }
        };

        setPreferredSize(new Dimension(600, 250));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new BorderLayout());

        //Handle Splash Screen
        splashScreen();
        // Tower Info panel
        addTowerInfoPanel();
        // Remove Floor Panel
        addRemoveFloorPanel();
        // Add Floor Panel
        addAddFloorPanel();
        // Persistence Panel
        addPersistencePanel();

        pack();
        setLocationRelativeTo(null);
        addWindowListener(listener);
        setVisible(true);
        setResizable(false);
    }

    //EFFECTS: creates splash screen
    private void splashScreen() {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.show(3000);
        splashScreen.hide();
    }

    //EFFECTS: creates and adds the save and load buttons
    private void addPersistencePanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, Y_AXIS));

        JButton saveTowerButton = new JButton("Save Tower");
        saveTowerButton.setActionCommand("Save Tower");
        saveTowerButton.addActionListener(this);

        JButton loadTowerButton = new JButton("Load Tower");
        loadTowerButton.setActionCommand("Load Tower");
        loadTowerButton.addActionListener(this);

        panel.add(saveTowerButton);
        panel.add(loadTowerButton);

        this.add(panel, BorderLayout.LINE_END);
    }

    //EFFECTS: creates and adds the addTowerPanel with text boxes for name and type and a button
    private void addAddFloorPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, Y_AXIS));

        floorNameField = new JTextField("Name your new floor",10);
        floorTypeField = new JTextField("Choose a floor type. (Residential, Commercial, Recreational)",10);

        JButton addFloorButton = new JButton("Add Floor");
        addFloorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addFloorButton.setActionCommand("Add Floor");
        addFloorButton.addActionListener(this);

        panel.add(floorNameField);
        panel.add(floorTypeField);
        panel.add(addFloorButton);

        this.add(panel, BorderLayout.CENTER);
    }


    //EFFECTS: creates and adds the removeFloor with text box for floor number and a button
    private void addRemoveFloorPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, Y_AXIS));

        floorNumField = new JTextField("Enter floor number to be removed.",5);

        JButton removeFloorButton = new JButton("Remove Floor");
        removeFloorButton.setActionCommand("Remove Floor");
        removeFloorButton.addActionListener(this);

        panel.add(floorNumField);
        panel.add(removeFloorButton);
        this.add(panel, BorderLayout.LINE_START);
    }

    //EFFECTS: creates and adds the TowerInfoPanel with label of current floors and list and dist buttons
    private void addTowerInfoPanel() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        label = new JLabel("Your tower currently has " + tower.getTotalFloors() + " floors!");

        JButton viewTowerButton = new JButton("List Floors");
        viewTowerButton.setActionCommand("List Floors");
        viewTowerButton.addActionListener(this);

        JButton typeDistButton = new JButton("Floor Type Distribution");
        typeDistButton.setActionCommand("Type Dist");
        typeDistButton.addActionListener(this);

        panel.add(label);
        panel.add(viewTowerButton);
        panel.add(typeDistButton);
        this.add(panel, BorderLayout.NORTH);
    }



    //EFFECTS: This is the method that is called when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("List Floors")) {
            viewTower();
        } else if (e.getActionCommand().equals("Type Dist")) {
            createTypeDist();
        } else if (e.getActionCommand().equals("Remove Floor")) {
            processUIRemoval();
        } else if (e.getActionCommand().equals("Add Floor")) {
            processUIAddition();
        } else if (e.getActionCommand().equals("Save Tower")) {
            saveTower();
        } else if (e.getActionCommand().equals("Load Tower")) {
            loadTower();
        }
    }

    private void printEvents(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }


    //EFFECTS: create a graph of the type distribution in the tower
    private void createTypeDist() {
        new TypeDistributions(tower);
    }

    // MODIFIES: this
    // EFFECTS: loads tower from file
    private void loadTower() {
        try {
            tower = jsonReader.read();
            label.setText("Your tower currently has " + tower.getTotalFloors() + " floors!");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the tower to file
    private void saveTower() {
        if (Objects.equals(tower.getName(), "")) {
            String name = JOptionPane.showInputDialog(this,
                    "Name your tower:", null);
            tower.setName(name);
        }
        try {
            jsonWriter.open();
            jsonWriter.write(tower);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: creates a window of the current floors in the tower
    private void viewTower() {
        new TowerViewer(tower);
    }

    //MODIFIES: tower
    //EFFECTS: processes the removal of a floor from the tower
    private void processUIRemoval() {
        if (!tower.getFloors().isEmpty()) {

            int floorNum = Integer.parseInt(floorNumField.getText());

            if (floorNum <= tower.getTotalFloors() && floorNum > 0) {
                tower.removeFloor(floorNum);
                fixNumbering(floorNum - 1);
            }
        }

        label.setText("Your tower currently has " + tower.getTotalFloors() + " floors!");
    }

    //MODIFIES: tower
    //EFFECTS: changes the floorNums of all the floors higher than the floor that was removed to floorNum - 1
    private void fixNumbering(int floorNum) {
        for (Floor floor : tower.getFloors()) {
            if (floor.getFloorNum() > floorNum) {
                floor.setFloorNum(floor.getFloorNum() - 1);
            }
        }

    }

    //EFFECTS: creates a floor of the specified type, sets its floorNum, adds it to the tower and updates the label
    private void processUIAddition() {
        if (Objects.equals(floorTypeField.getText(), "Residential")) {
            ResidentialFloor floor = new ResidentialFloor(floorNameField.getText());
            floor.setFloorNum(tower.getTotalFloors() + 1);
            tower.addFloor(floor);

        } else if (Objects.equals(floorTypeField.getText(), "Commercial")) {
            CommercialFloor floor = new CommercialFloor(floorNameField.getText());
            floor.setFloorNum(tower.getTotalFloors() + 1);
            tower.addFloor(floor);

        } else if (Objects.equals(floorTypeField.getText(), "Recreational")) {
            RecreationalFloor floor = new RecreationalFloor(floorNameField.getText());
            floor.setFloorNum(tower.getTotalFloors() + 1);
            tower.addFloor(floor);

        }

        label.setText("Your tower currently has " + tower.getTotalFloors() + " floors!");

    }


    //EFFECTS: starts the application
    public static void main(String[] args) {
        new TowerUI();
    }
}
