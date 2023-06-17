package org.lab7.client.gui;

import org.lab7.client.InteractiveMode;
import org.lab7.client.models.FuelType;
import org.lab7.client.models.VehicleType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CoordinatePlane extends JPanel {

    private float pointX;
    private float pointY;
    private JPanel panel;
    private static ArrayList<ArrayList<String>> vehicles = new ArrayList<>();

    public CoordinatePlane(ArrayList<ArrayList<String>> vehicles) {
        pointX = 0;
        pointY = 0;
        this.vehicles = vehicles;
        addMouseListenerToPoints();
    }

    public void setVehicles(ArrayList<ArrayList<String>> vehicles) {
        this.vehicles = vehicles;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    private void addMouseListenerToPoints() {
        addMouseListener(new MouseAdapter() {
            String id;

            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                boolean isMatch = false;

                for (ArrayList<String> vehicle : vehicles) {
                    float pointX = Float.parseFloat(vehicle.get(7));
                    float pointY = Float.parseFloat(vehicle.get(8));
                    int pointScreenX = Math.round(getWidth() / 2 + pointX);
                    int pointScreenY = Math.round(getHeight() / 2 - pointY);

                    if (mouseX >= pointScreenX - 6 && mouseX <= pointScreenX + 6 &&
                            mouseY >= pointScreenY - 6 && mouseY <= pointScreenY + 6) {
                        showPopupMessage(vehicle.toString());
                        System.out.println(vehicle.toString());
                        id = vehicle.toString().split(" ")[0].substring(1, vehicle.toString().split(" ")[0].length() - 1);
                        System.out.println(id);
                        isMatch = true;
                        break;
                    }
                }
                if (isMatch) {
                    int choice = JOptionPane.showOptionDialog(panel, "Редактировать?", "Вопрос", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                    if (choice == JOptionPane.YES_OPTION) {
                        String[] arrayOfInput = new String[2];
                        boolean flag = true;
                        String name = JOptionPane.showInputDialog(panel, "Введите name:");
                        String x = JOptionPane.showInputDialog(panel, "Введите X:");
                        String y = JOptionPane.showInputDialog(panel, "Введите Y:");
                        String enginePower = JOptionPane.showInputDialog(panel, "Введите enginePower:");
                        String capacity = JOptionPane.showInputDialog(panel, "Введите Capacity:");
                        String type = JOptionPane.showInputDialog(panel, "Введите Type:");
                        String fuelType = JOptionPane.showInputDialog(panel, "Введите FuelType:");
                        try {
                            VehicleType.valueOf(type);
                            FuelType.valueOf(fuelType);
                            Float.parseFloat(x);
                            Float.parseFloat(y);
                            Float.parseFloat(enginePower);
                            Float.parseFloat(capacity);
                            arrayOfInput[1] = id + " " + name + " " + x
                                    + " " + y + " " + enginePower
                                    + " " + capacity + " " + type
                                    + " " + fuelType;

                        } catch (Exception ex) {
                            flag = false;
                            System.out.println(ex.getMessage());
                            JOptionPane.showMessageDialog(panel, "Неправильно введена комнада и/или значения!");
                        }
                        if (flag) {
                            arrayOfInput[0] = "update";
                            InteractiveMode.runCommand(arrayOfInput);
                        }
                    }


                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

        for (ArrayList<String> vehicle : vehicles) {
            g.setColor(Color.getHSBColor(vehicle.get(6).hashCode(), vehicle.get(6).hashCode(), vehicle.get(6).hashCode()));
            pointX = Float.parseFloat(vehicle.get(7));
            pointY = Float.parseFloat(vehicle.get(8));
            int pointScreenX = Math.round(getWidth() / 2 + pointX);
            int pointScreenY = Math.round(getHeight() / 2 - pointY);
            g.fillOval(pointScreenX - 3, pointScreenY - 3, 15, 15);
        }

    }

    private void showPopupMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
