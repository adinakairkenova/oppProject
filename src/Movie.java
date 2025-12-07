public class Movie extends MediaItem {
    private String director;

    public Movie(String title, int year, String genre, String director) {
        super(title, year, genre);
        this.director = director;
    }

    @Override
    public String getInfo() {
        return "Фильм: " + getTitle() + " (" + getYear() + "), жанр: " + getGenre() +
                ", режиссёр: " + director;
    }
}
