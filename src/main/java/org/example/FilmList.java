package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class FilmList extends JFrame {

    JTable table;
    DefaultTableModel model;
    ArrayList<movieapp.Film> list;
    TableRowSorter<DefaultTableModel> sorter;
    JTextField searchField;

    public FilmList() {
        setTitle("Data Film");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        list = FilmFileHandler.load();

        String[] col = {"ID","Judul","Genre","Tahun","Durasi"};
        model = new DefaultTableModel(col,0);
        table = new JTable(model);

        // SORTING
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        loadTable();

        // SEARCH FIELD
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Cari");

        searchBtn.addActionListener(e -> searchData());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Cari:"));
        topPanel.add(searchField);
        topPanel.add(searchBtn);

        JButton add = new JButton("Tambah");
        JButton edit = new JButton("Edit");
        JButton del = new JButton("Hapus");
        JButton refresh = new JButton("Refresh");

        add.addActionListener(e -> {
            new movieapp.FilmForm(null);
            loadTable();
        });

        edit.addActionListener(e -> editFilm());
        del.addActionListener(e -> deleteFilm());
        refresh.addActionListener(e -> {
            searchField.setText("");
            sorter.setRowFilter(null);
            loadTable();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(add);
        bottomPanel.add(edit);
        bottomPanel.add(del);
        bottomPanel.add(refresh);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // LOAD DATA
    void loadTable() {
        list = FilmFileHandler.load();
        model.setRowCount(0);

        for (movieapp.Film f : list) {
            model.addRow(new Object[]{
                    f.getId(),
                    f.getTitle(),
                    f.getGenre(),
                    f.getYear(),
                    f.getDuration()
            });
        }
    }

    // SEARCH DATA
    void searchData() {
        String text = searchField.getText();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

    void editFilm() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,"Pilih data dulu!");
            return;
        }

        int modelRow = table.convertRowIndexToModel(row);
        movieapp.Film selected = list.get(modelRow);
        new movieapp.FilmForm(selected);
        loadTable();
    }

    void deleteFilm() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,"Pilih data dulu!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus film ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int modelRow = table.convertRowIndexToModel(row);
            list.remove(modelRow);
            FilmFileHandler.save(list);
            loadTable();
        }
    }
}