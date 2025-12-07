import java.io.Serializable;
public abstract class MediaItem implements Serializable {
    private String title;
    private int year;
    private String genre;

    public MediaItem(String title, int year, String genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public String getTitle() { return title; }
    public int getYear() { return year; }
    public String getGenre() { return genre; }

    public abstract String getInfo();
}
