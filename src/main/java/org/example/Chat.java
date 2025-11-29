package org.example;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class Chat {
    public static void main(String[] args) {
        //frame размер для удобства такой
        JFrame frame = new JFrame("Clown Chat");
        //Честно так и не понля зачем, но вроде что-то базированное
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        //Грубо говоря окно отображения сообщений, фолс ясно почему не делаем, мы туда не пишем и не должны писать))
        JTextPane chat = new JTextPane();
        chat.setEditable(false);
        chat.setBackground(new Color(255, 201, 201));

        //Ну чтоб чат скроллить йоу
        JScrollPane scrollPane = new JScrollPane(chat);

        JTextField input = new JTextField();
        input.setFont(new Font("Times New Roman", Font.BOLD, 32));
        input.setPreferredSize(new Dimension(100, 60));

        //Темка со стилями
        StyledDocument document = chat.getStyledDocument();


        //Объект для хранения стиля текста, в него всё кидаем, дальше всё понятно из названий методов
        SimpleAttributeSet bubble = new SimpleAttributeSet();

        StyleConstants.setBackground(bubble, new Color(255, 179, 150));
        StyleConstants.setAlignment(bubble, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setFontSize(bubble, 32);
        StyleConstants.setLeftIndent(bubble, 200);
        StyleConstants.setRightIndent(bubble, 20);
        StyleConstants.setSpaceBelow(bubble, 25);

        //вывод сообщения

        input.addActionListener(e -> {
            String message = input.getText();
            if (!message.isEmpty()) {

                //Кол-во символов в чате чтобы мы понему закидывали некст сообщение
                int pos = document.getLength();
                try {
                    //Ну вот тут вставляем текст сообщения в pos, ну в номер позиции йоу
                    document.insertString(pos, message + "\n", bubble);
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                //применения стиля к сообщениеям
                document.setParagraphAttributes(pos, message.length(), bubble, false);

                input.setText("");
            }
        });

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(input,BorderLayout.SOUTH);
        frame.setVisible(true);

    }
}
