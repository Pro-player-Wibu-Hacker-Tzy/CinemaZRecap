package org.example;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    public DashboardFrame() {
        setTitle("Movie Management System");
        setSize(400,300);
        setLayout(new GridLayout(4,1,10,10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton listBtn = new JButton("📋 Data Film");
        JButton addBtn = new JButton("➕ Tambah Film");
        JButton reportBtn = new JButton("📊 Laporan");
        JButton exitBtn = new JButton("❌ Keluar");

        listBtn.addActionListener(e -> new FilmList());
        addBtn.addActionListener(e -> new FilmForm(null));
        reportBtn.addActionListener(e -> new FilmReport());
        exitBtn.addActionListener(e -> System.exit(0));

        add(listBtn);
        add(addBtn);
        add(reportBtn);
        add(exitBtn);

        setVisible(true);
    }
}