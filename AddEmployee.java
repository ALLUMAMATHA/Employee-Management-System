package com.ems.gui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.ems.db.MySQLConnection;

public class AddEmployee extends JFrame {
    private JTextField nameField, salaryField, deptField, positionField;

    public AddEmployee() {
        setTitle("Employee Onboarding");
        setSize(400, 400);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 40, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 40, 150, 25);
        add(nameField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(50, 80, 100, 25);
        add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(150, 80, 150, 25);
        add(salaryField);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(50, 120, 100, 25);
        add(deptLabel);

        deptField = new JTextField();
        deptField.setBounds(150, 120, 150, 25);
        add(deptField);

        JLabel posLabel = new JLabel("Position:");
        posLabel.setBounds(50, 160, 100, 25);
        add(posLabel);

        positionField = new JTextField();
        positionField.setBounds(150, 160, 150, 25);
        add(positionField);

        JButton onboardBtn = new JButton("Onboard");
        onboardBtn.setBounds(50, 210, 120, 30);
        add(onboardBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(180, 210, 120, 30);
        add(backBtn);

        // Onboard Button Action
        onboardBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                String dept = deptField.getText();
                String pos = positionField.getText();

                try (Connection con = MySQLConnection.getConnection()) {
                    String sql = "INSERT INTO employee (name, salary, department, position) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, name);
                    ps.setDouble(2, salary);
                    ps.setString(3, dept);
                    ps.setString(4, pos);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "ONBOARDING SUCCESSFUL");
                    clearFields();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
                }
            }
        });

        // Back Button Action
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EMS().setVisible(true);
                dispose(); // close current window
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Helper method to clear fields after onboarding
    private void clearFields() {
        nameField.setText("");
        salaryField.setText("");
        deptField.setText("");
        positionField.setText("");
    }
}
