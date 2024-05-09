package ui;

import model.FloorType;
import model.Tower;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.*;

public class TypeDistributions extends JFrame {

    private Tower tower;
    private JLabel resLabel;
    private JLabel comLabel;
    private JLabel recLabel;


    public TypeDistributions(Tower t) {
        super("Viewing: " + t.getName());
        tower = t;
        createTypeDistPanel();
    }

    private void createTypeDistPanel() {
        setPreferredSize(new Dimension(200, 150));
        setLayout(new BorderLayout());

        JPanel panel = createPanel();
        add(BorderLayout.CENTER, new JScrollPane(panel));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(tower.getTotalFloors(), 1, 0, 0));

        resLabel = new JLabel("Residential Floors: " + tower.getNumFloorType(FloorType.RESIDENTIAL));
        comLabel = new JLabel("Commercial Floors: " + tower.getNumFloorType(FloorType.COMMERCIAL));
        recLabel = new JLabel("Recreational Floors: " + tower.getNumFloorType(FloorType.RECREATION));

        panel.add(resLabel);
        panel.add(comLabel);
        panel.add(recLabel);

        return panel;

    }

}
