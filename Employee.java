package com.ems.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EMS extends JFrame {
    public EMS() {
        setTitle("Employee Management System");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JButton addBtn = new JButton("Add Employee");
        addBtn.setBounds(150, 40, 200, 40);
        addBtn.setBackground(Color.CYAN);
        addBtn.setForeground(Color.BLACK);
        addBtn.addActionListener(e -> {
            new AddEmployee(); // Implement this class
            dispose();
        });
        add(addBtn);

        JButton viewBtn = new JButton("View Employee");
        viewBtn.setBounds(150, 90, 200, 40);
        viewBtn.setBackground(Color.PINK);
        viewBtn.setForeground(Color.BLACK);
        viewBtn.addActionListener(e -> {
            new ViewEmployee(); // Implement this class
            dispose();
        });
        add(viewBtn);

        JButton viewAllBtn = new JButton("View All Employees");
        viewAllBtn.setBounds(150, 140, 200, 40);
        viewAllBtn.setBackground(Color.YELLOW);
        viewAllBtn.setForeground(Color.BLACK);
        viewAllBtn.addActionListener(e -> {
            new DisplayAll();
            dispose();
        });
        add(viewAllBtn);

        JButton updateBtn = new JButton("Update Employee");
        updateBtn.setBounds(150, 190, 200, 40);
        updateBtn.setBackground(Color.ORANGE);
        updateBtn.setForeground(Color.BLACK);
        updateBtn.addActionListener(e -> {
            new UpdateEmployee();
            dispose();
        });
        add(updateBtn);

        JButton deleteBtn = new JButton("Delete Employee");
        deleteBtn.setBounds(150, 240, 200, 40);
        deleteBtn.setBackground(Color.RED);
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.addActionListener(e -> {
            new DeleteEmployee();
            dispose();
        });
        add(deleteBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(150, 290, 200, 40);
        exitBtn.setBackground(Color.GRAY);
        exitBtn.setForeground(Color.WHITE);
        exitBtn.addActionListener(e -> System.exit(0));
        add(exitBtn);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new EMS();
    }
}
