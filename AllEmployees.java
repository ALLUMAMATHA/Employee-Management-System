package com.ems.gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DisplayAll extends JFrame {
    JTable table;
    DefaultTableModel model;
    JButton backButton;

    public DisplayAll() {
        setTitle("All Employees");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table model
        model = new DefaultTableModel(new String[]{"ID", "Name", "Salary", "Department", "Position"}, 0);
        table = new JTable(model);

        // Header color
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.GREEN);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Arial", Font.BOLD, 14));

        // Alternate row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            Color[] colors = new Color[]{Color.YELLOW, Color.PINK, Color.GREEN};
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(colors[row % colors.length]);
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back");
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(e -> {
            dispose();
            new EMS(); // Go back to EMS main window
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        fetchEmployees();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void fetchEmployees() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "mammu");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("salary"),
                    rs.getString("department"),
                    rs.getString("position")
                });
            }

            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new DisplayAll();
    }
}
