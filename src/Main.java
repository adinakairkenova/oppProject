import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MediaLibrary library = new MediaLibrary();
        library.loadFromFile("catalog.dat"); // загружаем библиотеку из файла


        while (true) {
            System.out.println("\n1 - Добавить фильм");
            System.out.println("2 - Добавить сериал");
            System.out.println("3 - Показать все");
            System.out.println("4 - Поиск по названию");
            System.out.println("5 - Сохранить в файл");
            System.out.println("0 - Выход");
            System.out.print("Выбор: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Название: ");
                    String title = sc.nextLine();
                    System.out.print("Год: ");
                    int year = Integer.parseInt(sc.nextLine());
                    System.out.print("Жанр: ");
                    String genre = sc.nextLine();
                    System.out.print("Режиссёр: ");
                    String director = sc.nextLine();
                    library.addItem(new Movie(title, year, genre, director));
                }
                case "2" -> {
                    System.out.print("Название: ");
                    String title = sc.nextLine();
                    System.out.print("Год: ");
                    int year = Integer.parseInt(sc.nextLine());
                    System.out.print("Жанр: ");
                    String genre = sc.nextLine();
                    System.out.print("Количество сезонов: ");
                    int seasons = Integer.parseInt(sc.nextLine());
                    library.addItem(new Series(title, year, genre, seasons));
                }
                case "3" -> library.showAll();
                case "4" -> {
                    System.out.print("Введите название для поиска: ");
                    String keyword = sc.nextLine();
                    library.searchByTitle(keyword);
                }
                case "5" -> library.saveToFile("catalog.txt");
                case "0" -> {
                    library.saveToFile("catalog.dat"); // сохраняем все фильмы и сериалы
                    System.out.println("Выход из программы...");
                    return;
                }

                default -> System.out.println("Неверный ввод!");
            }
        }
    }
}
