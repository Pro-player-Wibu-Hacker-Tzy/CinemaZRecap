package movieapp;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FilmReport extends JFrame {
    public FilmReport() {
        setTitle("Laporan Film");
        setSize(400,300);
        setLocationRelativeTo(null);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        ArrayList<Film> list = FilmFileHandler.load();

        area.append("Total Film: " + list.size() + "\n\n");

        HashMap<String, Integer> genreCount = new HashMap<>();
        for (Film f : list) {
            genreCount.put(f.getGenre(), genreCount.getOrDefault(f.getGenre(),0)+1);
        }

        area.append("Jumlah Film per Genre:\n");
        for (String g : genreCount.keySet()) {
            area.append(g + " : " + genreCount.get(g) + "\n");
        }

        add(new JScrollPane(area));
        setVisible(true);
    }
}
