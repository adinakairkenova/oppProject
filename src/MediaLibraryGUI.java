import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MediaLibraryGUI extends JFrame {
    private final MediaLibrary library = new MediaLibrary(); // библиотека
    private final JTextArea outputArea = new JTextArea(15, 40);
    private final JTextField titleField = new JTextField(15);
    private final JTextField yearField = new JTextField(5);
    private final JTextField genreField = new JTextField(10);
    private final JTextField extraField = new JTextField(10);
    private final JComboBox<String> typeBox = new JComboBox<>(new String[]{"Фильм", "Сериал"});
    private final JTextField searchField = new JTextField(15);

    public MediaLibraryGUI() {
        super("Медиатека ");

        library.loadFromFile("catalog.dat"); // загружаем библиотеку при старте

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Настройки outputArea
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(255, 245, 250));
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Панель добавления
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Добавить элемент"));
        inputPanel.setBackground(new Color(255, 230, 240));

        inputPanel.add(new JLabel("Тип:"));
        inputPanel.add(typeBox);
        inputPanel.add(new JLabel("Название:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Год:"));
        inputPanel.add(yearField);
        inputPanel.add(new JLabel("Жанр:"));
        inputPanel.add(genreField);
        inputPanel.add(new JLabel("Режиссёр / Сезонов:"));
        inputPanel.add(extraField);

        // Создаём кнопки
        JButton addButton = createCuteButton("Добавить");
        addButton.addActionListener(this::handleAdd);

        JButton saveButton = createCuteButton("Сохранить в файл");
        saveButton.addActionListener(e -> library.saveToFile("catalog.dat"));

        JButton deleteButton = createCuteButton("Удалить");
        deleteButton.addActionListener(e -> handleDelete());


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(255, 220, 230));
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);

        // Панель поиска
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createTitledBorder("Поиск"));
        searchPanel.setBackground(new Color(255, 220, 230));
        searchPanel.add(new JLabel("Название:"));
        searchPanel.add(searchField);

        JButton searchButton = createCuteButton("Искать");
        searchButton.addActionListener(this::handleSearch);
        searchPanel.add(searchButton);

        JButton showAllButton = createCuteButton("Показать все");
        showAllButton.addActionListener(e -> showAll());
        searchPanel.add(showAllButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);
        add(new JScrollPane(outputArea), BorderLayout.EAST);


        // Сохраняем при закрытии окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                library.saveToFile("catalog.dat");
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleAdd(ActionEvent e) {
        try {
            String title = titleField.getText();
            int year = Integer.parseInt(yearField.getText());
            String genre = genreField.getText();

            if (typeBox.getSelectedItem().equals("Фильм")) {
                String director = extraField.getText();
                library.addItem(new Movie(title, year, genre, director));
            } else {
                int seasons = Integer.parseInt(extraField.getText());
                library.addItem(new Series(title, year, genre, seasons));
            }

            clearInputs();
            outputArea.setText("Добавлено: " + title);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка ввода: " + ex.getMessage());
        }
    }

    private void handleSearch(ActionEvent e) {
        String keyword = searchField.getText();
        outputArea.setText("");
        for (MediaItem item : library.getItems()) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                outputArea.append(item.getInfo() + "\n");
            }
        }
    }

    private void handleDelete() {
        String title = searchField.getText().trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Введите название для удаления в поле поиска!");
            return;
        }

        boolean removed = library.getItems().removeIf(item ->
                item.getTitle().equalsIgnoreCase(title));

        if (removed)
            outputArea.setText("Удалено: " + title);
        else
            outputArea.setText("Не найдено!");
    }

    private void showAll() {
        outputArea.setText("");
        for (MediaItem item : library.getItems()) {
            outputArea.append(item.getInfo() );
        }
    }

    private void clearInputs() {
        titleField.setText("");
        yearField.setText("");
        genreField.setText("");
        extraField.setText("");
    }


    private JButton createCuteButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(255, 180, 200),
                        0, getHeight(), new Color(255, 120, 160));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            public void setContentAreaFilled(boolean b) {
                super.setContentAreaFilled(false);
            }
        };
        button.setFocusPainted(false);
        button.setForeground(new Color(60, 0, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MediaLibraryGUI::new);
    }
}
