package editor;

import javax.swing.*;
import java.awt.*;


public class TextEditor extends JFrame
{
    public TextEditor()
    {
        super("Пример JTextArea");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Добавим простое текстовое поле
        JTextArea textArea = new JTextArea(13, 20);
        setName("textArea");

        //Сделаем простое текствое поле прокручиваемым
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        //Укажем что скролл будет всегда
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Добавим поля в окно
        JPanel contents = new JPanel();

        contents.add(new JScrollPane(scrollableTextArea));
        setContentPane(contents);

        // Выводим окно на экран
        setVisible(true);
    }

}


