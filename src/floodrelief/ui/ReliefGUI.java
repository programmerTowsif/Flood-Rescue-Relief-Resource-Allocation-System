 
package floodrelief.ui;

import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import floodrelief.service.ReliefManager;
import floodrelief.model.*;
import floodrelief.exception.InsufficientResourceException;

public class ReliefGUI extends JFrame {

    private final ReliefManager manager = new ReliefManager();

    private JLabel[] stats = new JLabel[4];

    private DefaultTableModel reqModel, resModel;
    private DefaultTableModel shelterModel, historyModel;

    private JComboBox<String> reqChoice, resChoice;
    private JComboBox<String> reqType, resType;

    // Request fields
    private JTextField reqIdField, requesterField;
    private JTextField peopleField, shelterIdField;
    private JTextField descriptionField, urgencyField;
    private JTextField foodQtyField, medicineNameField;
    private JTextField medicineQtyField;

    // Resource fields
    private JTextField[] resourceFields = new JTextField[6];

    // Shelter fields
    private JTextField[] shelterFields = new JTextField[5];

    public ReliefGUI() {

        setTitle("Flood Rescue & Relief Resource Allocation System");
        setSize(1150, 720);
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Dashboard", dashboard());
        tabs.addTab("Manage Requests", requests());
        tabs.addTab("Manage Resources", resources());
        tabs.addTab("Manage Shelters", shelters());
        tabs.addTab("Resource Allocation", allocation());
        tabs.addTab("Allocation History", history());

        add(tabs);

        refreshAll();
    }

    // ================= DASHBOARD =================

    private JPanel dashboard() {

        JPanel p = new JPanel(new GridLayout(2, 2, 25, 25));

        p.setBorder(
            BorderFactory.createEmptyBorder(45, 60, 45, 60)
        );

        String[] names = {
            "Total Requests: 0",
            "Total Shelters: 0",
            "Total Resources: 0",
            "Total Allocations: 0"
        };

        for (int i = 0; i < 4; i++) {

            stats[i] = new JLabel(
                names[i],
                SwingConstants.CENTER
            );

            stats[i].setFont(
                new Font("Arial", Font.BOLD, 23)
            );

            stats[i].setBorder(
                BorderFactory.createEtchedBorder()
            );

            p.add(stats[i]);
        }

        return p;
    }

    // ================= MANAGE REQUESTS =================

    private JPanel requests() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel form = new JPanel(
            new GridLayout(0, 4, 8, 8)
        );

        form.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        reqType = new JComboBox<>(
            new String[]{"Food Request", "Medical Request"}
        );

        reqIdField = new JTextField();
        requesterField = new JTextField();
        peopleField = new JTextField();
        shelterIdField = new JTextField();
        descriptionField = new JTextField();
        urgencyField = new JTextField();
        foodQtyField = new JTextField();
        medicineNameField = new JTextField();
        medicineQtyField = new JTextField();

        add(form, "Request Type", reqType);
        add(form, "Request ID", reqIdField);
        add(form, "Requester", requesterField);
        add(form, "People Count", peopleField);
        add(form, "Shelter ID", shelterIdField);
        add(form, "Description", descriptionField);
        add(form, "Urgency (1-5)", urgencyField);
        add(form, "Food Quantity", foodQtyField);
        add(form, "Medicine Name", medicineNameField);
        add(form, "Medicine Quantity", medicineQtyField);

        JButton save = new JButton("Add Request");
        JButton clearButton = new JButton("Clear");

        JPanel actions = new JPanel();
        actions.add(save);
        actions.add(clearButton);

        reqModel = tableModel(
            "ID", "Type", "Requester",
            "People", "Shelter", "Details", "Priority"
        );

        JTable table = new JTable(reqModel);
        table.setAutoCreateRowSorter(true);

        save.addActionListener(e -> addRequest());

        clearButton.addActionListener(
            e -> clearRequestForm()
        );

        reqType.addActionListener(
            e -> updateRequestFields()
        );

        JPanel top = new JPanel(new BorderLayout());

        top.add(form, BorderLayout.CENTER);
        top.add(actions, BorderLayout.SOUTH);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        updateRequestFields();

        return panel;
    }

    private void updateRequestFields() {

        boolean food = "Food Request".equals(
            reqType.getSelectedItem()
        );

        foodQtyField.setEnabled(food);
        medicineNameField.setEnabled(!food);
        medicineQtyField.setEnabled(!food);
    }

    private void clearRequestForm() {

        reqIdField.setText("");
        requesterField.setText("");
        peopleField.setText("");
        shelterIdField.setText("");
        descriptionField.setText("");
        urgencyField.setText("");
        foodQtyField.setText("");
        medicineNameField.setText("");
        medicineQtyField.setText("");

        updateRequestFields();
    }

    private void addRequest() {

        try {

            int id = Integer.parseInt(
                reqIdField.getText().trim()
            );

            String name = requesterField.getText().trim();

            int people = Integer.parseInt(
                peopleField.getText().trim()
            );

            int shelter = Integer.parseInt(
                shelterIdField.getText().trim()
            );

            String desc = descriptionField.getText().trim();

            int urgency = Integer.parseInt(
                urgencyField.getText().trim()
            );

            if (name.isEmpty()) {
                throw new IllegalArgumentException(
                    "Requester name is required."
                );
            }

            if (desc.isEmpty()) {
                throw new IllegalArgumentException(
                    "Description is required."
                );
            }

            if (people <= 0 || shelter <= 0 || id <= 0) {
                throw new IllegalArgumentException(
                    "ID, people count and shelter ID must be positive."
                );
            }

            if (urgency < 1 || urgency > 5) {
                throw new IllegalArgumentException(
                    "Urgency must be between 1 and 5."
                );
            }

            ReliefRequest request;

            if ("Food Request".equals(reqType.getSelectedItem())) {

                int quantity = Integer.parseInt(
                    foodQtyField.getText().trim()
                );

                request = new FoodRequest(
                    id, name, people, shelter,
                    desc, urgency, quantity
                );

            } else {

                String medicine =
                    medicineNameField.getText().trim();

                int quantity = Integer.parseInt(
                    medicineQtyField.getText().trim()
                );

                if (medicine.isEmpty()) {
                    throw new IllegalArgumentException(
                        "Medicine name is required."
                    );
                }

                request = new MedicalRequest(
                    id, name, people, shelter,
                    desc, medicine, quantity
                );
            }

            manager.addRequest(request);

            refreshAll();
            clearRequestForm();

            info("Request added successfully.");

        } catch (Exception ex) {

            error(ex);
        }
    }

    // ================= MANAGE RESOURCES =================

    private JPanel resources() {

        JPanel p = new JPanel(new BorderLayout(8, 8));

        JPanel f = new JPanel(
            new GridLayout(0, 4, 7, 7)
        );

        f.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        resType = new JComboBox<>(
            new String[]{
                "Food Supply",
                "Water Supply",
                "Medical Supply"
            }
        );

        add(f, "Resource Type", resType);

        String[] labels = {
            "Resource ID",
            "Resource Name",
            "Quantity",
            "Expiry YYYY-MM-DD",
            "Water Unit",
            "Medicine Name"
        };

        for (int i = 0; i < 6; i++) {

            resourceFields[i] = new JTextField();

            add(f, labels[i], resourceFields[i]);
        }

        JButton save = new JButton("Add Resource");
        JButton clearButton = new JButton("Clear");

        JPanel actions = new JPanel();
        actions.add(save);
        actions.add(clearButton);

        resModel = tableModel(
            "ID", "Type", "Name", "Quantity", "Details"
        );

        JTable table = new JTable(resModel);
        table.setAutoCreateRowSorter(true);

        save.addActionListener(e -> addResource());

        clearButton.addActionListener(
            e -> clear(resourceFields)
        );

        resType.addActionListener(
            e -> updateResourceFields()
        );

        updateResourceFields();

        JPanel top = new JPanel(new BorderLayout());

        top.add(f, BorderLayout.CENTER);
        top.add(actions, BorderLayout.SOUTH);

        p.add(top, BorderLayout.NORTH);
        p.add(new JScrollPane(table), BorderLayout.CENTER);

        return p;
    }

    private void updateResourceFields() {

        String type = (String) resType.getSelectedItem();

        resourceFields[3].setEnabled(
            !"Water Supply".equals(type)
        );

        resourceFields[4].setEnabled(
            "Water Supply".equals(type)
        );

        resourceFields[5].setEnabled(
            "Medical Supply".equals(type)
        );
    }

    private void addResource() {

        try {

            int id = num(resourceFields[0], "Resource ID");

            String name = val(
                resourceFields[1], "Resource name"
            );

            int qty = numZero(
                resourceFields[2], "Quantity"
            );

            String type = (String) resType.getSelectedItem();

            ReliefResource r;

            if ("Food Supply".equals(type)) {

                String date = val(
                    resourceFields[3], "Expiry date"
                );

                LocalDate.parse(date);

                r = new FoodSupply(
                    id, name, qty, date
                );

            } else if ("Water Supply".equals(type)) {

                r = new WaterSupply(
                    id, name, qty,
                    val(resourceFields[4], "Water unit")
                );

            } else {

                String date = val(
                    resourceFields[3], "Expiry date"
                );

                LocalDate.parse(date);

                r = new MedicalSupply(
                    id,
                    name,
                    qty,
                    val(resourceFields[5], "Medicine name"),
                    date
                );
            }

            manager.addResource(r);

            refreshAll();
            clear(resourceFields);

            info("Resource added successfully.");

        } catch (Exception ex) {

            error(ex);
        }
    }

    // ================= MANAGE SHELTERS =================

    private JPanel shelters() {

        JPanel p = new JPanel(new BorderLayout(8, 8));

        JPanel f = new JPanel(
            new GridLayout(0, 4, 7, 7)
        );

        f.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        String[] labels = {
            "Shelter ID",
            "Shelter Name",
            "Location",
            "Capacity",
            "Current Occupancy"
        };

        for (int i = 0; i < 5; i++) {

            shelterFields[i] = new JTextField();

            add(f, labels[i], shelterFields[i]);
        }

        JButton save = new JButton("Add Shelter");
        JButton clearButton = new JButton("Clear");

        JPanel actions = new JPanel();
        actions.add(save);
        actions.add(clearButton);

        shelterModel = tableModel(
            "ID", "Name", "Location",
            "Capacity", "Occupancy", "Available"
        );

        JTable table = new JTable(shelterModel);
        table.setAutoCreateRowSorter(true);

        save.addActionListener(e -> addShelter());

        clearButton.addActionListener(
            e -> clear(shelterFields)
        );

        JPanel top = new JPanel(new BorderLayout());

        top.add(f, BorderLayout.CENTER);
        top.add(actions, BorderLayout.SOUTH);

        p.add(top, BorderLayout.NORTH);
        p.add(new JScrollPane(table), BorderLayout.CENTER);

        return p;
    }

    private void addShelter() {

        try {

            Shelter s = new Shelter(
                num(shelterFields[0], "Shelter ID"),
                val(shelterFields[1], "Shelter name"),
                val(shelterFields[2], "Location"),
                num(shelterFields[3], "Capacity"),
                numZero(shelterFields[4], "Occupancy")
            );

            manager.addShelter(s);

            refreshAll();
            clear(shelterFields);

            info("Shelter added successfully.");

        } catch (Exception ex) {

            error(ex);
        }
    }

    // ================= RESOURCE ALLOCATION =================

    private JPanel allocation() {

        JPanel p = new JPanel(
            new GridLayout(0, 2, 12, 12)
        );

        p.setBorder(
            BorderFactory.createEmptyBorder(70, 100, 70, 100)
        );

        reqChoice = new JComboBox<>();
        resChoice = new JComboBox<>();

        p.add(new JLabel("Select Request"));
        p.add(reqChoice);

        p.add(new JLabel("Select Resource"));
        p.add(resChoice);

        JButton allocate = new JButton("Allocate Resource");
        JButton refresh = new JButton("Refresh Lists");

        p.add(allocate);
        p.add(refresh);

        allocate.addActionListener(
            e -> allocateResource()
        );

        refresh.addActionListener(
            e -> refreshChoices()
        );

        return p;
    }

    private void allocateResource() {

        try {

            if (reqChoice.getSelectedItem() == null
                || resChoice.getSelectedItem() == null) {

                throw new IllegalArgumentException(
                    "Please add requests and resources first."
                );
            }

            int rid = Integer.parseInt(
                reqChoice.getSelectedItem()
                    .toString().split(" - ")[0]
            );

            int sid = Integer.parseInt(
                resChoice.getSelectedItem()
                    .toString().split(" - ")[0]
            );

            manager.allocateResource(rid, sid);

            refreshAll();

            info("Allocation successful.");

        } catch (InsufficientResourceException ex) {

            error(ex);

        } catch (Exception ex) {

            error(ex);
        }
    }

    // ================= ALLOCATION HISTORY =================

    private JPanel history() {

        JPanel p = new JPanel(new BorderLayout());

        historyModel = tableModel(
            "Request ID",
            "Resource ID",
            "Quantity",
            "Allocation Date"
        );

        JTable table = new JTable(historyModel);
        table.setAutoCreateRowSorter(true);

        p.add(new JScrollPane(table));

        return p;
    }

    // ================= REFRESH METHODS =================

    private void refreshAll() {

        if (stats[0] != null) {

            stats[0].setText(
                "Total Requests: " + manager.getRequests().size()
            );

            stats[1].setText(
                "Total Shelters: " + manager.getShelters().size()
            );

            stats[2].setText(
                "Total Resources: " + manager.getResources().size()
            );

            stats[3].setText(
                "Total Allocations: " + manager.getAllocations().size()
            );
        }

        refreshRequests();
        refreshResources();
        refreshShelters();
        refreshChoices();
        refreshHistory();
    }

    private void refreshRequests() {

        if (reqModel == null) return;

        reqModel.setRowCount(0);

        for (ReliefRequest r : manager.getRequests()) {

            String details = r.getRequestType();
            int priority = 0;

            if (r instanceof FoodRequest) {

                FoodRequest x = (FoodRequest) r;

                details += " / Qty " + x.getFoodQuantity();

                priority = x.calculatePriority();
            }

            if (r instanceof MedicalRequest) {

                MedicalRequest x = (MedicalRequest) r;

                details += " / " + x.getMedicineName()
                    + " x " + x.getMedicineQuantity();
            }

            reqModel.addRow(new Object[]{
                r.getRequestId(),
                r.getRequestType(),
                r.getRequesterName(),
                r.getPeopleCount(),
                r.getShelterId(),
                details,
                priority
            });
        }
    }

    private void refreshResources() {

        if (resModel == null) return;

        resModel.setRowCount(0);

        for (ReliefResource r : manager.getResources()) {

            String details = "";

            if (r instanceof FoodSupply) {

                details = "Expiry: "
                    + ((FoodSupply) r).getExpiryDate();

            } else if (r instanceof WaterSupply) {

                details = "Unit: "
                    + ((WaterSupply) r).getUnit();

            } else if (r instanceof MedicalSupply) {

                MedicalSupply m = (MedicalSupply) r;

                details = m.getMedicineName()
                    + " / " + m.getExpiryDate();
            }

            resModel.addRow(new Object[]{
                r.getResourceId(),
                r.getResourceType(),
                r.getResourceName(),
                r.getQuantity(),
                details
            });
        }
    }

    private void refreshShelters() {

        if (shelterModel == null) return;

        shelterModel.setRowCount(0);

        for (Shelter s : manager.getShelters()) {

            shelterModel.addRow(new Object[]{
                s.getShelterId(),
                s.getShelterName(),
                s.getLocation(),
                s.getCapacity(),
                s.getCurrentOccupancy(),
                s.getAvailableSpace()
            });
        }
    }

    private void refreshChoices() {

        if (reqChoice == null || resChoice == null) return;

        reqChoice.removeAllItems();
        resChoice.removeAllItems();

        for (ReliefRequest r : manager.getRequests()) {

            reqChoice.addItem(
                r.getRequestId() + " - "
                + r.getRequestType() + " - "
                + r.getRequesterName()
            );
        }

        for (ReliefResource r : manager.getResources()) {

            resChoice.addItem(
                r.getResourceId() + " - "
                + r.getResourceType() + " - "
                + r.getResourceName()
                + " (qty " + r.getQuantity() + ")"
            );
        }
    }

    private void refreshHistory() {

        if (historyModel == null) return;

        historyModel.setRowCount(0);

        for (ReliefAllocation a : manager.getAllocations()) {

            historyModel.addRow(new Object[]{
                a.getRequestId(),
                a.getResourceId(),
                a.getAllocatedQuantity(),
                a.getAllocationDate()
            });
        }
    }

    // ================= HELPER METHODS =================

    private void add(
        JPanel p,
        String label,
        Component c
    ) {

        p.add(new JLabel(label));
        p.add(c);
    }

    private DefaultTableModel tableModel(String... cols) {

        return new DefaultTableModel(cols, 0) {

            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
    }

    private String val(JTextField f, String label) {

        String s = f.getText().trim();

        if (s.isEmpty()) {

            throw new IllegalArgumentException(
                label + " is required."
            );
        }

        return s;
    }

    private int num(JTextField f, String label) {

        int n = Integer.parseInt(val(f, label));

        if (n <= 0) {

            throw new IllegalArgumentException(
                label + " must be positive."
            );
        }

        return n;
    }

    private int numZero(JTextField f, String label) {

        int n = Integer.parseInt(val(f, label));

        if (n < 0) {

            throw new IllegalArgumentException(
                label + " cannot be negative."
            );
        }

        return n;
    }

    private void clear(JTextField[] fs) {

        for (JTextField f : fs) {

            if (f != null) {
                f.setText("");
            }
        }
    }

    private void info(String s) {

        JOptionPane.showMessageDialog(
            this,
            s,
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void error(Exception e) {

        JOptionPane.showMessageDialog(
            this,
            e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // ================= MAIN METHOD =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new ReliefGUI().setVisible(true)
        );
    }
} 