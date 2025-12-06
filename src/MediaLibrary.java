import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MediaLibrary implements Serializable {
    private List<MediaItem> items = new ArrayList<>();

    public void addItem(MediaItem item) {
        items.add(item);
    }

    public void showAll() {
        for (MediaItem item : items) {
            System.out.println(item.getInfo());
        }
    }

    public void searchByTitle(String keyword) {
        for (MediaItem item : items) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(item.getInfo());
            }
        }
    }

    // Сохраняем библиотеку
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(items);
            System.out.println("Библиотека сохранена.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Загружаем библиотеку
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            items = (List<MediaItem>) ois.readObject();
            System.out.println("Библиотека загружена.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден, создается новая библиотека.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Добавляем этот метод для GUI
    public List<MediaItem> getItems() {
        return items;
    }
}
