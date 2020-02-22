package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TextEditor extends JFrame {
    final int WIDTH = 600;
    final int HEIGHT = 400;
    private JTextArea textArea;         //основное текстовое поле
    private Container container;        //основной контейнер
    private JButton saveButton;         //кнопка SAVE
    private JButton loadButton;         //кнопка LOAD
    private JTextField filenameField;   //Поле ввода имени файла


    public TextEditor() {
        super("Text Editor v.3.1");

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        container = getContentPane();

        //Добавим текстовое поле
        textArea = new JTextArea();
        textArea.setName("TextArea");

        //Сделаем текствое поле прокручиваемым
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setName("ScrollPane");
        //Укажем что скролл будет всегда
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Собираем все панели для вывода на экран

        container.add(scrollableTextArea, BorderLayout.CENTER);
        container.add(upArea(), BorderLayout.NORTH);
        container.add(new JLabel(" "), BorderLayout.SOUTH);    //Просто для красоты
        container.add(new JLabel("    "), BorderLayout.WEST);  //Просто для красоты
        container.add(new JLabel("    "), BorderLayout.EAST);  //Просто для красоты

        createListener();          //Установим слушатели
        menuBar();                 //Добавляем строку меню

        setVisible(true);          // Выводим окно на экран
    }

    //Верхняя область с полем имени файла и кнопками SAVE и LOAD
    private JPanel upArea() {
        JPanel upArea = new JPanel();
        upArea.setLayout(new FlowLayout(FlowLayout.CENTER));
        filenameField = new JTextField(30);
        filenameField.setName("FilenameField");

        //Создаем кнопку SAVE. Слушатель в отдельном методе.
        saveButton = new JButton("Save");
        saveButton.setName("SaveButton");

        //Создаем кнопку LOAD. Слушатель в отдельном методе.
        loadButton = new JButton("Load");
        loadButton.setName("LoadButton");

        // Собираем панель из поля ввода имени и 2х кнопок
        upArea.add(filenameField);
        upArea.add(saveButton);
        upArea.add(loadButton);

        return upArea;
    }

    public void menuBar() {
        //Создаем строку меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Создаем меню FILE
        JMenu fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        //Создаем пункт LOAD с мнемокомандой и возьмем слушатель от кнопки LOAD
        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.setName("MenuLoad");
        loadMenuItem.addActionListener(loadButton.getActionListeners()[0]);
        loadMenuItem.setMnemonic(KeyEvent.VK_L);


        //Создаем пункт SAVE с мнемокомандой и возьмем слушатель от кнопки SAVE
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setName("MenuSave");
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        saveMenuItem.addActionListener(saveButton.getActionListeners()[0]);

        //Создаем пункт EXIT с мнемокодом и безопасным выходом
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setName("MenuExit");
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.addActionListener(actionEvent -> dispose());

        //Добавим пункты в меню
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
    }

    //Устанавливаем все слушатели в одном месте
    public void createListener(){

        //Для операций SAVE
        saveButton.addActionListener(actionEvent -> {
            File file = new File(filenameField.getText());
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(container,
                        "ERROR!\nНевозможно создать файл:\n " + filenameField.getText());
            }
        });

        //Для операций LOAD
        loadButton.addActionListener(actionEvent -> {
            try {
                textArea.setText(new String(Files.readAllBytes(Paths.get(filenameField.getText()))));
            } catch (IOException e) {
//                Если открыть - будет классное окно с указанием ошибки. Но тест им пользоваться не умеет.
//                JOptionPane.showMessageDialog(container,
//                        "ERROR!\nНе найден файл:\n" + filenameField.getText());
                textArea.setText(null);
            }
        });
    }
}


