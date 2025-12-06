public class Series extends MediaItem {
    private int seasons;

    public Series(String title, int year, String genre, int seasons) {
        super(title, year, genre);
        this.seasons = seasons;
    }

    @Override
    public String getInfo() {
        return "Сериал: " + getTitle() + " (" + getYear() + "), жанр: " + getGenre() +
                ", сезонов: " + seasons;
    }
}
