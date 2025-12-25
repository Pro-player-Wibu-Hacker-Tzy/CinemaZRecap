package org.example;

import java.io.*;
import java.util.ArrayList;

public class FilmFileHandler {
    private static final String FILE = "data/film.csv";

    public static ArrayList<movieapp.Film> load() {
        ArrayList<movieapp.Film> list = new ArrayList<>();
        try {
            File f = new File(FILE);

            // Jika belum ada file → buat + isi 10 data default
            if (!f.exists() || f.length() == 0) {
                System.out.println("Generating default movie data...");
                generateDefaultData();
            }

            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line;

            while ((line = br.readLine()) != null) {
                String[] x = line.split(",");
                if (x.length < 5) continue;

                list.add(new movieapp.Film(
                        Integer.parseInt(x[0]),
                        x[1],
                        x[2],
                        Integer.parseInt(x[3]),
                        Integer.parseInt(x[4])
                ));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error load: " + e);
        }
        return list;
    }

    public static void save(ArrayList<movieapp.Film> list) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            for (movieapp.Film f : list) {
                bw.write(f.toCSV());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error save: " + e);
        }
    }

    private static void generateDefaultData() {
        try {
            new File("data").mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));

            String[] defaultMovies = {
                    "1,Avengers: Endgame,Action,2019,181",
                    "2,Inception,Sci-Fi,2010,148",
                    "3,The Dark Knight,Action,2008,152",
                    "4,Interstellar,Sci-Fi,2014,169",
                    "5,Toy Story 4,Animation,2019,100",
                    "6,Avatar,Fantasy,2009,162",
                    "7,Fast & Furious 7,Action,2015,137",
                    "8,Jurassic World,Adventure,2015,124",
                    "9,Spider-Man: No Way Home,Action,2021,148",
                    "10,The Lion King,Animation,2019,118"
            };

            for (String m : defaultMovies) {
                bw.write(m);
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            System.out.println("Error generate default data: " + e);
        }
    }
}