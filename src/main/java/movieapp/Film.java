package movieapp;


public class Film {
    private int id;
    private String title;
    private String genre;
    private int year;
    private int duration;

    public Film(int id, String title, String genre, int year, int duration) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.duration = duration;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
    public int getDuration() { return duration; }

    public void setTitle(String title) { this.title = title; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setYear(int year) { this.year = year; }
    public void setDuration(int duration) { this.duration = duration; }

    public String toCSV() {
        return id + "|" + title + "|" + genre + "|" + year + "|" + duration;
    }
}