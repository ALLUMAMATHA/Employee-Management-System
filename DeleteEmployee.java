package com.ems.gui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import com.ems.db.MySQLConnection;

public class DeleteEmployee extends JFrame {
    private JTextField idField;
    private JButton deleteBtn, backBtn;

    public DeleteEmployee() {
        setTitle("Delete Employee");
        setSize(400, 250);
        setLayout(null);

        JLabel idLabel = new JLabel("Enter Employee ID:");
        idLabel.setBounds(50, 40, 150, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(180, 40, 120, 25);
        add(idField);

        deleteBtn = new JButton("Confirm Delete");
        deleteBtn.setBounds(60, 100, 120, 30);
        add(deleteBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(200, 100, 100, 30);
        add(backBtn);

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());

                int confirm = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to delete employee ID " + id + "?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try (Connection con = MySQLConnection.getConnection()) {
                        String sql = "DELETE FROM employee WHERE id=?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1, id);
                        int rowsAffected = ps.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "DELETION SUCCESSFUL");
                            idField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Employee ID not found!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    }
                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EMS().setVisible(true);
                dispose();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
