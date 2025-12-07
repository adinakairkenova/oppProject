# oppProject
# MediaLibrary — Java OOP Project with Swing GUI

Проект разработан для демонстрации ООП-принципов, событийно-ориентированного программирования и графического интерфейса на Java Swing.

---

## Project Architecture

```
MediaLibrary/
├── src/
│   ├── MediaItem.java        # Абстрактный класс
│   ├── Movie.java            # Класс фильма
│   ├── Series.java           # Класс сериала
│   ├── MediaLibrary.java     # Логика хранения данных
│   ├── MediaLibraryGUI.java  # Графический интерфейс
│   └── Main.java             # Консольный вход
├── catalog.dat               # Сохранённая база
├── catalog.txt               # Текстовая версия каталога
├── photos/                   # Скриншоты проекта
│   ├── gui.png
│   ├── code_example.png
│   └── ...
└── README.md
```

---

## Technologies Used

### Programming Language
- Java 17+

### GUI Framework
- Java Swing  
  - JFrame  
  - JPanel  
  - JButton  
  - JTextArea  
  - JTextField  
  - JComboBox  
  - ActionListener  

### Data Storage
- Java Serialization  
- Файловое хранение в `catalog.dat`  
- Автоматическая загрузка при запуске

### OOP Concepts
- Инкапсуляция  
- Наследование  
- Полиморфизм  
- Абстракция

---

## OOP Examples

### 1. Абстракция

Файл: `MediaItem.java`

```java
public abstract class MediaItem implements Serializable {
    private String title;
    private int year;
    private String genre;

    public abstract String getInfo();
}
```

---

### 2. Наследование

Файл: `Movie.java`

```java
public class Movie extends MediaItem {
    private String director;
}
```

Файл: `Series.java`

```java
public class Series extends MediaItem {
    private int seasons;
}
```

---

### 3. Полиморфизм

Использование общего списка объектов разных типов:

```java
for (MediaItem item : library.getItems()) {
    outputArea.append(item.getInfo() + "\n");
}
```

---

### 4. Инкапсуляция

Пример скрытых полей и геттеров:

```java
private String title;

public String getTitle() {
    return title;
}
```

---

## Application Interface (Swing)

Основное окно формируется в конструкторе:

```java
public MediaLibraryGUI() {
    super("Медиатека");
    library.loadFromFile("catalog.dat");
    setLayout(new BorderLayout());
    ...
}
```

Функциональные элементы интерфейса:
- панель ввода данных  
- панель кнопок  
- панель поиска  
- область вывода результатов  

---

## Event Handling Example

Файл: `MediaLibraryGUI.java`

Обработка нажатия кнопки «Добавить»:

```java
addButton.addActionListener(this::handleAdd);
```

Метод-обработчик:

```java
private void handleAdd(ActionEvent e) {
    library.addItem(new Movie(title, year, genre, director));
}
```

---

## Features Implemented

- добавление фильмов и сериалов  
- удаление по названию  
- поиск по подстроке  
- вывод всех записей  
- автосохранение при выходе  
- загрузка базы при старте  
- кастомные элементы интерфейса  
- сохранение данных между сессиями  

---

## How to Run

### Запуск GUI:

```
javac src/*.java
java MediaLibraryGUI
```

или через IntelliJ IDEA:

Run → MediaLibraryGUI

### Консольная версия:

```
java Main
```

---

## Author

Аднина Каиркенова  
GitHub: https://github.com/adinakairkenova  
E-mail: adinohka.29@gmail.ru

---

## License

MIT License


GUI Screenshots

Главное окно
![Main Window](photos/oop2.jfif)

Панель добавления фильма
![Add Movie Panel](photos/oop3.jfif)

Панель поиска
![Search Panel](photos/oop6.jfif)


