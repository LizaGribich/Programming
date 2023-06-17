package org.lab7.client.gui;

import org.lab7.client.*;
import org.lab7.client.commands.Insert;
import org.lab7.client.models.FuelType;
import org.lab7.client.models.VehicleType;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserGUI extends JFrame {
    private static ArrayList<ArrayList<String>> vehicles = new ArrayList<>();
    private final CardLayout cardLayout;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel commandLabel;
    private JLabel filterLabel;
    private JTextArea resultArea;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField commandField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton nextButton;
    private JButton backButton;
    private JButton sendButton;
    private JButton moreInfButton;
    private JLabel currentUserLabel;
    private JLabel currentUserLabel2;
    private JTable table;
    private DefaultTableModel tableModel;
    private String currentUser;
    private JPanel cardPanel;
    private Thread updateThread;
    private Thread repaintThread;
    private volatile boolean isRunning;
    private static boolean isChangedPaint;
    private static boolean isChangedTable;
    private JTextField filterTextField;
    JButton filterButton;
    JButton cleanButton;
    JLabel kitten;
    ImageIcon icon;
    LocalizationResources localizationResources = new LocalizationResources("russian");;

    public UserGUI() throws InterruptedException {

        // страница 1
        setTitle("MegaApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 150);
        setLayout(new FlowLayout());

        usernameLabel = new JLabel(localizationResources.getMessage("имя"));
        passwordLabel = new JLabel(localizationResources.getMessage("пароль"));

        usernameField = new JTextField(15);
        passwordField = new JTextField(15);

        loginButton = new JButton(localizationResources.getMessage("войти"));
        registerButton = new JButton(localizationResources.getMessage("зарегистироваться"));
        nextButton = new JButton(localizationResources.getMessage("далее"));

        currentUserLabel = new JLabel(localizationResources.getMessage("пользователь"));
        currentUserLabel2 = new JLabel(localizationResources.getMessage("пользователь"));

        kitten = new JLabel();

        ImageIcon iconOld = new ImageIcon("C:\\Users\\liza6\\IdeaProjects\\Lab8\\ClientLab6\\src\\main\\resources\\kitten.jpg");
        Image scaledImage = iconOld.getImage().getScaledInstance(600, 350, Image.SCALE_SMOOTH);

        icon = new ImageIcon(scaledImage);

        String[] languages = {"russian", "ukrainian"};
        JComboBox<String> languageComboBox = new JComboBox<>(languages);

        languageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = languageComboBox.getSelectedIndex();
                if (selectedIndex == 0) {
                    localizationResources.setLanguage("russian");
                    updateText();
                } else if (selectedIndex == 1) {
                    localizationResources.setLanguage("ukrainian");
                    updateText();
                }
            }
        });

        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new GridLayout(3, 1));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout());

        JPanel nextPanel = new JPanel();
        nextPanel.setLayout(new FlowLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        buttonsPanel.add(loginButton);
        buttonsPanel.add(registerButton);
        buttonsPanel.add(nextButton);
        buttonsPanel.add(languageComboBox);

        entryPanel.add(usernameLabel);
        entryPanel.add(usernameField);
        entryPanel.add(passwordLabel);
        entryPanel.add(passwordField);

        userPanel.add(currentUserLabel);
        entryPanel.add(currentUserLabel);

        JPanel page1 = new JPanel();
        page1.setLayout(new BorderLayout());

        page1.add(entryPanel, BorderLayout.NORTH);
        page1.add(buttonsPanel, BorderLayout.SOUTH);
        page1.add(kitten, BorderLayout.CENTER);

        // страница 2
        moreInfButton = new JButton(localizationResources.getMessage("инфа"));

        JPanel userPanel2 = new JPanel();
        userPanel2.setLayout(new GridLayout(2, 1));
        userPanel2.add(currentUserLabel2);
        userPanel2.add(moreInfButton);

        CoordinatePlane coordinatePlane = new CoordinatePlane(vehicles);
        coordinatePlane.setPreferredSize(new Dimension(400, 300));
        isRunning = true;
        repaintThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    isChangedPaint = Database.readIsChanged();
                    if (isChangedPaint) {
                        coordinatePlane.setVehicles(vehicles);
                        coordinatePlane.repaint();
                        isChangedPaint = false;
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (!isChangedPaint && !isChangedTable) {
                            Database.setBaseIsChanged();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }

                }
            }
        });

        repaintThread.start();



        JPanel page2 = new JPanel();
        page2.setLayout(new BorderLayout());

        coordinatePlane.setPanel(page2);
        page2.add(userPanel2, BorderLayout.SOUTH);
        //page2.add(moreInfButton, BorderLayout.SOUTH);
        page2.add(coordinatePlane, BorderLayout.CENTER);


        page2.setVisible(true);

        //страница 3
        commandLabel = new JLabel(localizationResources.getMessage("ввод"));
        commandField = new JTextField(15);
        sendButton = new JButton(localizationResources.getMessage("отправить"));
        backButton = new JButton(localizationResources.getMessage("назад"));

        resultArea = new JTextArea(localizationResources.getMessage("ответ"));

        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new GridLayout(4, 1));
        commandPanel.add(commandLabel);
        commandPanel.add(commandField);
        commandPanel.add(sendButton);
        commandPanel.add(backButton);


        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        populateTable();
        JScrollPane scrollPane = new JScrollPane(table);
        setVisible(true);


        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.getTableHeader().columnAtPoint(e.getPoint());

                List<Object[]> data = Arrays.stream(tableModel.getDataVector().toArray())
                        .map(Object::toString)
                        .map(str -> str.substring(1, str.length() - 1).split(", "))
                        .map(arr -> Arrays.stream(arr).map(String::trim).toArray(String[]::new))
                        .collect(Collectors.toList());


                List<Object[]> sortedData = data.stream()
                        .sorted(Comparator.comparing(objArr -> objArr[column].toString()))
                        .collect(Collectors.toList());

                tableModel.setRowCount(0);

                sortedData.forEach(tableModel::addRow);
            }
        });
        filterLabel = new JLabel(localizationResources.getMessage("отфильтровать"));
        filterTextField = new JTextField();
        filterButton = new JButton(localizationResources.getMessage("фильтр"));
        cleanButton = new JButton(localizationResources.getMessage("очистить"));


        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    populateTable();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filterText = filterTextField.getText().trim().toLowerCase();

                List<Object[]> filteredData = Arrays.stream(tableModel.getDataVector().toArray())
                        .map(Object::toString)
                        .map(str -> str.substring(1, str.length() - 1).split(", "))
                        .map(arr -> Arrays.stream(arr).map(String::trim).toArray(String[]::new))
                        .filter(objArr -> Arrays.stream(objArr)
                                .anyMatch(val -> val.toString().toLowerCase().contains(filterText)))
                        .collect(Collectors.toList());


                tableModel.setRowCount(0);

                filteredData.forEach(tableModel::addRow);
            }
        });

        JPanel filterPanel = new JPanel(new BorderLayout());
        JPanel filterButtonPanel = new JPanel(new BorderLayout());
        filterPanel.setLayout(new GridLayout(2, 1));
        filterButtonPanel.setLayout(new GridLayout(1, 2));

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setLayout(new GridLayout(2, 1));

        textPanel.add(filterLabel);
        textPanel.add(filterTextField);

        filterPanel.add(textPanel);
        filterButtonPanel.add(filterButton);
        filterButtonPanel.add(cleanButton);
        filterPanel.add(filterButtonPanel);

        updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    isChangedTable = Database.readIsChanged();
                    if (isChangedTable) {
                        try {
                            populateTable();
                            isChangedTable = false;
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (!isChangedPaint && !isChangedTable) {
                                Database.setBaseIsChanged();
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }

                }
            }
        });
        updateThread.start();


        JPanel page3 = new JPanel();


        page3.setLayout(new BorderLayout());

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {
                    Object[] rowData = new Object[table.getColumnCount()];
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        rowData[i] = table.getValueAt(selectedRow, i);
                    }

                    int choice = JOptionPane.showOptionDialog(page3, localizationResources.getMessage("редактировать"), "Вопрос", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    String id = (String) rowData[0];
                    System.out.println(id);
                    if (choice == JOptionPane.YES_OPTION) {
                        String[] arrayOfInput = new String[2];
                        boolean flag = true;
                        String name = JOptionPane.showInputDialog(page3, localizationResources.getMessage("введите")+"name:");
                        String x = JOptionPane.showInputDialog(page3, localizationResources.getMessage("введите")+"X:");
                        String y = JOptionPane.showInputDialog(page3, localizationResources.getMessage("введите")+"Y:");
                        String enginePower = JOptionPane.showInputDialog(page3, localizationResources.getMessage("введите")+"enginePower:");
                        String capacity = JOptionPane.showInputDialog(page3, localizationResources.getMessage("введите")+"capacity:");
                        String type = JOptionPane.showInputDialog(page3, localizationResources.getMessage("введите")+"Type:");
                        String fuelType = JOptionPane.showInputDialog(page3, localizationResources.getMessage("введите")+"FuelType:");
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
                            JOptionPane.showMessageDialog(page3, localizationResources.getMessage("неправильно"));
                        }
                        if (flag) {
                            arrayOfInput[0] = "update";
                            InteractiveMode.runCommand(arrayOfInput);
                        }
                    }
                }
            }
        });

        JPanel finalPanel = new JPanel(new BorderLayout());
        finalPanel.setLayout(new GridLayout(1, 2));
        finalPanel.add(commandPanel);
        finalPanel.add(filterPanel);

        page3.add(finalPanel, BorderLayout.CENTER);
        page3.add(resultArea, BorderLayout.SOUTH);
        page3.add(scrollPane, BorderLayout.NORTH);


        cardPanel.add(page1, "page1");
        cardPanel.add(page2, "page2");
        cardPanel.add(page3, "page3");

        add(cardPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginUser();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        moreInfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infPage();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registerUser();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    send();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void updateText() {
        usernameLabel.setText(localizationResources.getMessage("имя"));
        passwordLabel.setText(localizationResources.getMessage("пароль"));

        loginButton.setText(localizationResources.getMessage("войти"));
        registerButton.setText(localizationResources.getMessage("зарегистироваться"));
        nextButton.setText(localizationResources.getMessage("далее"));

        currentUserLabel.setText(localizationResources.getMessage("пользователь"));
        currentUserLabel2.setText(localizationResources.getMessage("пользователь"));

        moreInfButton.setText(localizationResources.getMessage("инфа"));
        commandLabel.setText(localizationResources.getMessage("ввод"));
        sendButton.setText(localizationResources.getMessage("отправить"));
        backButton.setText(localizationResources.getMessage("назад"));

        resultArea.setText(localizationResources.getMessage("ответ"));

        filterButton.setText(localizationResources.getMessage("фильтр"));
        cleanButton.setText(localizationResources.getMessage("очистить"));
    }

    private void populateTable() throws InterruptedException {
        Thread.sleep(1000);
        vehicles.clear();
        tableModel.setRowCount(0);

        InteractiveMode.runCommand("show".split(" "));
        String collectionString = RequestProcessor.getCommandResult().getMessage();

        ArrayList<String> collectionItems = new ArrayList<>(Arrays.asList(collectionString.split("\n")));

        ArrayList<String> columnNames = new ArrayList<>(Arrays.asList("ID Name Vehicle_type Engine_power Fuel Capacity User X Y Date".split(" ")));
        if (tableModel.getColumnCount() == 0) {
            for (String columnName : columnNames) {
                tableModel.addColumn(columnName);
            }
        }
        for (String collectionItem : collectionItems) {
            System.out.println(collectionItem);
            String[] parts = collectionItem.split(" : ");
            String itemId = parts[0].trim();
            try {
                String itemData = parts[1].trim();
                String[] itemFields = itemData.split("; ");
                ArrayList<String> itemValues = new ArrayList<>();
                itemValues.add(itemId);
                for (String field : itemFields) {
                    String[] fieldParts = field.split(": ");
                    String fieldValue = fieldParts[1].trim();
                    itemValues.add(fieldValue);
                }
                tableModel.addRow(itemValues.toArray());
                vehicles.add(itemValues);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }


    private void next() {
        if (Entry.getName() != null) {
            cardLayout.show(cardPanel, "page2");
        } else {
            JOptionPane.showMessageDialog(this, localizationResources.getMessage("войдите"));
        }
    }

    private void send() throws InterruptedException {
        boolean flag = true;
        String[] arrayOfInput = commandField.getText().split(" ");
        if (!CommandManager.getCommands().contains(arrayOfInput[0])) {
            flag = false;
            JOptionPane.showMessageDialog(this, localizationResources.getMessage("неправильно"));
        }
        if (arrayOfInput[0].equals(Insert.getName())) {
            String[] array = new String[2];
            array[0] = arrayOfInput[0];
            array[1] = "";
            arrayOfInput = array;
        }
        if (CommandManager.getCommandsExtraData().contains(arrayOfInput[0])) {
            String name = JOptionPane.showInputDialog(localizationResources.getMessage("поле"));
            String value = JOptionPane.showInputDialog(localizationResources.getMessage("значение"));
            try {
                Float.parseFloat(value);
                if (!(name.equals("capacity") || name.equals("enginePower"))) {
                    throw new Exception();
                }
                arrayOfInput[1] += " " + name + " " + value;
            } catch (Exception e) {
                flag = false;
                JOptionPane.showMessageDialog(this, localizationResources.getMessage("неправильно"));
            }
        }
        if (CommandManager.getCommandsExtraModel().contains(arrayOfInput[0])) {
            String name = JOptionPane.showInputDialog(localizationResources.getMessage("введите")+"name:");
            String x = JOptionPane.showInputDialog(localizationResources.getMessage("введите")+"X:");
            String y = JOptionPane.showInputDialog(localizationResources.getMessage("введите")+"Y:");
            String enginePower = JOptionPane.showInputDialog(localizationResources.getMessage("введите")+"enginePower:");
            String capacity = JOptionPane.showInputDialog(localizationResources.getMessage("введите")+"Capacity:");
            String type = JOptionPane.showInputDialog(localizationResources.getMessage("введите")+"Type:");
            String fuelType = JOptionPane.showInputDialog(localizationResources.getMessage("введите")+"FuelType:");
            try {
                VehicleType.valueOf(type);
                FuelType.valueOf(fuelType);
                Float.parseFloat(x);
                Float.parseFloat(y);
                Float.parseFloat(enginePower);
                Float.parseFloat(capacity);
                arrayOfInput[1] += " " + name + " " + x
                        + " " + y + " " + enginePower
                        + " " + capacity + " " + type
                        + " " + fuelType;

            } catch (Exception e) {
                flag = false;
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(this, localizationResources.getMessage("неправильно"));
            }
        }
        if (flag) {
            System.out.println(arrayOfInput[0]);
            InteractiveMode.runCommand(arrayOfInput);
        }
        resultArea.setText(RequestProcessor.getCommandResult().getMessage());
    }

    private void back() {
        cardLayout.show(cardPanel, "page2");
    }

    public void infPage() {
        cardLayout.show(cardPanel, "page3");
    }

    private void loginUser() throws NoSuchAlgorithmException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (!username.isEmpty() && !password.isEmpty()) {
            Login login = new Login(username, password);
            if (login.login()) {
                currentUser = username;
                currentUserLabel.setText(localizationResources.getMessage("пользователь")+ currentUser);
                currentUserLabel2.setText(localizationResources.getMessage("пользователь")+currentUser);
                kitten.setIcon(icon);
            } else {
                JOptionPane.showMessageDialog(this, localizationResources.getMessage("неверный логин"));
            }
            usernameField.setText("");
            passwordField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, localizationResources.getMessage("введите всё"));
        }
    }

    private void registerUser() throws NoSuchAlgorithmException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (!username.isEmpty() && !password.isEmpty()) {
            Register register = new Register(username, password);
            if (register.register()) {
                currentUser = username;
                currentUserLabel.setText(localizationResources.getMessage("пользователь")+ currentUser);
                currentUserLabel2.setText(localizationResources.getMessage("пользователь")+ currentUser);
                kitten.setIcon(icon);
            } else {
                JOptionPane.showMessageDialog(this, localizationResources.getMessage("занято"));
            }
            usernameField.setText("");
            passwordField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, localizationResources.getMessage("введите всё"));
        }
    }
}
