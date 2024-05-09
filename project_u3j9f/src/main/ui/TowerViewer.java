package ui;

import model.Tower;

import javax.swing.*;
import java.awt.*;

//Represents the tower viewer window of this app
public class TowerViewer extends JFrame {

    private Tower tower;
    private JLabel label;

    //EFFECTS: constructs the view tower window
    public TowerViewer(Tower t) {
        super("Viewing: " + t.getName());
        tower = t;
        createTowerWindow();
    }

    //EFFECTS: creates the tower window and adds a panel of all floor nums listed in reverse order
    private void createTowerWindow() {
        setPreferredSize(new Dimension(200, 400));
        setLayout(new BorderLayout());

        JPanel panel = createPanel();
        add(BorderLayout.CENTER, new JScrollPane(panel));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //EFFECTS: creates a panel of all floor nums listed in reverse order
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(tower.getTotalFloors(), 1, 0, 0));

        int length = tower.getTotalFloors() - 1;
        while (length >= 0) {
            label = new JLabel(tower.getFloors().get(length).getFloorNum() + ". "
                    + tower.getFloors().get(length).getFloorName());
            panel.add(label);
            length -= 1;
        }

        return panel;
    }



}
