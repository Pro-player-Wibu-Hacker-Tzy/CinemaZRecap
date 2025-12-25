package movieapp;

import java.awt.GridLayout;
import javax.swing.*;
import java.util.ArrayList;

public class FilmForm extends JFrame {
    JTextField tTitle = new JTextField();
    JTextField tGenre = new JTextField();
    JTextField tYear = new JTextField();
    JTextField tDuration = new JTextField();
    Film editFilm;

    public FilmForm(Film film) {
        this.editFilm = film;

        setTitle(film == null ? "Tambah Film" : "Edit Film");
        setSize(400,300);
        setLayout(new GridLayout(5,2,5,5));
        setLocationRelativeTo(null);

        add(new JLabel("Judul"));
        add(tTitle);
        add(new JLabel("Genre"));
        add(tGenre);
        add(new JLabel("Tahun"));
        add(tYear);
        add(new JLabel("Durasi (menit)"));
        add(tDuration);

        JButton save = new JButton("Simpan");
        add(save);

        if (film != null) {
            tTitle.setText(film.getTitle());
            tGenre.setText(film.getGenre());
            tYear.setText(String.valueOf(film.getYear()));
            tDuration.setText(String.valueOf(film.getDuration()));
        }

        save.addActionListener(e -> saveData());

        setVisible(true);
    }

    void saveData() {
        try {
            ArrayList<Film> list = FilmFileHandler.load();

            String title = tTitle.getText();
            String genre = tGenre.getText();
            int year = Integer.parseInt(tYear.getText());
            int duration = Integer.parseInt(tDuration.getText());

            if (editFilm == null) {
                int id = list.size() + 1;
                list.add(new Film(id, title, genre, year, duration));
            } else {
                editFilm.setTitle(title);
                editFilm.setGenre(genre);
                editFilm.setYear(year);
                editFilm.setDuration(duration);
            }

            FilmFileHandler.save(list);
            JOptionPane.showMessageDialog(this, "Berhasil disimpan!");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid!");
        }
    }
}
