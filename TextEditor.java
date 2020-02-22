package editor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TextEditor extends JFrame
{
    final int WIDTH = 600;
    final int HEIGHT = 400;
    JTextArea textArea;
    Container container;

    public TextEditor()
    {
        super("Text Editor v.2.1");

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
        // Выводим окно на экран
        setVisible(true);
    }

    //Верхняя область с полем имени файла и кнопками SAVE и LOAD
    private JPanel upArea(){
        JPanel upArea = new JPanel();
        upArea.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextField filenameField = new JTextField(30);
        filenameField.setName("FilenameField");

        //Сохраняем в файл
        JButton saveButton = new JButton("Save");
        saveButton.setName("SaveButton");
        saveButton.addActionListener(actionEvent -> {
            File file = new File(filenameField.getText());

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(container,
                    "ERROR!\nНевозможно создать файл:\n " + filenameField.getText());
            }
        });
        //Загрузка файла с именем в текстовом поле
        JButton loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        loadButton.addActionListener(actionEvent -> {
            try{
               textArea.setText(new String(Files.readAllBytes(Paths.get(filenameField.getText()))));
            } catch (IOException e) {
//                Если открыть - будет классное окно с указанием ошибки. Но тест им пользоваться не умеет.
//                JOptionPane.showMessageDialog(container,
//                        "ERROR!\nНе найден файл:\n" + filenameField.getText());
                textArea.setText(null);
            }
        });

        // Собираем панель из поля ввода имени и 2х кнопок
        upArea.add(filenameField);
        upArea.add(saveButton);
        upArea.add(loadButton);

        return upArea;
    }


}


