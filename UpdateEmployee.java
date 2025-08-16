package com.ems.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee extends JFrame {
    private JTextField txtId, txtName, txtSalary, txtDepartment, txtPosition;
    private JButton btnFetch, btnUpdate, btnBack;

    public UpdateEmployee() {
        setTitle("Update Employee");
        setSize(420, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Dimension fieldSize = new Dimension(200, 25);

        // Row 1 - ID
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblId = new JLabel("Employee ID:");
        lblId.setFont(labelFont);
        panel.add(lblId, gbc);

        gbc.gridx = 1;
        txtId = new JTextField();
        txtId.setPreferredSize(fieldSize);
        panel.add(txtId, gbc);

        gbc.gridx = 2;
        btnFetch = new JButton("Fetch");
        panel.add(btnFetch, gbc);

        // Row 2 - Name
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(labelFont);
        panel.add(lblName, gbc);

        gbc.gridx = 1;
        txtName = new JTextField();
        txtName.setPreferredSize(fieldSize);
        txtName.setEnabled(false);
        panel.add(txtName, gbc);

        // Row 3 - Salary
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblSalary = new JLabel("Salary:");
        lblSalary.setFont(labelFont);
        panel.add(lblSalary, gbc);

        gbc.gridx = 1;
        txtSalary = new JTextField();
        txtSalary.setPreferredSize(fieldSize);
        txtSalary.setEnabled(false);
        panel.add(txtSalary, gbc);

        // Row 4 - Department
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lblDept = new JLabel("Department:");
        lblDept.setFont(labelFont);
        panel.add(lblDept, gbc);

        gbc.gridx = 1;
        txtDepartment = new JTextField();
        txtDepartment.setPreferredSize(fieldSize);
        txtDepartment.setEnabled(false);
        panel.add(txtDepartment, gbc);

        // Row 5 - Position
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lblPos = new JLabel("Position:");
        lblPos.setFont(labelFont);
        panel.add(lblPos, gbc);

        gbc.gridx = 1;
        txtPosition = new JTextField();
        txtPosition.setPreferredSize(fieldSize);
        txtPosition.setEnabled(false);
        panel.add(txtPosition, gbc);

        // Row 6 - Buttons
        gbc.gridx = 0; gbc.gridy = 5;
        btnBack = new JButton("Back");
        panel.add(btnBack, gbc);

        gbc.gridx = 1;
        btnUpdate = new JButton("Update");
        btnUpdate.setEnabled(false);
        panel.add(btnUpdate, gbc);

        add(panel);
        setVisible(true);

        // --- Actions ---
        btnFetch.addActionListener(e -> fetchEmployee());
        btnUpdate.addActionListener(e -> updateEmployee());
        btnBack.addActionListener(e -> {
            dispose();
            new EMS();
        });
    }

    private void fetchEmployee() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "mammu");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtName.setText(rs.getString("name"));
                txtSalary.setText(String.valueOf(rs.getDouble("salary")));
                txtDepartment.setText(rs.getString("department"));
                txtPosition.setText(rs.getString("position"));

                txtId.setEditable(false);
                txtName.setEnabled(true);
                txtSalary.setEnabled(true);
                txtDepartment.setEnabled(true);
                txtPosition.setEnabled(true);
                btnUpdate.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found!");
            }

            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fetch error: " + ex.getMessage());
        }
    }

    private void updateEmployee() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            double salary = Double.parseDouble(txtSalary.getText());
            String dept = txtDepartment.getText();
            String position = txtPosition.getText();

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "mammu");
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE employee SET name = ?, salary = ?, department = ?, position = ? WHERE id = ?"
            );

            ps.setString(1, name);
            ps.setDouble(2, salary);
            ps.setString(3, dept);
            ps.setString(4, position);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Updated successfully!");
                dispose();
                new EMS();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed!");
            }

            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Update error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee();
    }
}
